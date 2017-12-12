package fr.cnes.sonarqube.plugins.icode.settings;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.sonar.api.config.PropertyDefinition;

import fr.cnes.sonarqube.plugins.icode.languages.ICodeLanguage;

public class ICodeLanguagePropertiesTest {

	@Test
	public void test() {
		List<PropertyDefinition> actual = ICodeLanguageProperties.getProperties();
		assertEquals(3, actual.size());
		PropertyDefinition reportSubDirectory = actual.get(0);
		assertEquals(ICodeLanguage.NAME, reportSubDirectory.category());
		assertEquals(ICodeLanguageProperties.REPORT_SUBDIR_KEY, reportSubDirectory.key());
		assertEquals(ICodeLanguageProperties.REPORT_SUBDIR_DEFAULT_VALUE, reportSubDirectory.defaultValue());
		PropertyDefinition reportExtensions = actual.get(1);
		assertEquals(ICodeLanguage.NAME, reportExtensions.category());
		assertEquals(ICodeLanguageProperties.REPORT_OUT_EXT_KEY, reportExtensions.key());
		assertEquals(ICodeLanguageProperties.REPORT_OUT_EXT_DEFAULT_VALUE, reportExtensions.defaultValue());
		PropertyDefinition reportInputFileType = actual.get(2);
		assertEquals(ICodeLanguage.NAME, reportInputFileType.category());
		assertEquals(ICodeLanguageProperties.EXPECTED_REPORT_INPUT_FILE_TYPES_KEY, reportInputFileType.key());
		assertEquals(ICodeLanguageProperties.EXPECTED_REPORT_INPUT_FILE_TYPES_DEFAULT_VALUE, reportInputFileType.defaultValue());
	}

}
