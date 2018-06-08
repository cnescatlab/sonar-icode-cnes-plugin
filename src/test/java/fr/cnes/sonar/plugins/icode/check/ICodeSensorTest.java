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

import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import fr.cnes.sonar.plugins.icode.model.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.config.Configuration;
import org.sonar.api.config.internal.MapSettings;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class ICodeSensorTest {

    private DefaultFileSystem fs;
    private SensorContextTester context;
    private Map<String, InputFile> files;

    private DefaultInputFile bash_sh;
    private DefaultInputFile clanhb_f;
    private DefaultInputFile example_res;
    private AnalysisRule rule;

    @Before
    public void prepare() throws URISyntaxException {
        fs = new DefaultFileSystem(new File(ICodeSensor.class.getResource("/TestsPluginICode/metrics/").toURI()));
        fs.setEncoding(Charset.forName("UTF-8"));

        bash_sh = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath("bash.sh").getPath())
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN)
                .setLines(10)
                .setOriginalLineOffsets(new int[]{0,0,0,0,0,0,0,0,0,0})
                .setLastValidOffset(100)
                .setContents("blablabla\nblablabla\nblablabla\nblablabla\nblablabla\nblablabla\nblablabla\nblablabla\nblablabla\n")
                .build();
        fs.add(bash_sh);
        clanhb_f = TestInputFileBuilder.create(
                "ProjectKey",
                fs.resolvePath("clanhb.f").getPath())
                .setLanguage("icode")
                .setType(InputFile.Type.MAIN).setLines(10)
                .setLines(10)
                .setOriginalLineOffsets(new int[]{0,0,0,0,0,0,0,0,0,0})
                .setLastValidOffset(100)
                .build();
        fs.add(clanhb_f);

        context = SensorContextTester.create(fs.baseDir());
        files = new HashMap<>();
        rule = new AnalysisRule();

        files.put("bash.sh", bash_sh);
        files.put("clanhb.f", clanhb_f);

        context = SensorContextTester.create(fs.baseDir());
        context.setFileSystem(fs);
        MapSettings settings = new MapSettings();
        settings.setProperty("sonar.icode.reports.path", "example.res");
        context.setSettings(settings);
    }

	@Test
	public void given_sensorDescriptor_when_describe_then_callSensorDescriptorName() {
		SensorDescriptor sensorDescriptor = Mockito.mock(SensorDescriptor.class);
		ICodeSensor icodeMetricsSensor = new ICodeSensor();
		icodeMetricsSensor.describe(sensorDescriptor);
		verify(sensorDescriptor).name(ICodeSensor.class.getName());
	}

	@Test
	public void test_normal_work() {
		final ICodeSensor sensor = new ICodeSensor();

		sensor.execute(context);

		Assert.assertNotNull(sensor);
		Assert.assertTrue(context.config().hasKey("sonar.icode.reports.path"));
	}

	@Test
    public void test_save_issue() {
        rule.result = new Result();
        rule.analysisRuleId = "SH.ERR.Help";
        rule.result.fileName = "bash.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "4";
        rule.result.resultTypePlace = "class";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";

        ICodeSensor.saveIssue(context, files, rule);
        Assert.assertEquals(1, context.allIssues().size());
    }

    @Test
    public void test_save_issue_with_unknown_file() {
        rule.result = new Result();
        rule.analysisRuleId = "SH.ERR.Help";
        rule.result.fileName = "lalalalalala.sh";
        rule.result.resultValue = "3";
        rule.result.resultLine = "110";
        rule.result.resultTypePlace = "class";
        rule.result.resultNamePlace = "yolo";
        rule.result.resultId = "11";
        rule.result.resultMessage = "Small file";

        ICodeSensor.saveIssue(context, files, rule);
        Assert.assertEquals(0, context.allIssues().size());
    }

}
