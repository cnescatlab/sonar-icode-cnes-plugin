package fr.cnes.sonarqube.plugins.icode.languages;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

import fr.cnes.sonarqube.plugins.icode.settings.ICodeLanguageProperties;

public final class ICodeLanguage extends AbstractLanguage {
	public static final String NAME = "ICode";
	public static final String KEY = "icode";

	private final Settings settings;

	/**
	 * ICode extension for ICode specific properties, Metrics and Rules.
	 * 
	 * @param settings Inject Sonar settings into this extension
	 */
	public ICodeLanguage(Settings settings) {
		super(KEY, NAME);
		this.settings = settings;
	}

	@Override
	public String[] getFileSuffixes() {
		String[] suffixes = filterEmptyStrings(
				settings.getStringArray(ICodeLanguageProperties.EXPECTED_REPORT_INPUT_FILE_TYPES_KEY));
		if (suffixes.length == 0) {
			suffixes = ICodeLanguageProperties.EXPECTED_REPORT_INPUT_FILE_TYPES_DEFAULT_VALUE
					.split(ICodeLanguageProperties.FILE_SUFFIXES_SEPARATOR);
		}
		return suffixes;
	}

	/**
	 * Delete all empty string values into a input String array
	 * 
	 * @param stringArray Input String array
	 * 
	 * @return Output String array without empty string values
	 */
	private String[] filterEmptyStrings(String[] stringArray) {
		List<String> nonEmptyStrings = new ArrayList<>();
		for (String string : stringArray) {

			// Add only not empty string values
			if (!(string.trim()).isEmpty()) {
				nonEmptyStrings.add(string.trim());
			}
		}
		return nonEmptyStrings.toArray(new String[nonEmptyStrings.size()]);
	}
}
