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

import fr.cnes.analysis.tools.analyzer.datas.CheckResult;
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
        fs = new DefaultFileSystem(new File(ICodeMetricsProcessor.class.getResource("/project/").toURI()));
        fs.setEncoding(Charset.forName("UTF-8"));

        bash_sh = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath("bash.sh").getPath())
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .build();
        fs.add(bash_sh);
        clanhb_f = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath("clanhb.f").getPath())
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

        rule.result = new Result();
        rule.analysisRuleId = "SH.MET.LineOfCode";
        rule.result.fileName = "bash.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "class";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";
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

        rule.result = new Result();
        rule.analysisRuleId = "SH.MET.LineOfComment";
        rule.result.fileName = "bash.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "class";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_save_shell_complexity_measures() {

        rule.result = new Result();
        rule.analysisRuleId = "SH.MET.ComplexitySimplified";
        rule.result.fileName = "bash.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "class";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_save_other_measures() {

        rule.result = new Result();
        rule.analysisRuleId = "F90.MET.Nesting";
        rule.result.fileName = "bash.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "class";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(0, context.measures(key).size());
    }

    @Test
    public void test_save_other_measures_on_inexistant_file() {

        rule.result = new Result();
        rule.analysisRuleId = "F90.MET.LineOfCode";
        rule.result.fileName = "zoulou.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "class";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";
        final String key = bash_sh.key();

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(0, context.measures(key).size());
    }

    @Test
    public void test_compute_complexity() {

        final AnalysisProject project = new AnalysisProject();
        final String key = clanhb_f.key();

        rule.result = new Result();
        rule.analysisRuleId = "F77.MET.ComplexitySimplified";
        rule.result.fileName = "clanhb.f";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "method";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";

        project.analysisRule = new AnalysisRule[]{rule};

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_compute_nesting() {

        final AnalysisProject project = new AnalysisProject();
        final String key = clanhb_f.key();

        rule.result = new Result();
        rule.analysisRuleId = "F77.MET.Nesting";
        rule.result.fileName = "clanhb.f";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "method";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";

        project.analysisRule = new AnalysisRule[]{rule};

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_compute_functions() {

        final AnalysisProject project = new AnalysisProject();
        final String key = bash_sh.key();

        rule.result = new Result();
        rule.analysisRuleId = "SH.MET.LineOfCode";
        rule.result.fileName = "bash.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "method";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";

        project.analysisRule = new AnalysisRule[]{rule};

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    public void test_compute_comment() {

        final AnalysisProject project = new AnalysisProject();
        final AnalysisRule rule2 = new AnalysisRule();
        final String key = bash_sh.key();

        rule.result = new Result();
        rule.analysisRuleId = "SH.MET.LineOfCode";
        rule.result.fileName = "bash.sh";
        rule.result.resultValue = "20";
        rule.result.resultLine = "3";
        rule.result.resultTypePlace = "method";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";

        rule2.result = new Result();
        rule2.analysisRuleId = "SH.MET.Nesting";
        rule2.result.fileName = "bash.sh";
        rule2.result.resultValue = "50";
        rule2.result.resultLine = "3";
        rule2.result.resultTypePlace = "method";
        rule2.result.resultNamePlace = "yolo";
        rule2.result.resultId = "11";
        rule2.result.resultMessage = "Small file";

        project.analysisRule = new AnalysisRule[]{rule, rule2};

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);
        Assert.assertEquals(2, context.measures(key).size());
        Assert.assertEquals("functions", ((DefaultMeasure)context.measures(key).toArray()[0]).metric().key());
        Assert.assertEquals("icode-nesting-max", ((DefaultMeasure)context.measures(key).toArray()[1]).metric().key());
        Assert.assertEquals(50, ((DefaultMeasure)context.measures(key).toArray()[1]).value());
    }

}
