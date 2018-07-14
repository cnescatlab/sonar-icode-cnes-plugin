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

import fr.cnes.sonar.plugins.icode.languages.ICodeLanguage;
import fr.cnes.sonar.plugins.icode.measures.ICodeMetricsProcessor;
import fr.cnes.sonar.plugins.icode.model.AnalysisFile;
import fr.cnes.sonar.plugins.icode.model.AnalysisProject;
import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import fr.cnes.sonar.plugins.icode.model.XmlHandler;
import fr.cnes.sonar.plugins.icode.rules.ICodeRulesDefinition;
import fr.cnes.sonar.plugins.icode.settings.ICodePluginProperties;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
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

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Executed during sonar-scanner call.
 * Import i-Code reports into SonarQube.
 *
 * @author lequal
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
    public void describe(SensorDescriptor sensorDescriptor) {
        // Prevents sensor to be run during all analysis.
        sensorDescriptor.onlyOnLanguage(ICodeLanguage.KEY);

        // Defines sensor name
        sensorDescriptor.name(getClass().getName());

        // Only main files are concerned, not tests.
        sensorDescriptor.onlyOnFileType(InputFile.Type.MAIN);

        // This sensor is activated only if a rule from the following repo is activated.
        sensorDescriptor.createIssuesForRuleRepository(ICodeRulesDefinition.getRepositoryKeyForLanguage());
    }

    /**
     * Execute the analysis.
     *
     * @param sensorContext Provide SonarQube services to register results.
     */
    @Override
    public void execute(SensorContext sensorContext) {

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
                final File file = new File(reportPath);
                final AnalysisProject analysisProject = (AnalysisProject) XmlHandler.unmarshal(file, AnalysisProject.class);
                // Retrieve file in a SonarQube format.
                final Map<String, InputFile> scannedFiles = getScannedFiles(fileSystem, analysisProject);

                // Handles issues.
                for (final AnalysisRule rule : analysisProject.getAnalysisRules()) {
                    if(isRuleActive(activeRules, rule.analysisRuleId)) { // manage active rules
                        saveIssue(sensorContext, scannedFiles, rule);
                    } else if (ICodeMetricsProcessor.isMetric(rule.analysisRuleId)) { // manage trivial measures
                        ICodeMetricsProcessor.saveMeasure(sensorContext, scannedFiles, rule);
                    } else { // log ignored data
                        LOGGER.info(String.format(
                                "An issue for rule '%s' was detected by i-Code but this rule is deactivated in current analysis.",
                                rule.analysisRuleId));
                    }
                }

                ICodeMetricsProcessor.saveExtraMeasures(sensorContext, scannedFiles, analysisProject);

            } catch (JAXBException e) {
                LOGGER.error(e.getMessage(), e);
                sensorContext.newAnalysisError().message(e.getMessage()).save();
            }
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
        final InputFile inputFile = files.getOrDefault(issue.result.fileName, null);

        if(inputFile!=null) {
            // Retrieve the ruleKey if it exists.
            final RuleKey ruleKey = RuleKey.of(ICodeRulesDefinition.getRepositoryKeyForLanguage(), issue.analysisRuleId);

            // Create a new issue for SonarQube, but it must be saved using NewIssue.save().
            final NewIssue newIssue = context.newIssue();
            // Create a new location for this issue.
            final NewIssueLocation newIssueLocation = newIssue.newLocation();

            // Calculate the line number of the issue (must be between 1 and max, otherwise 1).
            int issueLine = Integer.parseInt(issue.result.resultLine);
            issueLine = issueLine > 0 && issueLine <= inputFile.lines() ? issueLine : 1;

            // Set trivial issue's attributes from AnalysisRule fields.
            newIssueLocation.on(inputFile);
            newIssueLocation.at(inputFile.selectLine(issueLine));
            newIssueLocation.message(issue.result.resultMessage);
            newIssue.forRule(ruleKey);
            newIssue.at(newIssueLocation);
            newIssue.save();
        } else {
            LOGGER.error(String.format(
                    "Issue '%s' on file '%s' has not been saved because source file was not found.",
                    issue.analysisRuleId, issue.result.fileName
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
    private Map<String, InputFile> getScannedFiles(final FileSystem fileSystem, final AnalysisProject analysisProject) {
        // Contains the result to be returned.
        final Map<String, InputFile> result = new HashMap<>();
        final List<AnalysisFile> files = analysisProject.getAnalysisFiles();

        // Looks for each file in file system, print an error if not found.
        for(final AnalysisFile file : files) {
            // Checks if the file system contains a file with corresponding path (relative or absolute).
            FilePredicate predicate = fileSystem.predicates().hasPath(file.fileName);
            InputFile inputFile = fileSystem.inputFile(predicate);
            if(inputFile!=null) {
                result.put(file.fileName, inputFile);
            } else {
                LOGGER.error(String.format(
                        "The source file '%s' was not found.",
                        file.fileName
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
            if(fileSystem.hasFiles(fileSystem.predicates().hasPath(path))) {
                result.add(path);
                LOGGER.info(String.format("Results file %s has been found and will be processed.", path));
            } else {
                LOGGER.error(String.format("Results file %s has not been found and wont be processed.", path));
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
    private boolean isRuleActive(final ActiveRules activeRules, final String rule) {
        final RuleKey ruleKey = RuleKey.of(ICodeRulesDefinition.getRepositoryKeyForLanguage(), rule);
        return activeRules.find(ruleKey)!=null;
    }

}


