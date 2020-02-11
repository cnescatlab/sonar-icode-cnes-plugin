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

import fr.cnes.icode.data.CheckResult;
import fr.cnes.sonar.plugins.icode.model.AnalysisProject;
import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputComponent;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.measure.NewMeasure;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.util.*;

/**
 * Executed during sonar-scanner call.
 * Import i-Code metrics into SonarQube.
 */
public class ICodeMetricsProcessor {

    /** Logger for this class. **/
    private static final Logger LOGGER = Loggers.get(ICodeMetricsProcessor.class);

    /** Shared part between all i-Code metrics key. **/
    private static final String COMMON_METRICS_KEY_PART = ".MET.";

    /**
     * Private constructor because this class contains only static methods.
     */
    private ICodeMetricsProcessor(){}

    /**
     * Determine if an i-Code rule is a metric.
     *
     * @param ruleId Formatted like in i-Code.
     * @return True if it is a metric (contains .MET.).
     */
    public static boolean isMetric(final String ruleId) {
        return ruleId.contains(COMMON_METRICS_KEY_PART);
    }

    /**
     * Save a measure in a SQ or CNES metric.
     *
     * @param context Context of the analysis containing services.
     * @param files Map containing files in SQ format.
     * @param rule Measure considered like a rule in i-Code.
     */
    public static void saveMeasure(final SensorContext context, final Map<String, InputFile> files, final AnalysisRule rule) {

        // Create a new measure from the scan context.
        final NewMeasure<Integer> newMeasure = context.newMeasure();
        // i-Code rule id.
        final String metricKey = rule.getAnalysisRuleId();
        // Determine if a measure is relative to a file or a method.
        final String metricScope = rule.getResult().getResultTypePlace();
        // Component concerned by the measure.
        final String metricComponent = rule.getResult().getFileName();
        // Component concerned by the measure.
        final String measureValue = rule.getResult().getResultValue();

        // Is true if the metric must be interpreted by the plugin.
        boolean isCalculated = false;

        // Local attribute to set
        Metric<Integer> metric = null;
        InputComponent component = null;
        Integer value = null;
        if(metricScope.equals("class")) {
            // Take SHELL / F77 / F90 ncloc into account
            if (metricKey.contains("MET.LineOfCode")) {
                metric = CoreMetrics.NCLOC;
                component = files.getOrDefault(metricComponent, null);
                value = Double.valueOf(measureValue).intValue();
                isCalculated = true;
            }
            // Take SHELL / F77 / F90 number of comment lines into account
            else if (metricKey.contains("MET.LineOfComment")) {
                metric = CoreMetrics.COMMENT_LINES;
                component = files.getOrDefault(metricComponent, null);
                value = Double.valueOf(measureValue).intValue();
                isCalculated = true;
            }
            // Take SHELL complexity into account
            else if (metricKey.contains("SH.MET.ComplexitySimplified")) {
                metric = CoreMetrics.COMPLEXITY;
                component = files.getOrDefault(metricComponent, null);
                value = Double.valueOf(measureValue).intValue();
                isCalculated = true;
            }
        }

        // Finally save the measure if all value are filled.
        if(isCalculated) {
            if (metric != null && value != null && component != null) {
                newMeasure.forMetric(metric);
                newMeasure.withValue(value);
                newMeasure.on(component);
                newMeasure.save();
            } else {
                LOGGER.warn(String.format("Measure '%s' for '%s' is ignored on '%s'.",
                        metricKey, metricScope, metricComponent));
            }
        }

    }

    /**
     * Save extra measures from report analysis.
     *
     * @param context Context of the analysis containing services.
     * @param scannedFiles Available files.
     * @param project Complete i-Code report.
     */
    public static void saveExtraMeasures(final SensorContext context, final Map<String, InputFile> scannedFiles,
                                         final AnalysisProject project) {

        // Contains all measures
        final Map<String, List<AnalysisRule>> measures = new HashMap<>();

        // Collect all measures on methods into specific list
        for(final AnalysisRule rule : project.getAnalysisRules()) {
            final String type = rule.getResult().getResultTypePlace();
            final String id = rule.getAnalysisRuleId();
            if(id.contains(COMMON_METRICS_KEY_PART) && type.equals("method")) {
                final List<AnalysisRule> sub = measures.getOrDefault(id, new ArrayList<>());
                sub.add(rule);
                measures.put(id, sub);
            }
        }

        // Compute nesting for shell and fortran.
        computeNesting(context, scannedFiles, measures);
        // Compute complexity for Fortran.
        computeComplexity(context, scannedFiles, measures);
        // Compute function count metric.
        computeFunctions(context, scannedFiles, measures);

    }

