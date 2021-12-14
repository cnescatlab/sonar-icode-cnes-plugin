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

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Configuration;
import org.sonar.api.resources.AbstractLanguage;

import java.util.ArrayList;
import java.util.List;

/**
 * Declared language i-Code as the parent language for Fortran 77, Fortran 90.
 */
public abstract class ICodeLanguage extends AbstractLanguage {

	/**
	 * Injected SonarQube configuration.
	 */
	private final Configuration configuration;

	/**
	 * i-Code extension for i-Code specific properties, Metrics and Rules.
	 *
	 * @param configuration Inject SonarQube configuration into this extension.
	 * @param key Key of the language to set.
	 * @param name Name of the language to set.
	 */
	public ICodeLanguage(final Configuration configuration, final String key, final String name) {
		super(key, name);
		this.configuration = configuration;
	}

	/**
	 * Returns the list of suffixes which should be associated to this language.
	 *
	 * @return A strings' array with file's suffixes.
	 */
	@Override
	public String[] getFileSuffixes() {
		String[] suffixes = filterEmptyStrings(configuration.getStringArray(getSuffixKey()));
		if (suffixes.length == 0) {
			suffixes = filterEmptyStrings(getDefaultSuffixes().split(","));
		}
		return suffixes;
	}

	/**
	 * Return the ey corresponding to the property with suffixes.
	 *
	 * @return A String with the key.
	 */
	public abstract String getSuffixKey();

	/**
	 * Return default suffixes for the language.
	 *
	 * @return A String containing a coma-separated list.
	 */
	public abstract String getDefaultSuffixes();

	/**
	 * Delete all empty string values into a input String array.
	 * 
	 * @param stringArray Input String array.
	 *
	 * @return Output String array without empty string values.
	 */
    public static String[] filterEmptyStrings(final String[] stringArray) {
        List<String> nonEmptyStrings = new ArrayList<>();
        for (final String string : stringArray) {
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
	public boolean equals(final Object obj) {
		return obj==this;
	}

	/**
	 * Override hashcode because equals is overridden.
	 *
	 * @return An integer hashcode.
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
