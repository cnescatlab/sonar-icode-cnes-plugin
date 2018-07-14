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
package fr.cnes.sonar.plugins.icode.languages;

import fr.cnes.sonar.plugins.icode.settings.ICodePluginProperties;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Configuration;
import org.sonar.api.resources.AbstractLanguage;

import java.util.ArrayList;
import java.util.List;

/**
 * Declared language i-Code corresponding to Fortran 77, Fortran 90 & Shell.
 *
 * @author lequal
 */
public final class ICodeLanguage extends AbstractLanguage {

	/**
	 * Name of the language.
	 */
	public static final String NAME = "i-Code";
	/**
	 * Key of the language.
	 */
	public static final String KEY = "icode";

	/**
	 * Injected SonarQube configuration.
	 */
	private final Configuration configuration;

	/**
	 * i-Code extension for i-Code specific properties, Metrics and Rules.
	 * 
	 * @param configuration Inject SonarQube configuration into this extension
	 */
	public ICodeLanguage(Configuration configuration) {
		super(KEY, NAME);
		this.configuration = configuration;
	}

	/**
	 * Returns the list of suffixes which should be associated to this language.
	 *
	 * @return A strings' array with file's suffixes.
	 */
	@Override
	public String[] getFileSuffixes() {
		String[] suffixes = filterEmptyStrings(
				configuration.getStringArray(ICodePluginProperties.CODE_SUFFIX_KEY));
		if (suffixes.length == 0) {
			suffixes = ICodePluginProperties.CODE_SUFFIX_DEFAULT
					.split(",");
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
    private static String[] filterEmptyStrings(String[] stringArray) {
        List<String> nonEmptyStrings = new ArrayList<>();
        for (String string : stringArray) {
            if (StringUtils.isNotBlank(string.trim())) {
                nonEmptyStrings.add(string.trim());
            }
        }
        return nonEmptyStrings.toArray(new String[nonEmptyStrings.size()]);
    }

	/**
	 * Assert obj is the same object as this.
	 *
	 * @param obj Object to compare with this.
	 * @return True if obj is this.
	 */
	@Override
	public boolean equals(Object obj) {
		return obj==this;
	}
}
