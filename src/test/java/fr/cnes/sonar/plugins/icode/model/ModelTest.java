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

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ModelTest {

    private AnalysisProject project;
    private AnalysisFile file;
    private AnalysisRule rule;
    private RulesDefinition rulesDefinition;
    private Rule check;

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
        rule.result = new Result();
        rule.result.fileName = "a";
        rule.result.resultId = "z";
        rule.result.resultLine = "e";
        rule.result.resultMessage = "r";
        rule.result.resultNamePlace = "t";
        rule.result.resultTypePlace = "y";
        rule.result.resultValue = "u";

        check = new Rule();
        check.key = "a";
        check.cardinality = "a";
        check.description = "a";
        check.internalKey = "a";
        check.name = "a";
        check.remediationFunction = "a";
        check.remediationFunctionBaseEffort = "a";
        check.severity = "a";
        check.status = "a";
        check.tag = "a";
        check.type = "a";

        rulesDefinition = new RulesDefinition();
    }

    @Test
    public void test_getters() {
        Assert.assertEquals(0, project.getAnalysisFiles().size());
        Assert.assertEquals(0, project.getAnalysisRules().size());
        Assert.assertEquals(0, rulesDefinition.getRules().size());
        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(check);
        rulesDefinition.icodeRules = new ArrayList(rules);
        Assert.assertEquals(1, rulesDefinition.getRules().size());
    }

    @Test
    public void test_unmarshal_from_file()  {
        InputStream file = this.getClass().getResourceAsStream("/rules/icode-shell-rules.xml");
        RulesDefinition def = (RulesDefinition) XmlHandler.unmarshal(file, RulesDefinition.class);
        Assert.assertEquals(45, def.getRules().size());
    }

    @Test
    public void test_unmarshal_from_stream() {
        InputStream stream = this.getClass().getResourceAsStream("/rules/icode-shell-rules.xml");
        RulesDefinition def = (RulesDefinition) XmlHandler.unmarshal(stream, RulesDefinition.class);
        Assert.assertEquals(45, def.getRules().size());
    }
}
