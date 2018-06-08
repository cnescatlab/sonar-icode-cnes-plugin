/*
	 * This file is part of sonar-icode-cnes-plugin.
	 *
	 * sonar-icode-cnes-plugin is free software: you can redistribute it and/or modify
	 * it under the terms of the GNU General Public License as published by
	 * the Free Software Foundation, either version 3 of the License, or
	 * (at your option) any later version.
	 *
	 * sonar-icode-cnes-plugin is distributed in the hope that it will be useful,
	 * but WITHOUT ANY WARRANTY; without even the implied warranty of
	 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	 * GNU General Public License for more details.
	 *
	 * You should have received a copy of the GNU General Public License
	 * along with sonar-icode-cnes-plugin.  If not, see <http://www.gnu.org/licenses/>.

*/
package fr.cnes.sonar.plugins.icode.measures;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.cnes.sonar.plugins.icode.report.XmlReportReader;
import fr.cnes.sonar.plugins.icode.settings.ICodeLanguageProperties;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.cnes.sonar.plugins.icode.report.ErrorInterface;
import fr.cnes.sonar.plugins.icode.report.ReportFunctionRuleInterface;
import fr.cnes.sonar.plugins.icode.report.ReportInterface;
import fr.cnes.sonar.plugins.icode.report.ReportModuleRuleInterface;
import fr.cnes.sonar.plugins.icode.rules.ICodeRulesDefinition;

/**
 * Scan ICode report file. For all project code file : <b>FILE</b>, ICode create
 * a report file <b>FILE{@link ICodeSensor#REPORT_EXT}</b> into the
 * {@link ICodeSensor#REPORT_SUBDIR} shall be
 * 
 * @author Cyrille FRANCOIS
 */
public class ICodeSensor implements Sensor {

	private static final String I_CODE_DEFINE_LINES_OF_CODE_SIMPLIFIED_BY_MODULE = "ICode define lines of code simplified by module";

	private static final Logger LOGGER = Loggers.get(ICodeSensor.class);

	String expectedReportInputFileTypes = null;

	String reportOutExt = null;

	String reportSubdir = null;
	
	@Override
	public void describe(SensorDescriptor descriptor) {
		descriptor.name(getClass().getName());
	}

	@Override
	public void execute(SensorContext context) {
		LOGGER.info("ICodeSensor is running...");
		FileSystem fs = context.fileSystem();
		LOGGER.info("ICodeSensor : file system base dir = " + fs.baseDir());
		FilePredicates p = fs.predicates();
		LOGGER.info("ICodeSensor : file system base dir = " + fs.hasFiles(p.all()));
		
		readPluginSettings(context);		

		// only "main" files, but not "tests"
		String[] aMatchingPatterns = matchingPatterns();
		Iterable<InputFile> filesF = fs.inputFiles(fs.predicates().matchesPathPatterns(aMatchingPatterns));
		for (InputFile file : filesF) {
			LOGGER.debug("ICodeSensor : current input file = " + file.absolutePath());

			// Check for report out
			String fileRelativePathNameReportOut = outReportFileName(file);

			// Analyse report out
			analyseReportOut(context, file, fileRelativePathNameReportOut);
		}
		LOGGER.info("ICodeSensor done!");
	}

	void readPluginSettings(SensorContext context) {
		// Read Plugin settings
		expectedReportInputFileTypes = context.settings().getString(ICodeLanguageProperties.EXPECTED_REPORT_INPUT_FILE_TYPES_KEY);
		reportOutExt = context.settings().getString(ICodeLanguageProperties.REPORT_OUT_EXT_KEY);
		reportSubdir = context.settings().getString(ICodeLanguageProperties.REPORT_SUBDIR_KEY);
	}

	/**
	 * @return all expected file code patterns
	 */
	private String[] matchingPatterns() {
		String patternSeparator = ICodeLanguageProperties.FILE_SUFFIXES_SEPARATOR;
		return expectedReportInputFileTypes.trim().split(patternSeparator);
	}

