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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.cnes.sonar.plugins.icode.rules.ICodeRulesDefinition;
import fr.cnes.sonar.plugins.icode.rules.RulesRepository;

/**
 * Built-in quality profile format since SonarQube 9.9
 */
public final class ICodeQualityProfiles implements BuiltInQualityProfilesDefinition {

    /** Logger for this class. **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ICodeQualityProfiles.class);

    /** Display name for the built-in quality profile. **/
    private static final String I_CODE_RULES_PROFILE_NAME = "Sonar way";

    private List<NewRule> f77Rules = RulesRepository.getInstance().getF77Rules();
    private List<NewRule> f90Rules = RulesRepository.getInstance().getF90Rules();

    /**
     * Allow to create a plugin.
     *
     * @param context Context of the plugin.
     */
    @Override
    public void define(final Context context) {
        createBuiltInProfile(context, ICodeRulesDefinition.FORTRAN77_REPOSITORY, Fortran77Language.KEY, f77Rules);
        createBuiltInProfile(context, ICodeRulesDefinition.FORTRAN90_REPOSITORY, Fortran90Language.KEY, f90Rules);
    }

    /**
     * Create a built in quality profile for a specific language.
     *
     * @param context    SonarQube context in which create the profile.
     * @param repository Rules' repository.
     * @param language   Language key of the associated profile.
     * @param rules      Rules to activate.
     */
    private void createBuiltInProfile(final Context context, final String repository, final String languageKey,
            final List<NewRule> rules) {
        // Create a builder for the rules' repository.
        NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile(I_CODE_RULES_PROFILE_NAME, languageKey);

        // Activate all i-Code CNES rules.
        for (NewRule rule : rules) {
            profile.activateRule(repository, rule.key());
            LOGGER.info(String.format("Rule %s added to repository %s.", rule.key(), repository));
        }
        profile.setDefault(true);
        profile.done();
        LOGGER.info(String.format("%s rules are activated for the repository %s.", profile.activeRules().size(),
                repository));
    }
}