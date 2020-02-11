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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class used to unmarshal i-Code xml file.
 *
 * It contains meta data about the analyzed project.
 */
@XmlRootElement
public class AnalysisProject {
    @XmlAttribute
    public String analysisProjectName;
    @XmlAttribute
    public String analysisProjectVersion;
    @XmlElement
    public AnalysisInformations analysisInformations;
    @XmlElement
    public AnalysisFile[] analysisFile;
    @XmlElement
    public AnalysisRule[] analysisRule;

    /**
     * Getter for accessing analysis rules (issues).
     * @return A list of AnalysisRule.
     */
    public List<AnalysisRule> getAnalysisRules() {
        // Retrieve issues (called rules)
        List<AnalysisRule> rules;
        if(analysisRule!=null) {
            rules = Arrays.asList(analysisRule);
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
        if(analysisFile!=null) {
            files = Arrays.asList(analysisFile);
        } else {
            files = new ArrayList<>();
        }
        return files;
    }
}
