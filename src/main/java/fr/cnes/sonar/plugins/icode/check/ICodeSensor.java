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
package fr.cnes.sonar.plugins.icode.check;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fr.cnes.icode.data.AbstractChecker;
import fr.cnes.icode.data.CheckResult;
import fr.cnes.icode.exception.JFlexException;
import fr.cnes.icode.logger.ICodeLogger;
import fr.cnes.icode.services.checkers.CheckerContainer;
import fr.cnes.icode.services.checkers.CheckerService;
import fr.cnes.icode.services.languages.ILanguage;
import fr.cnes.icode.services.languages.LanguageService;
import fr.cnes.sonar.plugins.icode.exceptions.ICodeException;
import fr.cnes.sonar.plugins.icode.languages.Fortran77Language;
import fr.cnes.sonar.plugins.icode.languages.Fortran90Language;
import fr.cnes.sonar.plugins.icode.languages.ShellLanguage;
import fr.cnes.sonar.plugins.icode.measures.ICodeMetricsProcessor;
import fr.cnes.sonar.plugins.icode.model.AnalysisFile;
import fr.cnes.sonar.plugins.icode.model.AnalysisProject;
import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import fr.cnes.sonar.plugins.icode.model.XmlHandler;
import fr.cnes.sonar.plugins.icode.rules.ICodeRulesDefinition;
import fr.cnes.sonar.plugins.icode.settings.ICodePluginProperties;
import org.sonar.api.batch.fs.*;
import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.config.Configuration;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Executed during sonar-scanner call.
 * This Sensor is able to:
 *  - Run i-Code checks.
 *  - Import i-Code reports into SonarQube.
 *  - Run a specified external version of i-Code.
 */
public class ICodeSensor implements Sensor {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Loggers.get(ICodeSensor.class);

    /**
     * Give information about this sensor.
     *
     * @param sensorDescriptor Descriptor injected to set the sensor.
     */
    @Override
    public void describe(final SensorDescriptor sensorDescriptor) {
        // Prevents sensor to be run during all analysis.
        sensorDescriptor.onlyOnLanguages(ShellLanguage.KEY, Fortran77Language.KEY, Fortran90Language.KEY);

        // Defines sensor name
        sensorDescriptor.name(getClass().getName());

        // Only main files are concerned, not tests.
        sensorDescriptor.onlyOnFileType(InputFile.Type.MAIN);

        // This sensor is activated only if a rule from the following repo is activated.
        sensorDescriptor.createIssuesForRuleRepositories(
                ICodeRulesDefinition.getRepositoryKeyForLanguage(ShellLanguage.KEY),
                ICodeRulesDefinition.getRepositoryKeyForLanguage(Fortran77Language.KEY),
                ICodeRulesDefinition.getRepositoryKeyForLanguage(Fortran90Language.KEY));
    }

    /**
     * Execute the analysis.
     *
     * @param sensorContext Provide SonarQube services to register results.
     */
    @Override
    public void execute(final SensorContext sensorContext) {

        // Represent the configuration used for the analysis.
        final Configuration config = sensorContext.config();

        // Run external version of i-Code CNES on during analysis.
        if(config.getBoolean(ICodePluginProperties.AUTOLAUNCH_PROP_KEY)
                .orElse(Boolean.getBoolean(ICodePluginProperties.AUTOLAUNCH_PROP_DEFAULT))) {
            executeExternalICode(sensorContext);
        }

        // Run embedded version of i-Code CNES on during analysis.
        if(config.getBoolean(ICodePluginProperties.USE_EMBEDDED_PROP_KEY)
                .orElse(Boolean.getBoolean(ICodePluginProperties.USE_EMBEDDED_PROP_DEFAULT))) {
            executeEmbeddedICode(sensorContext);
        }

        // Import i-Code issues from external result files.
        executeExternalResultsImport(sensorContext);

    }

