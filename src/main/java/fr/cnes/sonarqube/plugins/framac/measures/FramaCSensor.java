package fr.cnes.sonarqube.plugins.framac.measures;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.cnes.sonarqube.plugins.framac.report.FramaCReportReader;
import fr.cnes.sonarqube.plugins.framac.report.ReportFunctionRuleInterface;
import fr.cnes.sonarqube.plugins.framac.report.ReportInterface;
import fr.cnes.sonarqube.plugins.framac.report.ReportModuleRuleInterface;


/**
 * Scan Frama-C report file.
 * For all project code file : <b>FILE</b>, Frama-C create a report file <b>FILE{@link FramaCSensor#REPORT_EXT}</b> into the {@link FramaCSensor#REPORT_SUBDIR} shall be
 * 
 * @author Cyrille FRANCOIS
 */
public class FramaCSensor implements Sensor {
	
	private static final Logger LOGGER = Loggers.get(FramaCSensor.class);
	
	/** Report sub directory */
	private static final String REPORT_SUBDIR = "oracle";
	/** Report extension */
	private static final String REPORT_OUT_EXT = ".res.oracle";
	/** project code file patterns */
	private static final String EXPECTED_REPORT_INPUT_FILE_TYPES = "*.c,*.i";
	
	FramaCReportReader framaCReportReader = null;
	
	@Override
	public void describe(SensorDescriptor descriptor) {
		descriptor.name(getClass().getName());
	}
	
	@Override
	public void execute(SensorContext context) {
		LOGGER.info("FramaCSensor is running...");
		FileSystem fs = context.fileSystem();
		LOGGER.info("FramaCSensor : file system base dir = " + fs.baseDir());
		FilePredicates p = fs.predicates();
		LOGGER.info("FramaCSensor : file system base dir = " + fs.hasFiles(p.all()));
		// // only "main" files, but not "tests"
		// Iterable<InputFile> files =
		// fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
		String[] icodeMatchingPatterns = matchingPatterns();
		Iterable<InputFile> filesC = fs.inputFiles(fs.predicates().matchesPathPatterns(icodeMatchingPatterns));
		for (InputFile file : filesC) {
			LOGGER.debug("FramaCSensor : current input file = " + file.absolutePath());

			// Check for report out
			String fileRelativePathNameReportOut = outReportFileName(file);

			// Analyse report out
			analyseReportOut(context, file, fileRelativePathNameReportOut);
		}
		LOGGER.info("FramaCSensor done!");
	}

	/**
	 * @return all expected file code patterns
	 */
	private String[] matchingPatterns() {
		StringBuffer sb = new StringBuffer();
		String patternSeparator = ",";
		String[] res = EXPECTED_REPORT_INPUT_FILE_TYPES.trim().split(patternSeparator);
		return res;
	}
	
	/**
	 * @param file input code file
	 * @return relative report file for this input code file
	 */
	private String outReportFileName(InputFile file) {		
		String reportOutExt = REPORT_OUT_EXT;
		return relativeReportFileName(file, reportOutExt);
	}

	/**
	 * @param file input code file
	 * @param reportOutExt report file extension
	 * @return relative report file for this input code file
	 */
	private String relativeReportFileName(InputFile file, String reportOutExt) {
		String separator = file.file().separator;
		String name = file.file().getName();
		int extensionPoint = name.lastIndexOf('.');
		return REPORT_SUBDIR+separator+name.substring(0,extensionPoint)+reportOutExt;
	}

