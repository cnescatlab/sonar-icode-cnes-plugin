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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {

    private AnalysisProject project;
    private AnalysisFile file;
    private AnalysisRule rule;

    @Before
    public void before() {
        project = new AnalysisProject();

        project.analysisInformations = new AnalysisInformations();
        project.analysisInformations.analysisConfigurationId = "azerty";
        project.analysisInformations.analysisDate = "10/10/10";
        project.analysisInformations.author = "Me";
        project.analysisProjectName = "My Project";
        project.analysisProjectVersion = "1.0";

        file = new AnalysisFile();
        file.fileName = "foo.sh";
        file.language = "shell";

        rule = new AnalysisRule();
        rule.analysisRuleId = "WOW";
        rule.result =new Result();
        rule.result.fileName = "a";
        rule.result.resultId = "z";
        rule.result.resultLine = "e";
        rule.result.resultMessage = "r";
        rule.result.resultNamePlace = "t";
        rule.result.resultTypePlace = "y";
        rule.result.resultValue = "u";
    }

    @Test
    public void test_getters() {
        Assert.assertEquals(0, project.getAnalysisFiles().size());
        Assert.assertEquals(0, project.getAnalysisRules().size());
    }
}