    /**
     * Import i-Code issues from external result files.
     *
     * @param sensorContext Context of the sensor.
     */
    protected void executeExternalResultsImport(final SensorContext sensorContext) {

        // Represent the file system used for the analysis.
        final FileSystem fileSystem = sensorContext.fileSystem();
        // Represent the configuration used for the analysis.
        final Configuration config = sensorContext.config();
        // Represent the active rules used for the analysis.
        final ActiveRules activeRules = sensorContext.activeRules();

        // Report files found in file system and corresponding to SQ property.
        final List<String> reportFiles = getReportFiles(config, fileSystem);

        // If exists, unmarshal each xml result file.
        for(final String reportPath : reportFiles) {
            try {
                // Unmarshall the xml.
                final FileInputStream file = new FileInputStream(reportPath);
                final AnalysisProject analysisProject = (AnalysisProject) XmlHandler.unmarshal(file, AnalysisProject.class);
                // Retrieve file in a SonarQube format.
                final Map<String, InputFile> scannedFiles = getScannedFiles(fileSystem, analysisProject);

                // Handles issues.
                for (final AnalysisRule rule : analysisProject.getAnalysisRules()) {
                    if(isRuleActive(activeRules, rule.getAnalysisRuleId())) { // manage active rules
                        saveIssue(sensorContext, scannedFiles, rule);
                    } else if (ICodeMetricsProcessor.isMetric(rule.getAnalysisRuleId())) { // manage trivial measures
                        ICodeMetricsProcessor.saveMeasure(sensorContext, scannedFiles, rule);
                    } else { // log ignored data
                        LOGGER.info(String.format(
                                "An issue for rule '%s' was detected by i-Code but this rule is deactivated in current analysis.",
                                rule.getAnalysisRuleId()));
                    }
                }

                ICodeMetricsProcessor.saveExtraMeasures(sensorContext, scannedFiles, analysisProject);

            } catch (FileNotFoundException e) {
                LOGGER.error(e.getMessage(), e);
                sensorContext.newAnalysisError().message(e.getMessage()).save();
            }
        }
    }

    /**
     * Execute the embedded version of i-Code.
     *
     * @param sensorContext Context of the sensor.
     */
    private void executeEmbeddedICode(final SensorContext sensorContext) {
        // Initialisation of tools for analysis.
        final FileSystem fileSystem = sensorContext.fileSystem();
        final FilePredicates predicates = fileSystem.predicates();
        final ActiveRules activeRules = sensorContext.activeRules();
        final Iterable<InputFile> inputFiles = fileSystem.inputFiles(predicates.hasType(InputFile.Type.MAIN));
        final HashSet<File> files = new HashSet<>();
        final HashMap<String,InputFile> filesMap = new HashMap<>();

        // Gather all files in a Set.
        for(final InputFile inputFile : inputFiles) {
            files.add(inputFile.file());
            filesMap.put(inputFile.file().getPath(), inputFile);
        }

        // Run all checkers on all files.
        final List<CheckResult> results = sonarCheck(files, LanguageService.getLanguagesIds(), null);

        // Add each issue to SonarQube.
        for(final CheckResult result : results) {
            if(isRuleActive(activeRules, result.getName())) { // manage active rules
                saveIssue(sensorContext, result);
            } else if (ICodeMetricsProcessor.isMetric(result.getName())) { // manage trivial measures
                ICodeMetricsProcessor.saveMeasure(sensorContext, result);
            } else { // log ignored data
                LOGGER.info(String.format(
                        "An issue for rule '%s' was detected by i-Code but this rule is deactivated in current analysis.",
                        result.getName()));
            }
        }

        ICodeMetricsProcessor.saveExtraMeasures(sensorContext, filesMap, results);

    }

