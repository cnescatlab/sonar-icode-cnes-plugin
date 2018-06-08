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

public final class ICodeLanguage extends AbstractLanguage {
	public static final String NAME = "i-Code";
	public static final String KEY = "icode";

	private final Configuration configuration;

	/**
	 * ICode extension for ICode specific properties, Metrics and Rules.
	 * 
	 * @param configuration Inject SonarQube configuration into this extension
	 */
	public ICodeLanguage(Configuration configuration) {
		super(KEY, NAME);
		this.configuration = configuration;
	}

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
}