	/**
	 * @param file
	 *            input code file
	 * @return relative report file for this input code file
	 */
	protected String outReportFileName(InputFile file) {
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
		String separator = File.separator;
		@SuppressWarnings("deprecation")
		String name = file.file().getName();
		return reportSubdir + separator + name + reportOutExt;
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
	private void analyseReportOut(
			SensorContext context,
			InputFile file,
			String fileRelativePathNameReportOut) {
		ReportInterface report = null;
		StringBuilder warningMsgs = new StringBuilder();
		int nbWarningMsgs = 0;
		StringBuilder errorMsgs = new StringBuilder();
		int nbErrorMsgs = 0;
		LOGGER.debug("file.absolutePath():" + file.absolutePath());
		LOGGER.debug("Paths.get(file.absolutePath()).getParent():" + Paths.get(file.absolutePath()).getParent());
		LOGGER.debug("fileRelativePathNameReportOut:" + fileRelativePathNameReportOut);
		Path fileReportPath = Paths.get(file.absolutePath()).getParent().resolve(fileRelativePathNameReportOut);
		if (existReportFile(fileReportPath)) {

			try (FileChannel reportFile = FileChannel.open(fileReportPath)){
				report = XmlReportReader.parse(fileReportPath);
				long reportFileSize = reportFile.size();
				if (reportFileSize == 0) {
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
		computeWarnings(context, file, warningMsgs, nbWarningMsgs);
		// Add a ICode report error
		computeErrors(context, file, errorMsgs, nbErrorMsgs);
		if (report != null) {
			parseReportMeasures(context, file, report);
			parseReportIssues(context, file, report);
		}
	}

	private void computeErrors(SensorContext context, InputFile file, StringBuilder errorMsgs, int nbErrorMsgs) {
		if (nbErrorMsgs > 0) {
			context.<String>newMeasure()
				.forMetric(ICodeMetrics.REPORT_FILES_ERROR)
				.on(file)
				.withValue(errorMsgs.toString())
				.save();
			context.<Integer>newMeasure()
				.forMetric(ICodeMetrics.NUMBER_OF_ERRORS)
				.on(file)
				.withValue(nbErrorMsgs)
				.save();
		}
	}

	private void computeWarnings(SensorContext context, InputFile file, StringBuilder warningMsgs, int nbWarningMsgs) {
		if (nbWarningMsgs > 0) {
			context.<String>newMeasure()
				.forMetric(ICodeMetrics.REPORT_FILES_WARNING)
				.on(file)
				.withValue(warningMsgs.toString())
				.save();
			context.<Integer>newMeasure()
				.forMetric(ICodeMetrics.NUMBER_OF_WARNINGS)
				.on(file)
				.withValue(nbWarningMsgs)
				.save();
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
					double currentValue = Double.parseDouble(currentFunctionRuleInterface.getValue());

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
					LOGGER.error(e.getMessage());
					LOGGER.error("No cyclomatic measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double cyclomaticValueMean = (reportModuleRuleInterfaces.length > 0)
					? (cyclomaticValueSum / reportModuleRuleInterfaces.length) : cyclomaticValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			double cyclomaticValue = Double.NaN;
			cyclomaticValue = computeCyclomaticValue(reportModuleRuleInterface, cyclomaticValueSum, cyclomaticValue);

			storeCyclomaticMeasures(context, file, report, cyclomaticValueMin, cyclomaticValueMax, cyclomaticValueMean,
					cyclomaticValue);
		}
	}

	private double computeCyclomaticValue(ReportModuleRuleInterface reportModuleRuleInterface,
			double cyclomaticValueSum, double cyclomaticValue) {
		if(reportModuleRuleInterface != null && reportModuleRuleInterface.getValue() != null){
			cyclomaticValue = Double.parseDouble(reportModuleRuleInterface.getValue());
		}

		// Cyclomatic
		if (cyclomaticValue == Double.NaN) {
			cyclomaticValue = ((int) cyclomaticValueSum);
		} else {
			LOGGER.warn("ICode define complexity simplified by module");
		}
		return cyclomaticValue;
	}

	private void storeCyclomaticMeasures(SensorContext context, InputFile file, ReportInterface report,
			double cyclomaticValueMin, double cyclomaticValueMax, double cyclomaticValueMean, double cyclomaticValue) {
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
					double currentValue = Double.parseDouble(currentFunctionRuleInterface.getValue());

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
					LOGGER.error(e.getMessage());
					LOGGER.error("No lines of code measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double linesOfCodeValueMean = (reportModuleRuleInterfaces.length > 0)
					? (linesOfCodeValueSum / reportModuleRuleInterfaces.length) : linesOfCodeValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			double linesOfCodeValue = Double.NaN;
			linesOfCodeValue = computeLinesOfCodeValue(reportModuleRuleInterface, linesOfCodeValueSum,
					linesOfCodeValue);

			storeLOCMeasures(context, file, report, linesOfCodeValueMin, linesOfCodeValueMax, linesOfCodeValueMean,
					linesOfCodeValue);
		}
	}

	private double computeLinesOfCodeValue(ReportModuleRuleInterface reportModuleRuleInterface,
			double linesOfCodeValueSum, double linesOfCodeValue) {
		if(reportModuleRuleInterface != null && reportModuleRuleInterface.getValue() != null){
			linesOfCodeValue = Double.parseDouble(reportModuleRuleInterface.getValue());
		}
		
		if (linesOfCodeValue == Double.NaN) {
			linesOfCodeValue = linesOfCodeValueSum;
		} else {
			LOGGER.warn(I_CODE_DEFINE_LINES_OF_CODE_SIMPLIFIED_BY_MODULE);
		}
		return linesOfCodeValue;
	}

	private void storeLOCMeasures(SensorContext context, InputFile file, ReportInterface report,
			double linesOfCodeValueMin, double linesOfCodeValueMax, double linesOfCodeValueMean,
			double linesOfCodeValue) {
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
					double currentValue = Double.parseDouble(currentFunctionRuleInterface.getValue());

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
					LOGGER.error(e.getMessage());
					LOGGER.error("No ratio comment measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double ratioCommentValueMean = (reportModuleRuleInterfaces.length > 0)
					? (ratioCommentValueSum / reportModuleRuleInterfaces.length) : ratioCommentValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			String ratioCommentValue = "NaN";
			ratioCommentValue = computeRatioCommentValue(reportModuleRuleInterface, ratioCommentValueSum,
					ratioCommentValue);

			double ratioCommentNewValue = Double.parseDouble(ratioCommentValue);

			storeRatioCommentMeasures(context, file, report, ratioCommentValueMin, ratioCommentValueMax,
					ratioCommentValueMean, ratioCommentNewValue);
		}
	}

	private String computeRatioCommentValue(ReportModuleRuleInterface reportModuleRuleInterface,
			double ratioCommentValueSum, String ratioCommentValue) {
		if(reportModuleRuleInterface != null && reportModuleRuleInterface.getValue() != null){
			ratioCommentValue = reportModuleRuleInterface.getValue();
		}
		
		if ("NaN".equals(ratioCommentValue)) {
			ratioCommentValue = ("" + ratioCommentValueSum);
		} else {
			LOGGER.warn(I_CODE_DEFINE_LINES_OF_CODE_SIMPLIFIED_BY_MODULE);
		}
		return ratioCommentValue;
	}

	private void storeRatioCommentMeasures(SensorContext context, InputFile file, ReportInterface report,
			double ratioCommentValueMin, double ratioCommentValueMax, double ratioCommentValueMean,
			double ratioCommentNewValue) {
		if (report.isF77()) {
			storeRatioCommentMeasuresF77(context, file, ratioCommentValueMin, ratioCommentValueMax,
					ratioCommentValueMean, ratioCommentNewValue);
		} else if (report.isF90()) {
			storeRatioCommentMeasuresF90(context, file, ratioCommentValueMin, ratioCommentValueMax,
					ratioCommentValueMean, ratioCommentNewValue);
		} else {
			storeRatioCommentMeasuresSHELL(context, file, ratioCommentValueMin, ratioCommentValueMax,
					ratioCommentValueMean, ratioCommentNewValue);
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
					double currentValue = Double.parseDouble(currentFunctionRuleInterface.getValue());

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
					LOGGER.error(e.getMessage());
					LOGGER.error("No nesting measure: " + currentFunctionRuleInterface.getValue());
				}
			}

			// Create measure for this file
			double nestingValueMean = (reportModuleRuleInterfaces.length > 0)
					? (nestingValueSum / reportModuleRuleInterfaces.length) : nestingValueSum;

			// Complexity simplified store by module is not defined by ICode,
			// but ICode sonar plugin expected a module measure...
			double nestingValue = Double.NaN;
			nestingValue = computeNestingValue(reportModuleRuleInterface, nestingValueSum, nestingValue);

			storeNestingMeasures(context, file, report, nestingValueMin, nestingValueMax, nestingValueMean,
					nestingValue);
		}
	}

	private double computeNestingValue(ReportModuleRuleInterface reportModuleRuleInterface, double nestingValueSum,
			double nestingValue) {
		if(reportModuleRuleInterface != null && reportModuleRuleInterface.getValue() != null){
			nestingValue = Double.parseDouble(reportModuleRuleInterface.getValue());
		}

		if (nestingValue == Double.NaN) {
			nestingValue = ((int) nestingValueSum);
		} else {
			LOGGER.warn(I_CODE_DEFINE_LINES_OF_CODE_SIMPLIFIED_BY_MODULE);
		}
		return nestingValue;
	}

	private void storeNestingMeasures(SensorContext context, InputFile file, ReportInterface report,
			double nestingValueMin, double nestingValueMax, double nestingValueMean, double nestingValue) {
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
		// Store module VALUE, MEAN, MIN, MAX
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
		context.<Double>newMeasure().forMetric(ICodeMetricsF77RatioComment.F77_RATIO_COMMENT).on(file)
				.withValue(Double.valueOf(value)).save();
		context.<Double>newMeasure().forMetric(ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MAX).on(file)
				.withValue(Double.valueOf(ratioCommentValueMax)).save();
		context.<Double>newMeasure().forMetric(ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MIN).on(file)
				.withValue(Double.valueOf(ratioCommentValueMin)).save();
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
	 * Store measures from a valid report file into F90 Metrics (Ratio Comment).
	 * 
	 * @param context
	 * @param file
	 * @param ratioCommentValueMin
	 * @param ratioCommentValueMax
	 * @param ratioCommentValueMean
	 * @param value
	 */
	private void storeRatioCommentMeasuresF90(SensorContext context, InputFile file, double ratioCommentValueMin,
			double ratioCommentValueMax, double ratioCommentValueMean, double value) {
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
	 * Store measures from a valid report file into F77 Metrics (Lines of Code).
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            Input code file
	 * @param linesOfCodeValueMin
	 *            Minimum
	 * @param linesOfCodeValueMax
	 *            Maximum
	 * @param linesOfCodeValueMean
	 *            Mean
	 * @param value
	 *            Value
	 */
	private void storeLOCMeasuresF77(SensorContext context, InputFile file, double linesOfCodeValueMin,
			double linesOfCodeValueMax, double linesOfCodeValueMean, double value) {
		// Store module VALUE, MEAN, MIN, MAX
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
	 * Store measures from a valid report file into F77 Metrics (Nesting).
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            Input code file
	 * @param nestingValueMin
	 *            Minimum
	 * @param nestingValueMax
	 *            Maximum
	 * @param nestingValueMean
	 *            Mean
	 * @param value
	 *            Value
	 */
	private void storeNestingMeasuresF77(SensorContext context, InputFile file, double nestingValueMin,
			double nestingValueMax, double nestingValueMean, double value) {
		// Store module VALUE, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING).on(file)
				.withValue(Integer.valueOf((int) value)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING_MAX).on(file)
				.withValue(Integer.valueOf((int) nestingValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING_MIN).on(file)
				.withValue(Integer.valueOf((int) nestingValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF77Nesting.F77_NESTING_MEAN).on(file)
				.withValue(Double.valueOf(nestingValueMean)).save();
	}

	/**
	 * Store measures from a valid report file into F90 Metrics (Nesting).
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            Input code file
	 * @param nestingValueMin
	 *            Minimum
	 * @param nestingValueMax
	 *            Maximum
	 * @param nestingValueMean
	 *            Mean
	 * @param value
	 *            Value
	 */
	private void storeNestingMeasuresF90(SensorContext context, InputFile file, double nestingValueMin,
			double nestingValueMax, double nestingValueMean, double value) {
		// Store module VALUE, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING).on(file)
				.withValue(Integer.valueOf((int) value)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING_MAX).on(file)
				.withValue(Integer.valueOf((int) nestingValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING_MIN).on(file)
				.withValue(Integer.valueOf((int) nestingValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90Nesting.F90_NESTING_MEAN).on(file)
				.withValue(Double.valueOf(nestingValueMean)).save();
	}

	/**
	 * Store measures from a valid report file into SHELL Metrics (Nesting).
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            Input code file
	 * @param nestingValueMin
	 *            Minimum
	 * @param nestingValueMax
	 *            Maximum
	 * @param nestingValueMean
	 *            Mean
	 * @param value
	 *            Value
	 */
	private void storeNestingMeasuresSHELL(SensorContext context, InputFile file, double nestingValueMin,
			double nestingValueMax, double nestingValueMean, double value) {
		// Store module VALUE, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING).on(file)
				.withValue(Integer.valueOf((int) value)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING_MAX).on(file)
				.withValue(Integer.valueOf((int) nestingValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING_MIN).on(file)
				.withValue(Integer.valueOf((int) nestingValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsSHELLNesting.SHELL_NESTING_MEAN).on(file)
				.withValue(Double.valueOf(nestingValueMean)).save();
	}

	/**
	 * Store measures from a valid report file into F90 Metrics (Lines Of Code).
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            Input code file
	 * @param linesOfCodeValueMin
	 *            Minimum
	 * @param linesOfCodeValueMax
	 *            Maximum
	 * @param linesOfCodeValueMean
	 *            Mean
	 * @param value
	 *            Value
	 */
	private void storeLOCMeasuresF90(SensorContext context, InputFile file, double linesOfCodeValueMin,
			double linesOfCodeValueMax, double linesOfCodeValueMean, double value) {
		// Store module VALUE, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC).on(file)
				.withValue(Integer.valueOf((int) value)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC_MAX).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMax)).save();

		context.<Integer>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC_MIN).on(file)
				.withValue(Integer.valueOf((int) linesOfCodeValueMin)).save();

		context.<Double>newMeasure().forMetric(ICodeMetricsF90LinesOfCode.F90_LOC_MEAN).on(file)
				.withValue(Double.valueOf(linesOfCodeValueMean)).save();
	}

	/**
	 * Store measures from a valid report file into SHELL Metrics (Lines Of
	 * Code).
	 * 
	 * @param context
	 *            Sonar sensor context
	 * @param file
	 *            Input code file
	 * @param linesOfCodeValueMin
	 *            Minimum
	 * @param linesOfCodeValueMax
	 *            Maximum
	 * @param linesOfCodeValueMean
	 *            Mean
	 * @param value
	 *            Value
	 */
	private void storeLOCMeasuresSHELL(SensorContext context, InputFile file, double linesOfCodeValueMin,
			double linesOfCodeValueMax, double linesOfCodeValueMean, double value) {
		// Store module VALUE, MEAN, MIN, MAX
		context.<Integer>newMeasure().forMetric(ICodeMetricsSHELLLinesOfCode.SHELL_LOC).on(file)
				.withValue(Integer.valueOf((int) value)).save();

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
	 *            Sonar sensor context
	 * @param file
	 *            Input code file
	 * @param report
	 */
	private void parseReportIssues(SensorContext context, InputFile file, ReportInterface report) {
		LOGGER.info("Parse and store report issues (doing...)");

		// Read all report issues
		ErrorInterface[] errors = report.getErrors();
		if (errors != null) {
			InputFile inputFile = file;
			for (ErrorInterface error : errors) {
				String lineString = error.getLineDescriptor();
				String message = error.getDescription();
				String externalRuleKey = error.getRuleKey();
				saveIssue(context, inputFile, lineString, externalRuleKey, message);
			}
		}
		LOGGER.info("Parse and store report issues (done)");
	}

	private void saveIssue(SensorContext context, InputFile inputFile, String lineString, String externalRuleKey,
			String message) {
		RuleKey ruleKey = RuleKey.of(ICodeRulesDefinition.getRepositoryKeyForLanguage(), externalRuleKey);

		LOGGER.info("externalRuleKey: " + externalRuleKey);
		LOGGER.info("Repo: " + ICodeRulesDefinition.getRepositoryKeyForLanguage());
		LOGGER.info("RuleKey: " + ruleKey);
		NewIssue newIssue = context.newIssue().forRule(ruleKey);

		NewIssueLocation primaryLocation = newIssue.newLocation().on(inputFile).message(message);

		int maxLine = inputFile.lines();
		int iLine = getLineAsInt(lineString, maxLine);
		if (iLine > 0) {
			primaryLocation.at(inputFile.selectLine(iLine));
		}
		newIssue.at(primaryLocation);

		newIssue.save();
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
	static int getLineAsInt(String line, int maxLine) {
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
		res = fileReportPath.toFile().exists();
		return res;
	}

}
