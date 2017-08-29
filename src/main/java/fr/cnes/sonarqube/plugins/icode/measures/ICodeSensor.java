package fr.cnes.sonarqube.plugins.icode.measures;

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
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.cnes.sonarqube.plugins.icode.report.XmlReportReader;

/**
 * Scan ICode report file. For all project code file : <b>FILE</b>, ICode create
 * a report file <b>FILE{@link ICodeSensor#REPORT_EXT}</b> into the
 * {@link ICodeSensor#REPORT_SUBDIR} shall be
 * 
 * @author Cyrille FRANCOIS
 */
public class ICodeSensor implements Sensor {

	private static final Logger LOGGER = Loggers.get(ICodeSensor.class);

	/** Report sub directory */
	public static final String REPORT_SUBDIR = "reports";
	/** Report extension */
	public static final String REPORT_EXT = ".res.xml";
	/** project code file patterns */
	public static final String EXPECTED_REPORT_INPUT_FILE_TYPES = "*.f,*.f77,*.f90";

	@Override
	public void describe(SensorDescriptor descriptor) {
		descriptor.name(getClass().getName());
	}

	@Override
	public void execute(SensorContext context) {
		LOGGER.info("ICodeSensor is running...");
		FileSystem fs = context.fileSystem();
		FilePredicates p = fs.predicates();
		// // only "main" files, but not "tests"
		// Iterable<InputFile> files =
		// fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
		String[] icodeMatchingPatterns = matchingPatterns();
		Iterable<InputFile> filesC = fs.inputFiles(fs.predicates().matchesPathPatterns(icodeMatchingPatterns));
		for (InputFile file : filesC) {

			// Check for report out
			String fileRelativePathNameReportOut = outReportFileName(file);

			// Analyse report out
			analyseReportOut(context, file, fileRelativePathNameReportOut);
		}
		LOGGER.info("ICodeSensor done!");
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
	 * @param file
	 *            input code file
	 * @return relative report file for this input code file
	 */
	protected String outReportFileName(InputFile file) {
		String reportOutExt = REPORT_EXT;
		return relativeReportFileName(file, reportOutExt);
	}

	/**
	 * @param file
	 *            input code file
	 * @param reportOutExt
	 *            report file extension
	 * @return relative report file for this input code file
	 */
	private String relativeReportFileName(InputFile file, String reportOutExt) {
		String separator = file.file().separator;
		String name = file.file().getName();
		return REPORT_SUBDIR + separator + name + reportOutExt;
	}

	/**
	 * Analyze a report file provided by the external tool ICode. Check the
	 * report file integrity (exist, not empty and readable)
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param fileRelativePathNameReportOut
	 *            name of the expected report file for this input code file
	 */
	private void analyseReportOut(SensorContext context, InputFile file, String fileRelativePathNameReportOut) {
		ReportInterface report = null;
		StringBuffer warningMsgs = new StringBuffer();
		int nbWarningMsgs = 0;
		StringBuffer errorMsgs = new StringBuffer();
		int nbErrorMsgs = 0;
		LOGGER.debug("file.absolutePath():" + file.absolutePath());
		LOGGER.debug("Paths.get(file.absolutePath()).getParent():" + Paths.get(file.absolutePath()).getParent());
		LOGGER.debug("fileRelativePathNameReportOut:" + fileRelativePathNameReportOut);
		Path fileReportPath = Paths.get(file.absolutePath()).getParent().resolve(fileRelativePathNameReportOut);
		if (existReportFile(fileReportPath)) {

			try {
				FileChannel reportFile = FileChannel.open(fileReportPath);
				report = XmlReportReader.parse(fileReportPath);
				long reportFileSize = reportFile.size();
				if (reportFileSize > 0) {
					errorMsgs.append("Empty report file : " + fileRelativePathNameReportOut);
					nbErrorMsgs++;
				}
				if (report == null) {
					errorMsgs.append("Report file : " + fileRelativePathNameReportOut + " cannot be analysed !");
					nbErrorMsgs++;
				}
			} catch (IOException e) {
				errorMsgs.append("Unexpected error report file for : " + fileRelativePathNameReportOut);
				nbErrorMsgs++;
			}
		} else {
			errorMsgs.append("No report file for : " + fileRelativePathNameReportOut);
			nbErrorMsgs++;
		}
		// Add a ICode report warning
		if (nbWarningMsgs > 0) {
			context.<String>newMeasure().forMetric(ICodeMetrics.REPORT_FILES_WARNING).on(file)
					.withValue(warningMsgs.toString()).save();
			context.<Integer>newMeasure().forMetric(ICodeMetrics.NUMBER_OF_WARNINGS).on(file).withValue(nbWarningMsgs)
					.save();
		}
		// Add a ICode report error
		if (nbErrorMsgs > 0) {
			context.<String>newMeasure().forMetric(ICodeMetrics.REPORT_FILES_ERROR).on(file)
					.withValue(errorMsgs.toString()).save();
			context.<Integer>newMeasure().forMetric(ICodeMetrics.NUMBER_OF_ERRORS).on(file).withValue(nbErrorMsgs)
					.save();
		}
		if (report != null) {
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
		parseReportCyclomaticMeasures(context, file, report);
		parseReportLinesOfCodeMeasures(context, file, report);
		parseReportRatioCommentMeasures(context, file, report);
		parseReportNestingMeasures(context, file, report);
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
	private void parseReportCyclomaticMeasures(SensorContext context, InputFile file, ReportInterface report) {
		// Add metrics results
		ReportModuleRuleInterface reportModuleRuleInterface = report.getModuleCyclomaticMeasure();
		ReportFunctionRuleInterface[] reportModuleRuleInterfaces = report.getCyclomaticMeasureByFunction();

		// Cyclomatic variables.
		double cyclomaticValueSum = 0;
		double cyclomaticValueMin = Double.MAX_VALUE;
		double cyclomaticValueMax = 0;

		// Create module measures (from each function measures provided by
		// ICode)
		if (reportModuleRuleInterfaces != null) {

			// Read measure value for each elements of this module
			for (ReportFunctionRuleInterface currentFunctionRuleInterface : reportModuleRuleInterfaces) {
				try {
					double currentValue = Double.valueOf(currentFunctionRuleInterface.getValue());

					// Sum all elements values for mean computation
					cyclomaticValueSum += currentValue;

					// Search maximum
					if (currentValue > cyclomaticValueMax) {
						cyclomaticValueMax = currentValue;
					}

					// Search minimum
					if (currentValue < cyclomaticValueMin) {
						cyclomaticValueMin = currentValue;
					}

				} catch (Exception e) {
					LOGGER.error("No cyclomatic measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double cyclomaticValueMean = (reportModuleRuleInterfaces.length > 0)
					? (cyclomaticValueSum / reportModuleRuleInterfaces.length) : cyclomaticValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			double cyclomaticValue = Double.parseDouble(reportModuleRuleInterface.getValue());

			// Cyclomatic
			if ("NaN".equals(cyclomaticValue)) {
				cyclomaticValue = ((int) cyclomaticValueSum);
			} else {
				LOGGER.warn("ICode define complexity simplified by module");
			}

			if (report.isF77()) {
				// Cyclomatic
				storeCyclomaticMeasuresF77(context, file, cyclomaticValueMin, cyclomaticValueMax, cyclomaticValueMean,
						cyclomaticValue);
			} else if (report.isF90()) {
				// Cyclomatic
				storeCyclomaticMeasuresF90(context, file, cyclomaticValueMin, cyclomaticValueMax, cyclomaticValueMean,
						cyclomaticValue);
			} else {
				// Cyclomatic
				storeCyclomaticMeasuresSHELL(context, file, cyclomaticValueMin, cyclomaticValueMax, cyclomaticValueMean,
						cyclomaticValue);
			}
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
	private void parseReportLinesOfCodeMeasures(SensorContext context, InputFile file, ReportInterface report) {
		// Add metrics results
		ReportModuleRuleInterface reportModuleRuleInterface = report.getModuleLinesOfCodeMeasure();
		ReportFunctionRuleInterface[] reportModuleRuleInterfaces = report.getLinesOfCodeMeasureByFunction();

		// Lines of code variables.
		double linesOfCodeValueSum = 0;
		double linesOfCodeValueMin = Double.MAX_VALUE;
		double linesOfCodeValueMax = 0;

		// Create module measures (from each function measures provided by
		// ICode)
		if (reportModuleRuleInterfaces != null) {

			// Read measure value for each elements of this module
			for (ReportFunctionRuleInterface currentFunctionRuleInterface : reportModuleRuleInterfaces) {
				try {
					double currentValue = Double.valueOf(currentFunctionRuleInterface.getValue());

					// Sum all elements values for mean computation
					linesOfCodeValueSum += currentValue;

					// Search maximum
					if (currentValue > linesOfCodeValueMax) {
						linesOfCodeValueMax = currentValue;
					}

					// Search minimum
					if (currentValue < linesOfCodeValueMin) {
						linesOfCodeValueMin = currentValue;
					}

				} catch (Exception e) {
					LOGGER.error("No lines of code measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double linesOfCodeValueMean = (reportModuleRuleInterfaces.length > 0)
					? (linesOfCodeValueSum / reportModuleRuleInterfaces.length) : linesOfCodeValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			double linesOfCodeValue = Double.parseDouble(reportModuleRuleInterface.getValue());

			if ("NaN".equals(linesOfCodeValue)) {
				linesOfCodeValue = linesOfCodeValueSum;
			} else {
				LOGGER.warn("ICode define lines of code simplified by module");
			}

			if (report.isF77()) {
				storeLOCMeasuresF77(context, file, linesOfCodeValueMin, linesOfCodeValueMax, linesOfCodeValueMean,
						linesOfCodeValue);
			} else if (report.isF90()) {
				storeLOCMeasuresF90(context, file, linesOfCodeValueMin, linesOfCodeValueMax, linesOfCodeValueMean,
						linesOfCodeValue);
			} else {
				storeLOCMeasuresSHELL(context, file, linesOfCodeValueMin, linesOfCodeValueMax, linesOfCodeValueMean,
						linesOfCodeValue);
			}
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
	
	private void parseReportRatioCommentMeasures(SensorContext context, InputFile file, ReportInterface report) {
		// Add metrics results
		ReportModuleRuleInterface reportModuleRuleInterface = report.getModuleRatioCommentMeasure();
		ReportFunctionRuleInterface[] reportModuleRuleInterfaces = report.getRatioCommentMeasureByFunction();

		// Lines of code variables.
		double ratioCommentValueSum = 0;
		double ratioCommentValueMin = Double.MAX_VALUE;
		double ratioCommentValueMax = 0;

		// Create module measures (from each function measures provided by
		// ICode)
		if (reportModuleRuleInterfaces != null) {

			// Read measure value for each elements of this module
			for (ReportFunctionRuleInterface currentFunctionRuleInterface : reportModuleRuleInterfaces) {
				try {
					double currentValue = Double.valueOf(currentFunctionRuleInterface.getValue());

					// Sum all elements values for mean computation
					ratioCommentValueSum += currentValue;

					// Search maximum
					if (currentValue > ratioCommentValueMax) {
						ratioCommentValueMax = currentValue;
					}

					// Search minimum
					if (currentValue < ratioCommentValueMin) {
						ratioCommentValueMin = currentValue;
					}

				} catch (Exception e) {
					LOGGER.error("No ratio comment measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double ratioCommentValueMean = (reportModuleRuleInterfaces.length > 0)
					? (ratioCommentValueSum / reportModuleRuleInterfaces.length) : ratioCommentValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			String ratioCommentValue = reportModuleRuleInterface.getValue();
			
			if ("NaN".equals(ratioCommentValue)) {
				ratioCommentValue = (""+ratioCommentValueSum);
			} else {
				LOGGER.warn("ICode define lines of code simplified by module");
			}
			
			double ratioCommentNewValue = Double.parseDouble(ratioCommentValue);
			
			if (report.isF77()) {
				storeRatioCommentMeasuresF77(context, file, ratioCommentValueMin, ratioCommentValueMax, ratioCommentValueMean,
						ratioCommentNewValue);
			} else if (report.isF90()) {
				storeRatioCommentMeasuresF90(context, file, ratioCommentValueMin, ratioCommentValueMax, ratioCommentValueMean,
						ratioCommentNewValue);
			} else {
				storeRatioCommentMeasuresSHELL(context, file, ratioCommentValueMin, ratioCommentValueMax, ratioCommentValueMean,
						ratioCommentNewValue);
			}
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
	private void parseReportNestingMeasures(SensorContext context, InputFile file, ReportInterface report) {
		// Add metrics results
		ReportModuleRuleInterface reportModuleRuleInterface = report.getModuleNestingMeasure();
		ReportFunctionRuleInterface[] reportModuleRuleInterfaces = report.getNestingMeasureByFunction();

		// Lines of code variables.
		double nestingValueSum = 0;
		double nestingValueMin = Double.MAX_VALUE;
		double nestingValueMax = 0;

		// Create module measures (from each function measures provided by
		// ICode)
		if (reportModuleRuleInterfaces != null) {

			// Read measure value for each elements of this module
			for (ReportFunctionRuleInterface currentFunctionRuleInterface : reportModuleRuleInterfaces) {
				try {
					double currentValue = Double.valueOf(currentFunctionRuleInterface.getValue());

					// Sum all elements values for mean computation
					nestingValueSum += currentValue;

					// Search maximum
					if (currentValue > nestingValueMax) {
						nestingValueMax = currentValue;
					}

					// Search minimum
					if (currentValue < nestingValueMin) {
						nestingValueMin = currentValue;
					}

				} catch (Exception e) {
					LOGGER.error("No nesting measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double nestingValueMean = (reportModuleRuleInterfaces.length > 0)
					? (nestingValueSum / reportModuleRuleInterfaces.length) : nestingValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			double nestingValue = Double.parseDouble(reportModuleRuleInterface.getValue());

			if ("NaN".equals(nestingValue)) {
				nestingValue = ((int) nestingValueSum);
			} else {
				LOGGER.warn("ICode define lines of code simplified by module");
			}

			if (report.isF77()) {
				storeNestingMeasuresF77(context, file, nestingValueMin, nestingValueMax, nestingValueMean,
						nestingValue);
			} else if (report.isF90()) {
				storeNestingMeasuresF90(context, file, nestingValueMin, nestingValueMax, nestingValueMean,
						nestingValue);
			} else {
				storeNestingMeasuresSHELL(context, file, nestingValueMin, nestingValueMax, nestingValueMean,
						nestingValue);
			}
		}
	}

	/**
	 * Store measures from a valid report file into F77 Metrics.
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param cyclomaticValueMin
	 *            minimum
	 * @param cyclomaticValueMax
	 *            maximum
	 * @param cyclomaticValueMean
	 *            mean
	 * @param value
	 *            cyclomatic complexity value
	 * 
	 * @see ICodeMetrics
	 */
	private void storeCyclomaticMeasuresF77(SensorContext context, InputFile file, double cyclomaticValueMin,
			double cyclomaticValueMax, double cyclomaticValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Cyclomatic.F77_CYCLOMATIC).on(file)
				.withValue(Integer.valueOf((int) value)).save();
		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Cyclomatic.F77_CYCLOMATIC_MAX).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValueMax)).save();
		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Cyclomatic.F77_CYCLOMATIC_MIN).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValueMin)).save();
		context.<Double>newMeasure().forMetric(ICodeMetricsF77Cyclomatic.F77_CYCLOMATIC_MEAN).on(file)
				.withValue(Double.valueOf(cyclomaticValueMean)).save();
	}

	/**
	 * Store measures from a valid report file into SHELL Metrics.
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param cyclomaticValueMin
	 *            minimum
	 * @param cyclomaticValueMax
	 *            maximum
	 * @param cyclomaticValueMean
	 *            mean
	 * @param cyclomaticValue
	 *            complexity value
	 * 
	 * @see ICodeMetrics
	 */
	private void storeCyclomaticMeasuresSHELL(SensorContext context, InputFile file, double cyclomaticValueMin,
			double cyclomaticValueMax, double cyclomaticValueMean, double cyclomaticValue) {
		// TODO Auto-generated method stub
		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLCyclomatic.SHELL_CYCLOMATIC).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValue)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLCyclomatic.SHELL_CYCLOMATIC_MAX).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLCyclomatic.SHELL_CYCLOMATIC_MIN).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLCyclomatic.SHELL_CYCLOMATIC_MEAN).on(file)
				.withValue(Double.valueOf(cyclomaticValueMean)).save();

	}

	/**
	 * Store measures from a valid report file into F90 Metrics.
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param cyclomaticValueMin
	 *            minimum
	 * @param cyclomaticValueMax
	 *            maximum
	 * @param cyclomaticValueMean
	 *            mean
	 * @param cyclomaticValue
	 *            complexity value
	 * 
	 * @see ICodeMetrics
	 */
	private void storeCyclomaticMeasuresF90(SensorContext context, InputFile file, double cyclomaticValueMin,
			double cyclomaticValueMax, double cyclomaticValueMean, double cyclomaticValue) {
		// TODO Auto-generated method stub

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Cyclomatic.F90_CYCLOMATIC).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValue)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Cyclomatic.F90_CYCLOMATIC_MAX).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Cyclomatic.F90_CYCLOMATIC_MIN).on(file)
				.withValue(Integer.valueOf((int) cyclomaticValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90Cyclomatic.F90_CYCLOMATIC_MEAN).on(file)
				.withValue(Double.valueOf(cyclomaticValueMean)).save();
	}
	
	
	
	
	/**
	 * Store measures from a valid report file into F77 Metrics.
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param cyclomaticValueMin
	 *            minimum
	 * @param cyclomaticValueMax
	 *            maximum
	 * @param cyclomaticValueMean
	 *            mean
	 * @param value
	 *            cyclomatic complexity value
	 * 
	 * @see ICodeMetrics
	 */
	
	private void storeRatioCommentMeasuresF77(SensorContext context, InputFile file, double ratioCommentValueMin,
			double ratioCommentValueMax, double ratioCommentValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		LOGGER.warn("F77_RATIO_COMMENT: " + value);
		LOGGER.warn("F77_RATIO_COMMENT_MAX: " + ratioCommentValueMax);
		LOGGER.warn("F77_RATIO_COMMENT_MIN: " + ratioCommentValueMin);
		LOGGER.warn("F77_RATIO_COMMENT_MEAN: " + value);
		context.<Double>newMeasure().forMetric(ICodeMetricsF77RatioComment.F77_RATIO_COMMENT).on(file)
				.withValue(Double.valueOf( value)).save();
		context.<Double>newMeasure().forMetric(ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MAX).on(file)
				.withValue(Double.valueOf( ratioCommentValueMax)).save();
		context.<Double>newMeasure().forMetric(ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MIN).on(file)
				.withValue(Double.valueOf( ratioCommentValueMin)).save();
		context.<Double>newMeasure().forMetric(ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MEAN).on(file)
				.withValue(Double.valueOf(ratioCommentValueMean)).save();
	}

	/**
	 * Store measures from a valid report file into SHELL Metrics.
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param cyclomaticValueMin
	 *            minimum
	 * @param cyclomaticValueMax
	 *            maximum
	 * @param cyclomaticValueMean
	 *            mean
	 * @param cyclomaticValue
	 *            complexity value
	 * 
	 * @see ICodeMetrics
	 */
	
	private void storeRatioCommentMeasuresSHELL(SensorContext context, InputFile file, double ratioCommentValueMin,
			double ratioCommentValueMax, double ratioCommentValueMean, double value) {
		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT).on(file)
				.withValue(Double.valueOf(value)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT_MAX).on(file)
				.withValue(Double.valueOf(ratioCommentValueMax)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT_MIN).on(file)
				.withValue(Double.valueOf(ratioCommentValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT_MEAN).on(file)
				.withValue(Double.valueOf(ratioCommentValueMean)).save();

	}

	/**
	 * Store measures from a valid report file into F90 Metrics.
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            input code file
	 * @param cyclomaticValueMin
	 *            minimum
	 * @param cyclomaticValueMax
	 *            maximum
	 * @param cyclomaticValueMean
	 *            mean
	 * @param cyclomaticValue
	 *            complexity value
	 * 
	 * @see ICodeMetrics
	 */
	
	private void storeRatioCommentMeasuresF90(SensorContext context, InputFile file, double ratioCommentValueMin,
			double ratioCommentValueMax, double ratioCommentValueMean, double value) {
		// TODO Auto-generated method stub

		context.<Double>newMeasure().forMetric(ICodeMetricsF90RatioComment.F90_RATIO_COMMENT).on(file)
				.withValue(Double.valueOf(value)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90RatioComment.F90_RATIO_COMMENT_MAX).on(file)
				.withValue(Double.valueOf(ratioCommentValueMax)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90RatioComment.F90_RATIO_COMMENT_MIN).on(file)
				.withValue(Double.valueOf(ratioCommentValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90RatioComment.F90_RATIO_COMMENT_MEAN).on(file)
				.withValue(Double.valueOf(ratioCommentValueMean)).save();
	}
	
	
	
	

	/**
	 * 
	 * @param context
	 * @param file
	 * @param linesOfCodeValueMin
	 * @param linesOfCodeValueMax
	 * @param linesOfCodeValueMean
	 * @param value
	 */
	private void storeLOCMeasuresF77(SensorContext context, InputFile file, double linesOfCodeValueMin,
			double linesOfCodeValueMax, double linesOfCodeValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF77LinesOfCode.F77_LOC).on(file)
				.withValue(Integer.valueOf((int) value)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF77LinesOfCode.F77_LOC_MAX).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF77LinesOfCode.F77_LOC_MIN).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF77LinesOfCode.F77_LOC_MEAN).on(file)
				.withValue(Double.valueOf(linesOfCodeValueMean)).save();
	}

	/**
	 * 
	 * @param context
	 * @param file
	 * @param nestingValueMin
	 * @param nestingValueMax
	 * @param nestingValueMean
	 * @param value
	 */
	private void storeNestingMeasuresF77(SensorContext context, InputFile file, double nestingValueMin,
			double nestingValueMax, double nestingValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING).on(file)
		.withValue(Integer.valueOf((int)value))
				.save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING_MAX).on(file)
				.withValue(Integer.valueOf((int) nestingValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING_MIN).on(file)
				.withValue(Integer.valueOf((int) nestingValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING_MEAN).on(file)
				.withValue(Double.valueOf(nestingValueMean)).save();
	}

	/**
	 * 
	 * @param context
	 * @param file
	 * @param nestingValueMin
	 * @param nestingValueMax
	 * @param nestingValueMean
	 * @param value
	 */
	private void storeNestingMeasuresF90(SensorContext context, InputFile file, double nestingValueMin,
			double nestingValueMax, double nestingValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING).on(file)
		.withValue(Integer.valueOf((int)value))
				.save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING_MAX).on(file)
				.withValue(Integer.valueOf((int) nestingValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING_MIN).on(file)
				.withValue(Integer.valueOf((int) nestingValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING_MEAN).on(file)
				.withValue(Double.valueOf(nestingValueMean)).save();
	}

	/**
	 * 
	 * @param context
	 * @param file
	 * @param nestingValueMin
	 * @param nestingValueMax
	 * @param nestingValueMean
	 * @param value
	 */
	private void storeNestingMeasuresSHELL(SensorContext context, InputFile file, double nestingValueMin,
			double nestingValueMax, double nestingValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING).on(file)
		.withValue(Integer.valueOf((int)value))
				.save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING_MAX).on(file)
				.withValue(Integer.valueOf((int) nestingValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING_MIN).on(file)
				.withValue(Integer.valueOf((int) nestingValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING_MEAN).on(file)
				.withValue(Double.valueOf(nestingValueMean)).save();
	}

	/**
	 * 
	 * @param context
	 * @param file
	 * @param linesOfCodeValueMin
	 * @param linesOfCodeValueMax
	 * @param linesOfCodeValueMean
	 * @param value
	 */
	private void storeLOCMeasuresF90(SensorContext context, InputFile file, double linesOfCodeValueMin,
			double linesOfCodeValueMax, double linesOfCodeValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC).on(file).withValue(Integer.valueOf((int) value))
				.save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC_MAX).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC_MIN).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC_MEAN).on(file)
				.withValue(Double.valueOf(linesOfCodeValueMean)).save();
	}

