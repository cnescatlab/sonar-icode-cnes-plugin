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

import fr.cnes.sonar.plugins.icode.languages.Fortran77Language;
import fr.cnes.sonar.plugins.icode.model.AnalysisFile;
import fr.cnes.sonar.plugins.icode.model.AnalysisProject;
import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import fr.cnes.sonar.plugins.icode.model.Result;
import fr.cnes.sonar.plugins.icode.settings.ICodePluginProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.config.internal.MapSettings;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class ICodeSensorTest {

    private DefaultFileSystem fs;
    private SensorContextTester context;
    private Map<String, InputFile> files;

    private DefaultInputFile clanhb_f;
    private DefaultInputFile clanhb_f90;
    private AnalysisRule rule;

    @Before
    public void prepare() throws URISyntaxException {
        fs = new DefaultFileSystem(new File(getClass().getResource("/project").toURI()));
        fs.setEncoding(StandardCharsets.UTF_8);

        clanhb_f = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath("clanhb.f").getPath())
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .setLines(10)
                .setOriginalLineStartOffsets(new int[]{0,0,0,0,0,0,0,0,0,0})
                .setLastValidOffset(100)
                .build();
        fs.add(clanhb_f);

        clanhb_f90 = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath("clanhb.f90").getPath())
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .setLines(10)
                .setOriginalLineStartOffsets(new int[]{0,0,0,0,0,0,0,0,0,0})
                .setLastValidOffset(100)
                .build();
        fs.add(clanhb_f90);


        context = SensorContextTester.create(fs.baseDir());
        files = new HashMap<>();
        rule = new AnalysisRule();

        
        files.put("clanhb.f", clanhb_f);
        files.put("clanhb.f90", clanhb_f90);

        context = SensorContextTester.create(fs.baseDir());
        context.setFileSystem(fs);
        MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.embedded", false);
        settings.setProperty(ICodePluginProperties.REPORT_PATH_KEY, "../result.res");
        context.setSettings(settings);
    }

    @Test
    public void test_given_sensorDescriptor_when_describe_then_callSensorDescriptorName() {
        SensorDescriptor sensorDescriptor = Mockito.mock(SensorDescriptor.class);
        ICodeSensor icodeMetricsSensor = new ICodeSensor();
        icodeMetricsSensor.describe(sensorDescriptor);
        verify(sensorDescriptor).name("Sonar i-Code");
    }

    @Test
    public void test_run_internal_icode_in_nominal_situation() {
        final ICodeSensor sensor = new ICodeSensor();

        final MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.launch", false);
        settings.setProperty("sonar.icode.embedded", true);
        context.setSettings(settings);

        try {
            sensor.execute(context);
            Assertions.assertTrue(true);
        } catch(final Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void test_normal_work() {
        final ICodeSensor sensor = new ICodeSensor();

        sensor.execute(context);

        Assert.assertNotNull(sensor);
        Assert.assertTrue(context.config().hasKey("sonar.icode.reports.path"));
    }

    @Test
    public void test_normal_work_with_icode_launch_failed() {
        final ICodeSensor sensor = new ICodeSensor();

        final MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.launch", true);
        settings.setProperty("sonar.icode.embedded", false);
        context.setSettings(settings);

        try {
            sensor.execute(context);
            Assertions.assertTrue(true);
        } catch(final Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void test_normal_work_with_icode_launch_success() {
        final ICodeSensor sensor = new ICodeSensor() {
            @Override
            protected int runICode(final String command) {
                return 0;
            }
        };

        final MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.launch", true);
        settings.setProperty("sonar.icode.embedded", false);
        context.setSettings(settings);

        sensor.execute(context);
        Assertions.assertTrue(true);
    }

    @Test
    public void test_get_report_file_success() {
        final ICodeSensor sensor = new ICodeSensor() {
            @Override
            protected int runICode(final String command) {
                return 0;
            }
        };

        final MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.launch", true);
        settings.setProperty("sonar.icode.embedded", false);
        settings.setProperty(ICodePluginProperties.REPORT_PATH_KEY, "../result.res");
        context.setSettings(settings);

        int result = sensor.getReportFiles(context.config(), fs).size();
        Assertions.assertEquals(1, result);
    }

    @Test
    public void test_get_report_file_failure() {
        final ICodeSensor sensor = new ICodeSensor() {
            @Override
            protected int runICode(final String command) {
                return 0;
            }
        };

        final MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.launch", true);
        settings.setProperty("sonar.icode.embedded", false);
        settings.setProperty(ICodePluginProperties.REPORT_PATH_KEY, "../ghost.res");
        context.setSettings(settings);

        int result = sensor.getReportFiles(context.config(), fs).size();
        Assertions.assertEquals(0, result);
    }

    @Test
    public void test_normal_work_with_icode_launch_other_exit() {
        final ICodeSensor sensor = new ICodeSensor() {
            @Override
            protected int runICode(final String command) {
                return 2;
            }
        };

        final MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.launch", true);
        settings.setProperty("sonar.icode.embedded", false);
        context.setSettings(settings);

        sensor.execute(context);
        Assertions.assertTrue(true);
    }

    @Test
    public void test_run_a_command() throws IOException, InterruptedException {
        final ICodeSensor sensor = new ICodeSensor();

        final int value = sensor.runICode("java -version");
        
        Assert.assertEquals(0, value);
    }

    @Test
    public void test_get_scanned_files() {
        final ICodeSensor sensor = new ICodeSensor();

        final AnalysisProject project = new AnalysisProject();
        final AnalysisFile file = new AnalysisFile();
        file.setFileName("clanhb.f");
        file.setLanguage("f77");

        project.setAnalysisFile(new AnalysisFile[]{file});

        Assert.assertNotNull(sensor);

        Map<String, InputFile> relevantFile = sensor.getScannedFiles(fs, project);

        Assert.assertEquals(1, relevantFile.size());
    }


    @Test
    public void test_get_scanned_files_f90() {
        final ICodeSensor sensor = new ICodeSensor();

        final AnalysisProject project = new AnalysisProject();
        final AnalysisFile file = new AnalysisFile();
        file.setFileName("clanhb.f90");
        file.setLanguage("f90");

        project.setAnalysisFile(new AnalysisFile[]{file});

        Assert.assertNotNull(sensor);

        Map<String, InputFile> relevantFile = sensor.getScannedFiles(fs, project);

        Assert.assertEquals(1, relevantFile.size());
    }


    @Test
    public void test_save_issue_with_unknown_file() {
        rule.setResult(new Result());
        rule.setAnalysisRuleId("SH.ERR.Help");
        rule.getResult().setFileName("lalalalalala.sh");
        rule.getResult().setResultValue("3");
        rule.getResult().setResultLine("110");
        rule.getResult().setResultTypePlace("class");
        rule.getResult().setResultMessage("Small file");

        ICodeSensor.saveIssue(context, files, rule);
        Assert.assertEquals(0, context.allIssues().size());
    }

    @Test
    public void test_check_rules_activation() {
        final ICodeSensor sensor = new ICodeSensor();

        final boolean active = sensor.isRuleActive(context.activeRules(), "No rules are activated");

        Assert.assertFalse(active);
    }

}