    /**
     * Save extra measures from report analysis.
     *
     * @param context Context of the analysis containing services.
     * @param scannedFiles Available files.
     * @param results Complete i-Code report.
     */
    public static void saveExtraMeasures(final SensorContext context, final Map<String, InputFile> scannedFiles,
                                         final List<CheckResult> results) {

        // Contains all measures
        final Map<String, List<AnalysisRule>> measures = new HashMap<>();

        // Collect all measures on methods into specific list
        for(final CheckResult result : results) {
            final String type = Objects.isNull(result.getLocation()) || result.getLocation().isEmpty() ? "class" : "method";
            final String id = result.getName();
            if(id.contains(COMMON_METRICS_KEY_PART) && type.equals("method")) {
                final List<AnalysisRule> sub = measures.getOrDefault(id, new ArrayList<>());
                final AnalysisRule rule = new AnalysisRule(result);
                sub.add(rule);
                measures.put(id, sub);
            }
        }

        // Compute nesting for shell and fortran.
        computeNesting(context, scannedFiles, measures);
        // Compute complexity for Fortran.
        computeComplexity(context, scannedFiles, measures);
        // Compute function count metric.
        computeFunctions(context, scannedFiles, measures);

    }

    /**
     * Compute the number of functions for all files.
     *
     * @param context Context of the analysis containing services.
     * @param scannedFiles Available files.
     * @param measures Map containing list of measure by metrics.
     */
    private static void computeFunctions(final SensorContext context, final Map<String, InputFile> scannedFiles,
                                         final Map<String, List<AnalysisRule>> measures) {

        final Map<String, Integer> functions = new HashMap<>();
        final List<AnalysisRule> rawMeasures = new ArrayList<>();

        // Collect all ncloc measures in one list.
        measures.forEach((x,y) -> {
            if(x.contains(".MET.LineOfCode")) {
                rawMeasures.addAll(y);
            }
        });

        // Compute number of functions by file.
        for(final AnalysisRule measure : rawMeasures) {
            final String file = measure.getResult().getFileName();
            Integer sum = functions.getOrDefault(file, 0) + 1;
            functions.put(file, sum);
        }

        // create measures for number of functions
        functions.forEach((key, value) -> saveMeasure(context, scannedFiles, key, value, CoreMetrics.FUNCTIONS));
    }

    /**
     * Compute the sum of complexity for all fortran files.
     *
     * @param context Context of the analysis containing services.
     * @param scannedFiles Available files.
     * @param measures Map containing list of measure by metrics.
     */
    private static void computeComplexity(final SensorContext context, final Map<String, InputFile> scannedFiles,
                                          final Map<String, List<AnalysisRule>> measures) {

        final Map<String, Integer> complexity = new HashMap<>();
        final List<AnalysisRule> rawMeasures = new ArrayList<>();

        // Collect all fortran complexity measures in one list.
        measures.forEach((x,y) -> {
            if(x.contains("F77.MET.ComplexitySimplified")||x.contains("F90.MET.ComplexitySimplified")) {
                rawMeasures.addAll(y);
            }
        });

        // Compute complexity sum value by file.
        for(final AnalysisRule measure : rawMeasures) {
            final String file = measure.getResult().getFileName();
            final Integer value = Double.valueOf((measure.getResult().getResultValue())).intValue();
            Integer sum = complexity.getOrDefault(file, 0);
            sum += value;
            complexity.put(file, sum);
        }

        // create measures for complexity
        complexity.forEach((key, value) -> saveMeasure(context, scannedFiles, key, value, CoreMetrics.COMPLEXITY));

    }

