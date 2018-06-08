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
package fr.cnes.sonar.plugins.icode.settings;

import fr.cnes.sonar.plugins.icode.languages.ICodeLanguage;
import org.sonar.api.config.PropertyDefinition;

import java.util.List;

import static java.util.Arrays.asList;


public class ICodePluginProperties {

    /** Prefix used by all properties of this plugin. **/
	private static final String PROPERTIES_PREFIX = "sonar.icode.";
	
	// project code file patterns
    /** Key for the code suffix property **/
	public static final String CODE_SUFFIX_KEY = PROPERTIES_PREFIX + "file.suffixes";
    /** Default value for the code suffix property **/
	public static final String CODE_SUFFIX_DEFAULT = ".f,.f77,.f90,.F,.F77,.F90,.sh";
    /** Name for the code suffix property **/
	public static final String CODE_SUFFIX_NAME = "File Suffixes";
    /** Description for the code suffix property **/
    public static final String CODE_SUFFIX_DESC = "List of suffixes for files to analyze.";

	// Reports path
	/** Key for the report path property **/
	public static final String REPORT_PATH_KEY = PROPERTIES_PREFIX + "reports.path";
	/** Name for the report path property **/
	public static final String REPORT_PATH_NAME = "Report files";
	/** Description for the report path property **/
	public static final String REPORT_PATH_DESC = "Path to the i-Code reports. Multiple path can be provided.";
	/** Default value for the report path property **/
	public static final String REPORT_PATH_DEFAULT = "result.res";

	private ICodePluginProperties() {
		super();
	}

	/**
	 * Plugin properties extensions
	 */
	public static List<PropertyDefinition> getProperties() {
		return asList(
				PropertyDefinition.builder(CODE_SUFFIX_KEY).multiValues(true)
				.defaultValue(CODE_SUFFIX_DEFAULT).category(ICodeLanguage.NAME)
				.name(CODE_SUFFIX_NAME).description(CODE_SUFFIX_DESC)
				.build(),
				PropertyDefinition.builder(REPORT_PATH_KEY).multiValues(true)
				.defaultValue(REPORT_PATH_DEFAULT).category(ICodeLanguage.NAME)
				.name(REPORT_PATH_NAME).description(REPORT_PATH_DESC)
				.build());
	}

}
