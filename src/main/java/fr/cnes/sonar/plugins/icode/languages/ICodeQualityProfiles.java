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

import fr.cnes.sonar.plugins.icode.model.Rule;
import fr.cnes.sonar.plugins.icode.model.RulesDefinition;
import fr.cnes.sonar.plugins.icode.model.XmlHandler;
import fr.cnes.sonar.plugins.icode.rules.ICodeRulesDefinition;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.InputStream;

/**
 * Built-in quality profile format since SonarQube 6.6.
 */
public final class ICodeQualityProfiles implements BuiltInQualityProfilesDefinition {

    /** Logger for this class. **/
    private static final Logger LOGGER = Loggers.get(ICodeQualityProfiles.class);

    /** Display name for the built-in quality profile. **/
    private static final String I_CODE_RULES_PROFILE_NAME = "Sonar way";

    /**
     * Allow to create a plugin.
     *
     * @param context Context of the plugin.
     */
    @Override
    public void define(final Context context) {
        createBuiltInProfile(context, Fortran77Language.KEY, ICodeRulesDefinition.PATH_TO_F77_RULES_XML);
        createBuiltInProfile(context, Fortran90Language.KEY, ICodeRulesDefinition.PATH_TO_F90_RULES_XML);
    }

    /**
     * Create a built in quality profile for a specific language.
     *
     * @param context SonarQube context in which create the profile.
     * @param language Language key of the associated profile.
     * @param path Path to the xml definition of all rules.
     */
    private void createBuiltInProfile(final Context context, final String language, final String path) {
        // Create a builder for the rules' repository.
        final NewBuiltInQualityProfile defaultProfile =
                context.createBuiltInQualityProfile(I_CODE_RULES_PROFILE_NAME, language);

        // Retrieve all defined rules.
        final InputStream stream = getClass().getResourceAsStream(path);
        final RulesDefinition rules = (RulesDefinition) XmlHandler.unmarshal(stream, RulesDefinition.class);
        final String repositoryKey = ICodeRulesDefinition.getRepositoryKeyForLanguage(language);

        // Activate all i-Code CNES rules.
        for(final Rule rule : rules.getRules()) {
            defaultProfile.activateRule(repositoryKey, rule.getKey());
            LOGGER.debug(String.format("Rule %s added to repository %s.", rule.getKey(), repositoryKey));
        }
        LOGGER.debug(String.format("%s rules are activated.", defaultProfile.activeRules().size()));

        // Save the default profile.
        defaultProfile.setDefault(true);
        defaultProfile.done();
    }
}