	/**
	 *  Analyze a report file provided by the external tool ICode.
	 *  Check the report file integrity (exist, not empty and readable)
	 *  
	 * @param context Sonar sensor context
	 * @param file input code file 
	 * @param fileRelativePathNameReportOut name of the expected report file for this input code file
	 */
	private void analyseReportOut(
			SensorContext context, 
			InputFile file, 
			String fileRelativePathNameReportOut) {
		ReportInterface report = null;
		StringBuffer warningMsgs = new StringBuffer();
		int nbWarningMsgs = 0;
		StringBuffer errorMsgs = new StringBuffer();
		int nbErrorMsgs = 0;
		LOGGER.debug("file.absolutePath():"+file.absolutePath());
		LOGGER.debug("Paths.get(file.absolutePath()).getParent():"+Paths.get(file.absolutePath()).getParent());
		LOGGER.debug("fileRelativePathNameReportOut:"+fileRelativePathNameReportOut);
		Path fileReportPath = Paths.get(file.absolutePath()).getParent().resolve(fileRelativePathNameReportOut);
	    if(existReportFile(fileReportPath)){
  	    	  
			try {
				FileChannel reportFile = FileChannel.open(fileReportPath);
				framaCReportReader = new FramaCReportReader();
				report = framaCReportReader.parse(fileReportPath);
			    long reportFileSize = reportFile.size();
			    if(reportFileSize == 0){
			    	errorMsgs.append("Empty report file : "+fileRelativePathNameReportOut);
			    	nbErrorMsgs++;
			    }
			    if(report == null){
			    	errorMsgs.append("Report file : "+fileRelativePathNameReportOut+" cannot be analysed !");
			    	nbErrorMsgs++;
			    }
			} catch (IOException e) {
				errorMsgs.append("Unexpected error report file for : "+fileRelativePathNameReportOut);
		    	nbErrorMsgs++;
			}
	    }
	    else{
	    	errorMsgs.append("No report file for : "+fileRelativePathNameReportOut);
	    	nbErrorMsgs++;
	    }
		// Add a ICode report warning
		if(nbWarningMsgs>0){
		      context.<String>newMeasure()
		        .forMetric(FramaCMetrics.REPORT_FILES_WARNING)
		        .on(file)
		        .withValue(warningMsgs.toString())
		        .save();	    	  			
		      context.<Integer>newMeasure()
		        .forMetric(FramaCMetrics.NUMBER_OF_WARNINGS)
		        .on(file)
		        .withValue(nbWarningMsgs)
		        .save();	    	  			
		}
		// Add a ICode report error
		if(nbErrorMsgs>0){
		      context.<String>newMeasure()
		        .forMetric(FramaCMetrics.REPORT_FILES_ERROR)
		        .on(file)
		        .withValue(errorMsgs.toString())
		        .save();	    	  			
		      context.<Integer>newMeasure()
		        .forMetric(FramaCMetrics.NUMBER_OF_ERRORS)
		        .on(file)
		        .withValue(nbErrorMsgs)
		        .save();	    	  			
		}
		if(report != null){
			parseReportMeasures(context, file, report);
			parseReportIssues(context, file, report);
		}
	}

	/**
	 * Parse all measures from a valid report file. Measures shall be defined by
	 * Metrics {@link ICodeMetric} Only one measure by file and Metrics Measures
	 * by project are computed by {@link MeasureComputer#compute}
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param report
	 *            report file analyzed
	 * 
	 * @see XmlReportReader#parse
	 */
	private void parseReportMeasures(SensorContext context, InputFile file, ReportInterface report) {
		// Add metrics results
		ReportModuleRuleInterface reportModuleRuleInterface = report.getModuleCyclomaticMeasure();
		double cyclomaticValueSum = 0;
		double cyclomaticValueMin = Double.MAX_VALUE;
		double cyclomaticValueMax = 0;
		double locValueSum = 0;
		double locValueMin = Double.MAX_VALUE;
		double locValueMax = 0;
		double currentCyclomaticValue = 0;
		double currentLocValue = 0;

		// Create module measures (from each function measures provided by
		// ICode)
		if (reportModuleRuleInterface != null) {

			try {
				currentCyclomaticValue = Double.valueOf(reportModuleRuleInterface.getComplexity());
				currentLocValue = Double.valueOf(reportModuleRuleInterface.getLoc());

				// Sum all elements values for mean computation
				cyclomaticValueSum += currentCyclomaticValue;
				locValueSum += currentLocValue;

				// Search maximum
				if (currentCyclomaticValue > cyclomaticValueMax) {
					cyclomaticValueMax = currentCyclomaticValue;
				}
				if (currentLocValue > locValueMax) {
					locValueMax = currentLocValue;
				}

				// Search minimum
				if (currentCyclomaticValue < cyclomaticValueMin) {
					cyclomaticValueMin = currentCyclomaticValue;
				}
				if (currentLocValue < locValueMin) {
					locValueMin = currentLocValue;
				}
			} catch (Exception e) {
				LOGGER.error("No readable metrics measure: " + reportModuleRuleInterface.getComplexity());
				LOGGER.error("No readable metrics measure: " + reportModuleRuleInterface.getLoc());
			}
		}

		// Create measure for this file
		double cyclomaticValueMean = cyclomaticValueSum;
		double locValueMean = locValueSum;

		storeCyclomaticMeasures(context, file, cyclomaticValueMin, cyclomaticValueMax, cyclomaticValueMean,
				currentCyclomaticValue);
		storeLocMeasures(context, file, locValueMin, locValueMax, locValueMean, currentLocValue);

	}
	
