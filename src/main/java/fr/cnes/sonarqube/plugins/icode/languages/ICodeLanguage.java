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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((settings == null) ? 0 : settings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ICodeLanguage other = (ICodeLanguage) obj;
		if (settings == null) {
			if (other.settings != null)
				return false;
		} else if (!settings.equals(other.settings))
			return false;
		return true;
	}
}