    /**
     * Compute the max nesting by file and save measures.
     *
     * @param context Context of the analysis containing services.
     * @param scannedFiles Available files.
     * @param measures Map containing list of measure by metrics.
     */
    private static void computeNesting(final SensorContext context, final Map<String, InputFile> scannedFiles,
                                       final Map<String, List<AnalysisRule>> measures) {
        final Map<String, Integer> nesting = new HashMap<>();
        final List<AnalysisRule> rawMeasures = new ArrayList<>();

        // collect all nesting measures in one list
        measures.forEach((x,y) -> {
            if(x.contains(".MET.Nesting")) {
                rawMeasures.addAll(y);
            }
        });

        // compute max nesting value by file
        for(final AnalysisRule measure : rawMeasures) {
            final String file = measure.getResult().getFileName();
            final Integer value = Double.valueOf((measure.getResult().getResultValue())).intValue();
            Integer max = nesting.getOrDefault(file, 0);
            max = Math.max(max,value);
            nesting.put(file, max);
        }

        // create measures for nesting
        nesting.forEach((key, value) -> saveMeasure(context, scannedFiles, key, value, ICodeNestingMetric.NESTING_MAX));

    }

    /**
     * Save a measure for a given situation: metric, file and value.
     *
     * @param context Context of the analysis.
     * @param scannedFiles Set of available files.
     * @param filename Name of the file for which the measure is saved.
     * @param value Value of the measure.
     * @param metric Metric type to save.
     */
    private static void saveMeasure(final SensorContext context, final Map<String, InputFile> scannedFiles,
                                    final String filename, final Integer value, final Metric<Integer> metric) {
        // Retrieve the file.
        final InputFile file = scannedFiles.getOrDefault(filename, null);
        // Create a new measure from the scan context if the file exists.
        if(file!=null) {
            final NewMeasure<Integer> newMeasure = context.newMeasure();
            newMeasure.forMetric(metric);
            newMeasure.withValue(value);
            newMeasure.on(file);
            // Finally save the measure.
            newMeasure.save();
        } else {
            LOGGER.error(String.format(
                    "Measure '%s' on file '%s' has not been saved because source file was not found.",
                    metric.getName(), filename
            ));
        }
    }

    /**
     * Save a measure in a SQ or CNES metric.
     *
     * @param sensorContext Context of the analysis containing services.
     * @param result Measure considered like a rule in i-Code.
     */
    public static void saveMeasure(final SensorContext sensorContext, final CheckResult result) {

        // Filesystem provided by SonarQube.
        final FileSystem fileSystem = sensorContext.fileSystem();
        // Factory for SonarQube predicates.
        final FilePredicates predicates = fileSystem.predicates();
        // Create a new measure from the scan context.
        final NewMeasure<Integer> newMeasure = sensorContext.newMeasure();
        // i-Code rule id.
        final String metricKey = result.getName();
        // Determine if a measure is relative to a file or a method.
        final String metricScope = Objects.isNull(result.getLocation()) || result.getLocation().isEmpty() ? "class" : "method";
        // Component concerned by the measure.
        final String metricComponent = result.getFile().getPath();
        // Component concerned by the measure.
        final String measureValue = String.valueOf(result.getValue());

        // Is true if the metric must be interpreted by the plugin.
        boolean isCalculated = false;

        // Local attribute to set
        Metric<Integer> metric = null;
        InputComponent component = null;
        Integer value = null;
        if(metricScope.equals("class")) {
            // Take SHELL / F77 / F90 ncloc into account
            if (metricKey.contains("MET.LineOfCode")) {
                metric = CoreMetrics.NCLOC;
                component = fileSystem.inputFile(predicates.hasPath(metricComponent));
                value = Double.valueOf(measureValue).intValue();
                isCalculated = true;
            }
            // Take SHELL / F77 / F90 number of comment lines into account
            else if (metricKey.contains("MET.LineOfComment")) {
                metric = CoreMetrics.COMMENT_LINES;
                component = fileSystem.inputFile(predicates.hasPath(metricComponent));
                value = Double.valueOf(measureValue).intValue();
                isCalculated = true;
            }
            // Take SHELL complexity into account
            else if (metricKey.contains("SH.MET.ComplexitySimplified")) {
                metric = CoreMetrics.COMPLEXITY;
                component = fileSystem.inputFile(predicates.hasPath(metricComponent));
                value = Double.valueOf(measureValue).intValue();
                isCalculated = true;
            }
        }

        // Finally save the measure if all value are filled.
        if(isCalculated) {
            if (metric != null && value != null && component != null) {
                newMeasure.forMetric(metric);
                newMeasure.withValue(value);
                newMeasure.on(component);
                newMeasure.save();
            } else {
                LOGGER.warn(String.format("Measure '%s' for '%s' is ignored on '%s'.",
                        metricKey, metricScope, metricComponent));
            }
        }

    }
}
