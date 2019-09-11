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

import org.sonar.api.PropertyType;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import java.util.Arrays;
import java.util.List;

/**
 * Define all SonarQube properties provided by this plugin.
 */
public class ICodePluginProperties {


    /**
     * i-Code CNES default location's path value.
     */
    public static final String ICODE_PATH_DEFAULT = "/usr/bin/icodegit";

    /**
     * Prefix used by all properties of this plugin.
     **/
    private static final String PROPERTIES_PREFIX = "sonar.icode.";

    /**
     * i-Code name.
     **/
    public static final String ICODE_NAME = "i-Code CNES";

    // project code file patterns
    /**
     * Key for the Shell suffix property
     **/
    public static final String SHELL_SUFFIX_KEY = PROPERTIES_PREFIX + "shell.file.suffixes";
    /**
     * Default value for the code suffix property
     **/
    public static final String SHELL_SUFFIX_DEFAULT = ".sh,.ksh,.bash";
    /**
     * Name for the code suffix property
     **/
    public static final String SHELL_SUFFIX_NAME = "Shell File Suffixes";
    /**
     * Description for the code suffix property
     **/
    public static final String SHELL_SUFFIX_DESC = "List of suffixes for Shell files to analyze.";

    // project code file patterns
    /**
     * Key for the F77 suffix property
     **/
    public static final String F77_SUFFIX_KEY = PROPERTIES_PREFIX + "f77.file.suffixes";
    /**
     * Default value for the code suffix property
     **/
    public static final String F77_SUFFIX_DEFAULT = ".f,.f77,.for,.fpp,.ftn,.F,.F77,.FOR,.FPP,.FTN";
    /**
     * Name for the code suffix property
     **/
    public static final String F77_SUFFIX_NAME = "Fortran 77 File Suffixes";
    /**
     * Description for the code suffix property
     **/
    public static final String F77_SUFFIX_DESC = "List of suffixes for Fortran 77 files to analyze.";

    // project code file patterns
    /**
     * Key for the F90 suffix property
     **/
    public static final String F90_SUFFIX_KEY = PROPERTIES_PREFIX + "f90.file.suffixes";
    /**
     * Default value for the code suffix property
     **/
    public static final String F90_SUFFIX_DEFAULT = ".f90,.F90";
    /**
     * Name for the code suffix property
     **/
    public static final String F90_SUFFIX_NAME = "Fortran 90 File Suffixes";
    /**
     * Description for the code suffix property
     **/
    public static final String F90_SUFFIX_DESC = "List of suffixes for Fortran 90 files to analyze.";

    // Reports path
    /**
     * Key for the report path property
     **/
    public static final String REPORT_PATH_KEY = PROPERTIES_PREFIX + "reports.path";
    /**
     * Name for the report path property
     **/
    public static final String REPORT_PATH_NAME = "Report files";
    /**
     * Description for the report path property
     **/
    public static final String REPORT_PATH_DESC = "Path to the i-Code reports. Multiple path can be provided.";
    /**
     * Default value for the report path property
     **/
    public static final String REPORT_PATH_DEFAULT = "result.res";
    /**
     * i-Code CNES launching mode key
     */
    public static final String AUTOLAUNCH_PROP_KEY = PROPERTIES_PREFIX + "launch";
    /**
     * i-Code CNES launching mode default value
     */
    public static final String AUTOLAUNCH_PROP_DEFAULT = "false";
    /**
     * Launching mode name
     */
    public static final String AUTOLAUNCH_PROP_NAME = "i-Code CNES auto-launch";
    /**
     * Launching mode description
     */
    public static final String AUTOLAUNCH_PROP_DESC = "Auto-launch i-Code CNES on analysis using indicated location.";
    /**
     * i-Code CNES location's path key
     */
    public static final String ICODE_PATH_KEY = PROPERTIES_PREFIX + "path";
    /**
     * i-Code CNES location's path key
     */
    public static final String ICODE_PATH_NAME = "i-Code CNES location";
    /**
     * i-Code CNES location's path key
     */
    public static final String ICODE_PATH_DESC = "Define i-Code CNES executable path to auto-launch it on analysis.";

    private ICodePluginProperties() {
        super();
    }

    /**
     * Plugin properties extensions.
     *
     * @return The list of built properties.
     */
    public static List<PropertyDefinition> getProperties() {
        return Arrays.asList(
            PropertyDefinition.builder(AUTOLAUNCH_PROP_KEY)
                    .defaultValue(AUTOLAUNCH_PROP_DEFAULT)
                    .category(ICODE_NAME)
                    .name(AUTOLAUNCH_PROP_NAME)
                    .description(AUTOLAUNCH_PROP_DESC)
                    .type(PropertyType.BOOLEAN)
                    .onQualifiers(Qualifiers.PROJECT)
                    .build()
            ,
            PropertyDefinition.builder(ICODE_PATH_KEY)
                    .defaultValue(ICODE_PATH_DEFAULT)
                    .category(ICODE_NAME)
                    .name(ICODE_PATH_NAME)
                    .description(ICODE_PATH_DESC)
                    .onQualifiers(Qualifiers.PROJECT)
                    .build()
            ,
            PropertyDefinition.builder(SHELL_SUFFIX_KEY).multiValues(true)
                    .defaultValue(SHELL_SUFFIX_DEFAULT).category(ICODE_NAME)
                    .name(SHELL_SUFFIX_NAME).description(SHELL_SUFFIX_DESC)
                    .onQualifiers(Qualifiers.PROJECT)
                    .build()
            ,
            PropertyDefinition.builder(F77_SUFFIX_KEY).multiValues(true)
                    .defaultValue(F77_SUFFIX_DEFAULT).category(ICODE_NAME)
                    .name(F77_SUFFIX_NAME).description(F77_SUFFIX_DESC)
                    .onQualifiers(Qualifiers.PROJECT)
                    .build()
            ,
            PropertyDefinition.builder(F90_SUFFIX_KEY).multiValues(true)
                    .defaultValue(F90_SUFFIX_DEFAULT).category(ICODE_NAME)
                    .name(F90_SUFFIX_NAME).description(F90_SUFFIX_DESC)
                    .onQualifiers(Qualifiers.PROJECT)
                    .build()
            ,
            PropertyDefinition.builder(REPORT_PATH_KEY).multiValues(true)
                    .defaultValue(REPORT_PATH_DEFAULT).category(ICODE_NAME)
                    .name(REPORT_PATH_NAME).description(REPORT_PATH_DESC)
                    .onQualifiers(Qualifiers.PROJECT)
                    .build());
    }

}
