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
package fr.cnes.sonar.plugins.icode.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class used to unmarshal i-Code xml file.
 *
 * It contains meta data about the analyzed project.
 *
 * @author lequal
 */
public class AnalysisProject {
    private String analysisProjectName;
    private String analysisProjectVersion;
    private AnalysisInformations analysisInformations;
    private AnalysisFile[] analysisFile;
    private AnalysisRule[] analysisRule;

    /**
     * Getter for accessing analysis rules (issues).
     * @return A list of AnalysisRule.
     */
    public List<AnalysisRule> getAnalysisRules() {
        // Retrieve issues (called rules)
        List<AnalysisRule> rules;
        if(getAnalysisRule() !=null) {
            rules = Arrays.asList(getAnalysisRule());
        } else {
            rules = new ArrayList<>();
        }
        return rules;
    }

    /**
     * Getter for accessing analyzed files (sources).
     * @return A list of AnalysisFile.
     */
    public List<AnalysisFile> getAnalysisFiles() {
        // Retrieve files
        List<AnalysisFile> files;
        if(getAnalysisFile() !=null) {
            files = Arrays.asList(getAnalysisFile());
        } else {
            files = new ArrayList<>();
        }
        return files;
    }

    public String getAnalysisProjectName() {
        return analysisProjectName;
    }

    public void setAnalysisProjectName(String analysisProjectName) {
        this.analysisProjectName = analysisProjectName;
    }

    public String getAnalysisProjectVersion() {
        return analysisProjectVersion;
    }

    public void setAnalysisProjectVersion(String analysisProjectVersion) {
        this.analysisProjectVersion = analysisProjectVersion;
    }

    public AnalysisInformations getAnalysisInformations() {
        return analysisInformations;
    }

    public void setAnalysisInformations(AnalysisInformations analysisInformations) {
        this.analysisInformations = analysisInformations;
    }

    public AnalysisFile[] getAnalysisFile() {
        return analysisFile;
    }

    public void setAnalysisFile(AnalysisFile[] analysisFile) {
        this.analysisFile = analysisFile;
    }

    public AnalysisRule[] getAnalysisRule() {
        return analysisRule;
    }

    public void setAnalysisRule(AnalysisRule[] analysisRule) {
        this.analysisRule = analysisRule;
    }
}
