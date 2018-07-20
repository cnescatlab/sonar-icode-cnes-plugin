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
import org.sonar.api.config.Configuration;

/**
 * Declares language Shell.
 *
 * @author lequal
 */
public final class ShellLanguage extends ICodeLanguage {

	/**
	 * Name of the language.
	 */
	public static final String NAME = "Shell";
	/**
	 * Key of the language.
	 */
	public static final String KEY = "shell";

	/**
	 * i-Code extension for i-Code specific properties, Metrics and Rules.
	 *
	 * @param configuration Inject SonarQube configuration into this extension
	 */
	public ShellLanguage(final Configuration configuration) {
		super(configuration, KEY, NAME);
	}

	/**
	 * Return the ey corresponding to the property with suffixes.
	 *
	 * @return A String with the key.
	 */
	@Override
	public String getSuffixKey() {
		return ICodePluginProperties.SHELL_SUFFIX_KEY;
	}

	/**
	 * Return default suffixes for the language.
	 *
	 * @return A String containing a coma-separated list.
	 */
	@Override
	public String getDefaultSuffixes() {
		return ICodePluginProperties.SHELL_SUFFIX_DEFAULT;
	}
}
