package fr.cnes.sonarqube.plugins.icode.languages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import fr.cnes.sonarqube.plugins.icode.settings.ICodeLanguageProperties;

public class ICodeLanguageTest {

	@Test
	public void given_settings_when_getFileSuffixes_then_settings() {
		String[] expected = new String[]{"*.c","*.i"};
		org.sonar.api.config.Settings settings = Mockito.mock(org.sonar.api.config.Settings.class);
		Mockito.when(settings.getStringArray(ICodeLanguageProperties.EXPECTED_REPORT_INPUT_FILE_TYPES_KEY))
		.thenReturn(new String[]{" "+expected[0]+" ", " "+expected[1]+" "});
		ICodeLanguage icodeLanguage = new ICodeLanguage(settings);
		assertEquals(expected, icodeLanguage.getFileSuffixes());
	}


}
