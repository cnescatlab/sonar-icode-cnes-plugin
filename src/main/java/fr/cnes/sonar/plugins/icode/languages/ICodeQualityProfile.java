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

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

/**
 * Built-in quality profile format since SonarQube 6.6.
 *
 * @author lequal
 */
public final class ICodeQualityProfile implements BuiltInQualityProfilesDefinition {

    /** Display name for the built-in quality profile. **/
    private static final String I_CODE_RULES_PROFILE_NAME = "Built-in i-Code Quality Profile";

    /**
     * Allow to create a plugin.
     *
     * @param context Context of the plugin.
     */
    @Override
    public void define(Context context) {
        context.createBuiltInQualityProfile(I_CODE_RULES_PROFILE_NAME, ICodeLanguage.KEY);
    }
}