	/**
	 * Store measures from a valid report file into Frama-C Metrics.
	 * 
	 * @param context Sonar sensor context
	 * @param file input code file 
	 * @param cyclomaticValueMin minimum
	 * @param cyclomaticValueMax maximum
	 * @param cyclomaticValueMean mean
	 * @param value cyclomatic complexity value
	 * 
	 * @see ICodeMetrics
	 */
	private void storeCyclomaticMeasures(SensorContext context, InputFile file, double cyclomaticValueMin,
			double cyclomaticValueMax, double cyclomaticValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure()
		.forMetric(CyclomaticMetrics.CYCLOMATIC)
		.on(file)
		.withValue(Integer.valueOf((int)value))
		.save();
		context.<Integer>newMeasure()
		.forMetric(CyclomaticMetrics.CYCLOMATIC_MAX)
		.on(file)
		.withValue(Integer.valueOf((int)cyclomaticValueMax))
		.save();
		context.<Integer>newMeasure()
		.forMetric(CyclomaticMetrics.CYCLOMATIC_MIN)
		.on(file)
		.withValue(Integer.valueOf((int)cyclomaticValueMin))
		.save();
		context.<Double>newMeasure()
		.forMetric(CyclomaticMetrics.CYCLOMATIC_MEAN)
		.on(file)
		.withValue(Double.valueOf(cyclomaticValueMean))
		.save();
	}
	/**
	 * Store measures from a valid report file into Frama-C Metrics.
	 * 
	 * @param context Sonar sensor context
	 * @param file input code file 
	 * @param locValueMin minimum
	 * @param locValueMax maximum
	 * @param locValueMean mean
	 * @param value loc complexity value
	 * 
	 * @see ICodeMetrics
	 */
	private void storeLocMeasures(SensorContext context, InputFile file, double locValueMin,
			double locValueMax, double locValueMean, double value) {
		// Store module loc, MEAN, MIN, MAX
		context.<Integer>newMeasure()
		.forMetric(CyclomaticMetrics.SLOC)
		.on(file)
		.withValue(Integer.valueOf((int)value))
		.save();
		context.<Integer>newMeasure()
		.forMetric(CyclomaticMetrics.SLOC_MAX)
		.on(file)
		.withValue(Integer.valueOf((int)locValueMax))
		.save();
		context.<Integer>newMeasure()
		.forMetric(CyclomaticMetrics.SLOC_MIN)
		.on(file)
		.withValue(Integer.valueOf((int)locValueMin))
		.save();
		context.<Double>newMeasure()
		.forMetric(CyclomaticMetrics.SLOC_MEAN)
		.on(file)
		.withValue(Double.valueOf(locValueMean))
		.save();
	}
		
	private void parseReportIssues(SensorContext context, InputFile file, ReportInterface report) {
		// Read all report issues
		ReportModuleRuleInterface reportModuleRuleInterface = report.getModuleCyclomaticMeasure();
		ReportFunctionRuleInterface[] reportModuleRuleInterfaces = report.getCyclomaticMeasureByFunction();

		// Create issues for this file
		if(reportModuleRuleInterface != null && reportModuleRuleInterfaces != null){
			InputFile inputFile = file;
			int lines = inputFile.lines();
			
			// Read measure value for each elements of this module
			for (ReportFunctionRuleInterface currentFunctionRuleInterface : reportModuleRuleInterfaces) {
				String line = currentFunctionRuleInterface.getLine();
	            int lineNr = getLineAsInt(line, lines);
//			            RuleKey ruleKey = ICodeRulesDefinition.RULE_CYCLO;//TODO: TBD
//			            NewIssue newIssue = context.newIssue().forRule(ruleKey);
//			            NewIssueLocation location = newIssue.newLocation()
//			                    .on(inputFile)
//			                    .at(inputFile.selectLine(lineNr > 0 ? lineNr : 1))
//			                    .message(currentFunctionRuleInterface.getValue());
//
//			            newIssue.at(location);
//			            newIssue.save();
//			            violationsCount++;//TODO: TBD count number of issues
			}
		}
	}

	/**
	 * Format line number according to the sonar expected format for issue
	 * 
	 * @param line string value of a line
	 * @param maxLine file line numbers
	 * @return Sonar complaint fine number (strictly positive)
	 */
	private static int getLineAsInt(String line, int maxLine) {
	    int lineNr = 0;
	    if (line != null) {
	      try {
	        lineNr = Integer.parseInt(line);
	        if (lineNr < 1) {
	          lineNr = 1;
	        } else if (lineNr > maxLine) {
	        	lineNr = maxLine;
	        }
	      } catch (java.lang.NumberFormatException nfe) {
	        LOGGER.warn("Skipping invalid line number: {}", line);
	        lineNr = -1;
	      }
	    }
	    return lineNr;
	}
	
	/**
	 * Check a expected report file.
	 * 
	 * @param fileReportPath
	 * @return true if the report file exist
	 */
	private static boolean existReportFile(Path fileReportPath) {
		boolean res=false;
		LOGGER.debug("existFile ?:"+fileReportPath.toAbsolutePath());
		res=Files.exists(fileReportPath, LinkOption.NOFOLLOW_LINKS);
		return res;
	}
}
