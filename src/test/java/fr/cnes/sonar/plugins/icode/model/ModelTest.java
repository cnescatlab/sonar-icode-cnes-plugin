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

        project.setAnalysisInformations(new AnalysisInformations());
        project.getAnalysisInformations().setAnalysisConfigurationId("azerty");
        project.getAnalysisInformations().setAnalysisDate("10/10/10");
        project.getAnalysisInformations().setAuthor("Me");
        project.setAnalysisProjectName("My Project");
        project.setAnalysisProjectVersion("1.0");

        file = new AnalysisFile();
        file.setFileName("foo.sh");
        file.setLanguage("shell");

        rule = new AnalysisRule();
        rule.setAnalysisRuleId("WOW");
        rule.setResult(new Result());
        rule.getResult().setFileName("a");
        rule.getResult().setResultId("z");
        rule.getResult().setResultLine("e");
        rule.getResult().setResultMessage("r");
        rule.getResult().setResultNamePlace("t");
        rule.getResult().setResultTypePlace("y");
        rule.getResult().setResultValue("u");

        check = new Rule();
        check.setKey("a");
        check.setCardinality("a");
        check.setDescription("a");
        check.setInternalKey("a");
        check.setName("a");
        check.setRemediationFunction("a");
        check.setRemediationFunctionBaseEffort("a");
        check.setSeverity("a");
        check.setStatus("a");
        check.setTag("a");
        check.setType("a");

        rulesDefinition = new RulesDefinition();
    }

    @Test
    public void test_getters() {
        Assert.assertEquals(0, project.getAnalysisFiles().size());
        Assert.assertEquals(0, project.getAnalysisRules().size());
        Assert.assertEquals(0, rulesDefinition.getRules().size());
        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(check);
        rulesDefinition.setIcodeRules(new ArrayList(rules));
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