	/**
	 * 
	 * @param context
	 * @param file
	 * @param linesOfCodeValueMin
	 * @param linesOfCodeValueMax
	 * @param linesOfCodeValueMean
	 * @param value
	 */
	private void storeLOCMeasuresSHELL(SensorContext context, InputFile file, double linesOfCodeValueMin,
			double linesOfCodeValueMax, double linesOfCodeValueMean, double value) {
		// Store module CYCLOMATIC, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLLinesOfCode.SHELL_LOC).on(file).withValue(Integer.valueOf((int) value))
				.save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLLinesOfCode.SHELL_LOC_MAX).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLLinesOfCode.SHELL_LOC_MIN).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLLinesOfCode.SHELL_LOC_MEAN).on(file)
				.withValue(Double.valueOf(linesOfCodeValueMean)).save();
	}

	/**
	 * 
	 * @param context
	 * @param file
	 * @param report
	 */
	private void parseReportIssues(SensorContext context, InputFile file, ReportInterface report) {
		// Read all report issues
		ReportModuleRuleInterface reportModuleRuleInterface = report.getModuleCyclomaticMeasure();
		ReportFunctionRuleInterface[] reportModuleRuleInterfaces = report.getCyclomaticMeasureByFunction();

		// Create issues for this file
		if (reportModuleRuleInterface != null) {
			InputFile inputFile = file;
			int lines = inputFile.lines();

			// Read measure value for each elements of this module
			for (ReportFunctionRuleInterface currentFunctionRuleInterface : reportModuleRuleInterfaces) {
				String line = currentFunctionRuleInterface.getLine();
				int lineNr = getLineAsInt(line, lines);
				// RuleKey ruleKey = ICodeRulesDefinition.RULE_CYCLO;//TODO: TBD
				// NewIssue newIssue = context.newIssue().forRule(ruleKey);
				// NewIssueLocation location = newIssue.newLocation()
				// .on(inputFile)
				// .at(inputFile.selectLine(lineNr > 0 ? lineNr : 1))
				// .message(currentFunctionRuleInterface.getValue());
				//
				// newIssue.at(location);
				// newIssue.save();
				// violationsCount++;//TODO: TBD count number of issues
			}
		}
	}

	/**
	 * Format line number according to the sonar expected format for issue
	 * 
	 * @param line
	 *            string value of a line
	 * @param maxLine
	 *            file line numbers
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
		boolean res = false;
		LOGGER.debug("existFile ?:" + fileReportPath.toAbsolutePath());
		res = Files.exists(fileReportPath, LinkOption.NOFOLLOW_LINKS);
		return res;
	}

}