    /**
     * <h1>{@link #sonarCheck(Set, List, List)}</h1>
     * <p>
     * This method apply all rules of the different contributions set in
     * parameter except the one excluded. File in parameters are being analyzed
     * by each contribution able to handle it or none if it isn't.
     * </p>
     * <p>
     * <strong>Important :</strong> Default configurations to run analysis are
     * available when setting parameters.
     *
     * @param pInputFiles
     *            to analyze
     * @param pLanguageIds
     *            to include in the analysis. <strong>Set null</strong> to run
     *            an analysis including all contributions.
     * @param pExcludedCheckIds
     *            rules identifier to exclude from the analysis. <strong>Set
     *            null</strong> run analysis with every rules.
     * @return list of {@link CheckResult} found by the analysis.
     */
    public List<CheckResult> sonarCheck(final Set<File> pInputFiles, final List<String> pLanguageIds,
                                         final List<String> pExcludedCheckIds) {
        final String methodName = "check";
        ICodeLogger.entering(this.getClass().getName(), methodName);

        List<String> languageIds = pLanguageIds;
        if (languageIds == null) {
            languageIds = LanguageService.getLanguagesIds();
        }
        List<String> excludedCheckIds = pExcludedCheckIds;
        if (pExcludedCheckIds == null) {
            excludedCheckIds = new ArrayList<>();
        }
        final List<CheckResult> analysisResultCheckResult = new ArrayList<>();

        // Contains checkers by language.
        final Map<String,List<CheckerContainer>> checkers = Maps.newHashMap();
        // Contains files by language.
        final Map<String, List<File>> inputs = Maps.newLinkedHashMap();
        // Get languages to check during the analysis.
        final List<ILanguage> languages = LanguageService.getLanguages(languageIds);
        // Get checkers to run during analysis.
        for(final ILanguage language : languages) {
            checkers.put(language.getId(), CheckerService.getCheckers(language.getId(), excludedCheckIds));
        }

        // Sort files by language.
        for (final File file : pInputFiles) {
            final String languageId = LanguageService.getLanguageId(getFileExtension(file.getAbsolutePath()));
            final List<File> tempList = inputs.getOrDefault(languageId, Lists.newArrayList());
            tempList.add(file);
            inputs.put(languageId, tempList);
        }
        // For each selected language, run selected checkers on selected files.
        for (final ILanguage language : languages) {
            for (final File input : inputs.getOrDefault(language.getId(), Lists.newArrayList())) {
                for (final CheckerContainer checker : checkers.get(language.getId())) {
                    try {
                        AbstractChecker check = checker.getChecker();
                        check.setInputFile(input);
                        analysisResultCheckResult.addAll(check.run());
                    } catch (final Exception e) {
                        // Set the error message.
                        final String errorMessage = String.format("Internal i-Code error: exception [%s] thrown while checking [%s] on file [%s] - %s",
                                e.getClass().getSimpleName(), checker.getName(), input.getPath(), e.getMessage());
                        // Log the error in i-Code and SonarQube logger.
                        LOGGER.error(errorMessage, e);
                        // Create an issue to be displayed in SonarQube.
                        final CheckResult exception = new CheckResult("Parsing Error", "Parsing Error", input);
                        exception.setLangageId(language.getId());
                        exception.setLocation("unknown");
                        exception.setLine(0);
                        exception.setMessage(errorMessage);
                        // Add the exception as a Parsing Error result.
                        analysisResultCheckResult.add(exception);
                    }
                }
            }
        }

        return analysisResultCheckResult;
    }

    /**
     * Return the file extension without the final point '.'.
     *
     * @param pFileName
     *            to retrieve the extension
     * @return The extension name of the file
     */
    private String getFileExtension(final String pFileName) {
        String extension = null;

        final int i = pFileName.lastIndexOf('.');
        final int p = Math.max(pFileName.lastIndexOf('/'), pFileName.lastIndexOf('\\'));

        if (i > p) {
            extension = pFileName.substring(i + 1);
        }
        return extension;
    }

