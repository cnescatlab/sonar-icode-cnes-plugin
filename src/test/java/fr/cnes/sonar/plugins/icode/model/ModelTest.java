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

import fr.cnes.icode.data.CheckResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

public class ModelTest {

    private AnalysisProject project;
    private AnalysisFile file;
    private AnalysisRule rule;
    private RulesDefinition rulesDefinition;
    private Rule check;
    private CheckResult checkResult;

    @Before
    public void before() {
        project = new AnalysisProject();

        project.setAnalysisInformations(new AnalysisInformations());
        project.getAnalysisInformations().setAuthor("Me");

        rule = new AnalysisRule();
        rule.setAnalysisRuleId("WOW");
        rule.setResult(new Result());
        rule.getResult().setFileName("a");
        rule.getResult().setResultLine("e");
        rule.getResult().setResultMessage("r");
        rule.getResult().setResultTypePlace("y");
        rule.getResult().setResultValue("u");

        check = new Rule();
        check.setKey("a");
        check.setName("a");
        check.setType("a");

        rulesDefinition = new RulesDefinition();

        checkResult = new CheckResult("a", "b", "c");
        checkResult.setFile(new File("test-file"));
        checkResult.setValue(0.0f);
        checkResult.setLine(1);
        checkResult.setLocation(null);
        checkResult.setMessage("d");
    }

    @Test
    public void test_getters() {
        Assert.assertEquals(0, project.getAnalysisFiles().size());
        Assert.assertEquals(0, project.getAnalysisRules().size());
        Assert.assertEquals(0, rulesDefinition.getRules().size());
        Assert.assertEquals("a", check.getName());
        Assert.assertEquals("a", check.getType());
    }

    @Test
    public void test_unmarshal_from_file()  {
        InputStream file = this.getClass().getResourceAsStream("/rules/icode-f77-rules.xml");
        RulesDefinition def = (RulesDefinition) XmlHandler.unmarshal(file, RulesDefinition.class);
        Assert.assertEquals(58, def.getRules().size());
    }

    @Test
    public void test_unmarshal_from_stream() {
        InputStream stream = this.getClass().getResourceAsStream("/rules/icode-f77-rules.xml");
        RulesDefinition def = (RulesDefinition) XmlHandler.unmarshal(stream, RulesDefinition.class);
        Assert.assertEquals(58, def.getRules().size());
    }

    @Test
    public void test_CheckResult_to_AnalysisRule_conversion() {
        CheckResult checkResult = new CheckResult("blue", "white", new File("phantom.sh"));
        AnalysisRule analysisRule = new AnalysisRule(checkResult);
        Assert.assertEquals(checkResult.getName(), analysisRule.getAnalysisRuleId());
        Assert.assertEquals(checkResult.getId(), analysisRule.getResult().getResultId());
    }

    @Test
    public void test_CheckResult_to_AnalysisRule_conversion_with_method_location() {
        checkResult.setLocation("method");
        final AnalysisRule analysisRule = new AnalysisRule(checkResult);
        Assert.assertEquals("method", analysisRule.getResult().getResultTypePlace());
    }

    @Test
    public void test_CheckResult_to_AnalysisRule_conversion_with_class_location() {
        checkResult.setLocation("class");
        final AnalysisRule analysisRule = new AnalysisRule(checkResult);
        Assert.assertEquals("class", analysisRule.getResult().getResultTypePlace());
    }

    @Test
    public void test_CheckResult_to_AnalysisRule_conversion_with_empty_location() {
        checkResult.setLocation("");
        final AnalysisRule analysisRule = new AnalysisRule(checkResult);
        Assert.assertEquals("class", analysisRule.getResult().getResultTypePlace());
    }

    @Test
    public void test_CheckResult_to_AnalysisRule_conversion_with_null_location() {
        checkResult.setLocation(null);
        final AnalysisRule analysisRule = new AnalysisRule(checkResult);
        Assert.assertEquals("class", analysisRule.getResult().getResultTypePlace());
    }

    @Test
    public void test_CheckResult_to_AnalysisRule_conversion_with_yolo_location() {
        checkResult.setLocation("yolo");
        final AnalysisRule analysisRule = new AnalysisRule(checkResult);
        Assert.assertEquals("yolo", analysisRule.getResult().getResultTypePlace());
    }

}
