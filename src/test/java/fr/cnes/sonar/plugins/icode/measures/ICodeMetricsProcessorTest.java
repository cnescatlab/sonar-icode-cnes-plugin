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

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import fr.cnes.icode.data.CheckResult;
import fr.cnes.sonar.plugins.icode.model.AnalysisProject;
import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import fr.cnes.sonar.plugins.icode.model.Result;

class ICodeMetricsProcessorTest {

    private DefaultFileSystem fs;
    private SensorContextTester context;
    private Map<String, InputFile> files;
    private AnalysisRule rule;

    private DefaultInputFile clanhb_f;
    private DefaultInputFile clanhb_f90;

    @BeforeEach
    public void prepare() throws URISyntaxException {
        final URI projectPath = ICodeMetricsProcessor.class.getResource("/project").toURI();
        fs = new DefaultFileSystem(new File(projectPath));
        fs.setEncoding(Charset.forName("UTF-8"));

        clanhb_f = TestInputFileBuilder.create(
                "ProjectKey",
                fs.baseDir(), new File(getClass().getResource("/project/clanhb.f").toURI()))
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .build();
        fs.add(clanhb_f);

        clanhb_f90 = TestInputFileBuilder
                .create("ProjectKey", fs.baseDir(), new File(getClass().getResource("/project/clanhb.f90").toURI()))
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .build();
        fs.add(clanhb_f90);

        context = SensorContextTester.create(fs.baseDir());
        files = new HashMap<>();
        rule = new AnalysisRule();

        files.put("clanhb.f", clanhb_f);
        files.put("clanhb.f90", clanhb_f90);
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new AnalysisRuleTestData("F77.MET.ComplexitySimplified", "clanhb.f", "3")),
                Arguments.of(new AnalysisRuleTestData("F77.MET.Nesting", "clanhb.f", "3")),
                Arguments.of(new AnalysisRuleTestData("F77.MET.Line", "clanhb.f", "3")));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void test_compute_metrics(AnalysisRuleTestData testData) {
        final AnalysisProject project = new AnalysisProject();
        final String key = clanhb_f.key();

        rule.setResult(new Result());
        rule.setAnalysisRuleId(testData.getAnalysisRuleId());
        rule.getResult().setFileName(testData.getFileName());
        rule.getResult().setResultValue(testData.getResultValue());
        rule.getResult().setResultLine("3"); // Assuming the result line is always "3"
        rule.getResult().setResultTypePlace("method");
        rule.getResult().setResultMessage("Small file");

        project.setAnalysisRule(new AnalysisRule[] { rule });

        ICodeMetricsProcessor.saveExtraMeasures(context, files, project);

        Assert.assertEquals(1, context.measures(key).size());
    }

    @Test
    void test_is_metric_true() {
        Assert.assertTrue(ICodeMetricsProcessor.isMetric("SH.MET.COCO"));
    }

    @Test
    void test_is_metric_false() {
        Assert.assertFalse(ICodeMetricsProcessor.isMetric("COCO"));
    }

    @Test
    void test_save_extra_measure_with_null_location() {
        // If we upgrade to Junit5, we may check @ParametrizedTest annotation
        String[] locations = { null, "", "method" };
        int[] expectedResults = { 0, 0, 1 };

        for (int i = 0; i < locations.length; ++i) {
            final CheckResult checkResult = new CheckResult("F77.MET.ComplexitySimplified",
                    "F77.MET.ComplexitySimplified", "f77");
            checkResult.setLocation(locations[i]);
            checkResult.setMessage("empty message");
            checkResult.setLine(1);
            checkResult.setValue(1.0f);
            checkResult.setFile(new File("clanhb.f"));

            ICodeMetricsProcessor.saveExtraMeasures(context, files, List.of(checkResult));
            Assert.assertEquals(expectedResults[i], context.measures(clanhb_f.key()).size());
        }
    }

    @Test
    void test_save_nominal_measures() {

        final String key = clanhb_f.key();
        rule.setResult(new Result());
        rule.setAnalysisRuleId("F77.MET.Line");
        rule.getResult().setFileName("clanhb.f");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("3");
        rule.getResult().setResultTypePlace("class");
        rule.getResult().setResultMessage("Small file");

        ICodeMetricsProcessor.saveMeasure(context, files, rule);
        Assert.assertEquals(1, context.measures(key).size());
    }

}

class AnalysisRuleTestData {
    private String analysisRuleId;
    private String fileName;
    private String resultValue;

    public AnalysisRuleTestData(String analysisRuleId, String fileName, String resultValue) {
        this.analysisRuleId = analysisRuleId;
        this.fileName = fileName;
        this.resultValue = resultValue;
    }

    public String getAnalysisRuleId() {
        return analysisRuleId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getResultValue() {
        return resultValue;
    }

}