    /**
     * Execute i-Code through a system process.
     *
     * @param sensorContext Context of the sensor.
     */
    private void executeExternalICode(final SensorContext sensorContext) {
        LOGGER.info("i-Code CNES auto-launch enabled.");
        final Configuration config = sensorContext.config();
        final FileSystem fileSystem = sensorContext.fileSystem();
        final FilePredicates predicates = fileSystem.predicates();
        final Iterable<InputFile> inputFiles = fileSystem.inputFiles(predicates.hasType(InputFile.Type.MAIN));
        final StringBuilder files = new StringBuilder();
        inputFiles.forEach(file -> files.append(file.file().getPath()).append(" "));
        final String executable = config.get(ICodePluginProperties.ICODE_PATH_KEY).orElse(ICodePluginProperties.ICODE_PATH_DEFAULT);
        final String outputFile = config.get(ICodePluginProperties.REPORT_PATH_KEY).orElse(ICodePluginProperties.REPORT_PATH_DEFAULT);
        final String outputPath = Paths.get(sensorContext.fileSystem().baseDir().toString(),outputFile).toString();
        final String outputOption = "-o";
        final String command = String.join(" ", executable, files.toString(), outputOption, outputPath);

        LOGGER.info("Running i-Code CNES and generating results to "+ outputPath);
        try {
            int success = runICode(command);
            if(0!=success){
                final String message = String.format("i-Code CNES auto-launch analysis failed with exit code %d.", success);
                throw new ICodeException(message);
            }
            LOGGER.info("Auto-launch successfully executed i-Code CNES.");
        } catch (final InterruptedException | IOException | ICodeException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Run the i-Code command.
     *
     * @param command The i-Code Command to execute.
     * @return 0 if all was fine.
     * @throws IOException On possible unknown file.
     * @throws InterruptedException On possible process problem.
     */
    protected int runICode(final String command) throws IOException, InterruptedException {
        final Process icode =  Runtime.getRuntime().exec(command);
        return icode.waitFor();
    }

    /**
     * This method save an issue into the SonarQube service.
     *
     * @param sensorContext A SensorContext to reach SonarQube services.
     * @param result A CheckResult with the convenient format for i-Code.
     */
    static void saveIssue(final SensorContext sensorContext, final CheckResult result) {
        final FileSystem fileSystem = sensorContext.fileSystem();
        final FilePredicates predicates = fileSystem.predicates();
        final NewIssue issue = sensorContext.newIssue();
        final InputFile file = fileSystem.inputFile(predicates.hasPath(result.getFile().getPath()));
        if(Objects.nonNull(file)) {
            final String repositoryKey = ICodeRulesDefinition.getRepositoryKeyForLanguage(file.language());
            final RuleKey ruleKey = RuleKey.of(repositoryKey, result.getName());
            final NewIssueLocation location = issue.newLocation();
            final TextRange textRange = file.selectLine(result.getLine()>0?result.getLine():1);
            location.on(file);
            location.at(textRange);
            location.message(result.getMessage());
            issue.at(location);
            issue.forRule(ruleKey);

            issue.save();
        }
    }

    /**
     * This method save an issue into the SonarQube service.
     *
     * @param context A SensorContext to reach SonarQube services.
     * @param files Map containing files in SQ format.
     * @param issue A AnalysisRule with the convenient format for i-Code.
     */
    static void saveIssue(final SensorContext context, final Map<String, InputFile> files, final AnalysisRule issue) {

        // Retrieve the file containing the issue.
        final InputFile inputFile = files.getOrDefault(issue.getResult().getFileName(), null);

        if(inputFile!=null) {
            // Retrieve the ruleKey if it exists.
            final RuleKey ruleKey = RuleKey.of(ICodeRulesDefinition.getRepositoryKeyForLanguage(inputFile.language()), issue.getAnalysisRuleId());

            // Create a new issue for SonarQube, but it must be saved using NewIssue.save().
            final NewIssue newIssue = context.newIssue();
            // Create a new location for this issue.
            final NewIssueLocation newIssueLocation = newIssue.newLocation();

            // Calculate the line number of the issue (must be between 1 and max, otherwise 1).
            int issueLine = Integer.parseInt(issue.getResult().getResultLine());
            issueLine = issueLine > 0 && issueLine <= inputFile.lines() ? issueLine : 1;

            // Set trivial issue's attributes from AnalysisRule fields.
            newIssueLocation.on(inputFile);
            newIssueLocation.at(inputFile.selectLine(issueLine));
            newIssueLocation.message(issue.getResult().getResultMessage());
            newIssue.forRule(ruleKey);
            newIssue.at(newIssueLocation);
            newIssue.save();
        } else {
            LOGGER.error(String.format(
                    "Issue '%s' on file '%s' has not been saved because source file was not found.",
                    issue.getAnalysisRuleId(), issue.getResult().getFileName()
            ));
        }

    }

    /**
     * Construct a map with all found source files.
     *
     * @param fileSystem The file system on which the analysis is running.
     * @param analysisProject The i-Code report content.
     * @return A possibly empty Map of InputFile.
     */
    protected Map<String, InputFile> getScannedFiles(final FileSystem fileSystem, final AnalysisProject analysisProject) {
        // Contains the result to be returned.
        final Map<String, InputFile> result = new HashMap<>();
        final List<AnalysisFile> files = analysisProject.getAnalysisFiles();

        // Looks for each file in file system, print an error if not found.
        for(final AnalysisFile file : files) {
            // Checks if the file system contains a file with corresponding path (relative or absolute).
            FilePredicate predicate = fileSystem.predicates().hasPath(file.getFileName());
            InputFile inputFile = fileSystem.inputFile(predicate);
            if(inputFile!=null) {
                result.put(file.getFileName(), inputFile);
            } else {
                LOGGER.error(String.format(
                        "The source file '%s' was not found.",
                        file.getFileName()
                ));
            }
        }

        return result;
    }

    /**
     * Returns a list of processable result file's path.
     *
     * @param config Configuration of the analysis where properties are put.
     * @param fileSystem The current file system.
     * @return Return a list of path 'findable' in the file system.
     */
    private List<String> getReportFiles(final Configuration config, final FileSystem fileSystem) {
        // Contains the result to be returned.
        final List<String> result = new ArrayList<>();

        // Retrieves the non-verified path list from the SonarQube property.
        final String[] pathArray = config.getStringArray(ICodePluginProperties.REPORT_PATH_KEY);

        // Check if each path is known by the file system and add it to the processable path list,
        // otherwise print a warning and ignore this result file.
        for(String path : pathArray) {
            final File file = new File(fileSystem.baseDir(), path);
            if(file.exists() && file.isFile()) {
                result.add(path);
                LOGGER.info(String.format("Results file %s has been found and will be processed.", path));
            } else {
                LOGGER.warn(String.format("Results file %s has not been found and wont be processed.", path));
            }
        }

        return result;
    }

    /**
     * Check if a rule is activated in current analysis.
     *
     * @param activeRules Set of active rules during an analysis.
     * @param rule Key (i-Code) of the rule to check.
     * @return True if the rule is active and false if not or not exists.
     */
    protected boolean isRuleActive(final ActiveRules activeRules, final String rule) {
        final RuleKey ruleKeyShell = RuleKey.of(ICodeRulesDefinition.getRepositoryKeyForLanguage(ShellLanguage.KEY), rule);
        final RuleKey ruleKeyF77 = RuleKey.of(ICodeRulesDefinition.getRepositoryKeyForLanguage(Fortran77Language.KEY), rule);
        final RuleKey ruleKeyF90 = RuleKey.of(ICodeRulesDefinition.getRepositoryKeyForLanguage(Fortran90Language.KEY), rule);
        return activeRules.find(ruleKeyShell)!=null || activeRules.find(ruleKeyF77)!=null || activeRules.find(ruleKeyF90)!=null;
    }

}


