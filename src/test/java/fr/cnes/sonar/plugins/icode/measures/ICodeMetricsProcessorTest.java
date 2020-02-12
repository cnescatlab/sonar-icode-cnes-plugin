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
import fr.cnes.sonar.plugins.icode.model.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.batch.sensor.measure.internal.DefaultMeasure;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ICodeMetricsProcessorTest {

    private DefaultFileSystem fs;
    private SensorContextTester context;
    private Map<String, InputFile> files;
    private AnalysisRule rule;

    private DefaultInputFile bash_sh;
    private DefaultInputFile clanhb_f;

    @Before
    public void prepare() throws URISyntaxException {
        final URI projectPath = ICodeMetricsProcessor.class.getResource("/project/").toURI();
        fs = new DefaultFileSystem(new File(projectPath));
        fs.setEncoding(Charset.forName("UTF-8"));

        bash_sh = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath(projectPath.getRawPath()+"bash.sh").getPath())
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .build();
        fs.add(bash_sh);
        clanhb_f = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath(projectPath.getRawPath()+"clanhb.f").getPath())
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .build();
        fs.add(clanhb_f);

        context = SensorContextTester.create(fs.baseDir());
        files = new HashMap<>();
        rule = new AnalysisRule();

        files.put("bash.sh", bash_sh);
        files.put("clanhb.f", clanhb_f);
    }

    @Test
    public void test_is_metric_true() {
        Assert.assertTrue(ICodeMetricsProcessor.isMetric("SH.MET.COCO"));
    }

    @Test
    public void test_is_metric_false() {
        Assert.assertFalse(ICodeMetricsProcessor.isMetric("COCO"));
    }

    @Test
    public void test_save_nominal_measures() {

        rule.setResult(new Result());
        rule.setAnalysisRuleId("SH.MET.LineOfCode");
        rule.getResult().setFileName("bash.sh");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("class");
        rule.getResult().setResultMessage("Small file");
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_save_checkresult_measures() {
        final String key = bash_sh.key();
        CheckResult checkResult = new CheckResult("SH.MET.LineOfCode", "11", "Shell");
        checkResult.setMessage("bim");
        checkResult.setFile(new File(getClass().getResource("/project/bash.sh").getPath()));
        checkResult.setLine(4);
        checkResult.setValue(3f);
        checkResult.setLocation(null);

        ICodeMetricsProcessor.saveMeasure(context, checkResult);
        Assert.assertEquals(0, context.measures(key).size());
    }

    @Test
    public void test_save_line_of_comment_measures() {

        rule.setResult(new Result());
        rule.setAnalysisRuleId("SH.MET.LineOfComment");
        rule.getResult().setFileName("bash.sh");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("class");
        rule.getResult().setResultMessage("Small file");
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_save_shell_complexity_measures() {

        rule.setResult(new Result());
        rule.setAnalysisRuleId("SH.MET.ComplexitySimplified");
        rule.getResult().setFileName("bash.sh");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("class");
        rule.getResult().setResultMessage("Small file");
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_save_other_measures() {

        rule.setResult(new Result());
        rule.setAnalysisRuleId("F90.MET.Nesting");
        rule.getResult().setFileName("bash.sh");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("class");
        rule.getResult().setResultMessage("Small file");
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(0, context.measures(key).size());
    }

    @Test
    public void test_save_other_measures_on_inexistant_file() {

        rule.setResult(new Result());
        rule.setAnalysisRuleId("F90.MET.LineOfCode");
        rule.getResult().setFileName("zoulou.sh");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("class");
        rule.getResult().setResultMessage("Small file");
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(0, context.measures(key).size());
    }

    @Test
    public void test_compute_complexity() {

        final AnalysisProject project = new AnalysisProject();
        final String key = clanhb_f.key();

        rule.setResult(new Result());
        rule.setAnalysisRuleId("F77.MET.ComplexitySimplified");
        rule.getResult().setFileName("clanhb.f");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("method");
        rule.getResult().setResultMessage("Small file");

        project.setAnalysisRule(new AnalysisRule[]{rule});

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_compute_nesting() {

        final AnalysisProject project = new AnalysisProject();
        final String key = clanhb_f.key();

        rule.setResult(new Result());
        rule.setAnalysisRuleId("F77.MET.Nesting");
        rule.getResult().setFileName("clanhb.f");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("method");
        rule.getResult().setResultMessage("Small file");

        project.setAnalysisRule(new AnalysisRule[]{rule});

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_compute_functions() {

        final AnalysisProject project = new AnalysisProject();
        final String key = bash_sh.key();

        rule.setResult(new Result());
        rule.setAnalysisRuleId("SH.MET.LineOfCode");
        rule.getResult().setFileName("bash.sh");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("method");
        rule.getResult().setResultMessage("Small file");

        project.setAnalysisRule(new AnalysisRule[]{rule});

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_compute_comment() {

        final AnalysisProject project = new AnalysisProject();
        final AnalysisRule rule2 = new AnalysisRule();
        final String key = bash_sh.key();

        rule.setResult(new Result());
        rule.setAnalysisRuleId("SH.MET.LineOfCode");
        rule.getResult().setFileName("bash.sh");
        rule.getResult().setResultValue("20");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("method");
        rule.getResult().setResultMessage("Small file");

        rule2.setResult(new Result());
        rule2.setAnalysisRuleId("SH.MET.Nesting");
        rule2.getResult().setFileName("bash.sh");
        rule2.getResult().setResultValue("50");
        rule2.getResult().setResultLine("3");
        rule2.getResult().setResultTypePlace("method");
        rule2.getResult().setResultMessage("Small file");

        project.setAnalysisRule(new AnalysisRule[]{rule, rule2});

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(2, context.measures(key).size());
        Assert.assertEquals("functions", ((DefaultMeasure)context.measures(key).toArray()[0]).metric().key());
        Assert.assertEquals("icode-nesting-max", ((DefaultMeasure)context.measures(key).toArray()[1]).metric().key());
        Assert.assertEquals(50, ((DefaultMeasure)context.measures(key).toArray()[1]).value());
    }

}
