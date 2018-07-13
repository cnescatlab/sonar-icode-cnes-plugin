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
import fr.cnes.sonar.plugins.icode.model.XMLHandler;
import fr.cnes.sonar.plugins.icode.rules.ICodeRulesDefinition;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;

/**
 * Built-in quality profile format since SonarQube 6.6.
 *
 * @author lequal
 */
public final class ICodeQualityProfile implements BuiltInQualityProfilesDefinition {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Loggers.get(ICodeQualityProfile.class);

    /** Display name for the built-in quality profile. **/
    private static final String I_CODE_RULES_PROFILE_NAME = "Sonar way";

    /**
     * Allow to create a plugin.
     *
     * @param context Context of the plugin.
     */
    @Override
    public void define(Context context) {

        // Create a builder for the rules' repository.
        final NewBuiltInQualityProfile defaultProfile =
                context.createBuiltInQualityProfile(I_CODE_RULES_PROFILE_NAME, ICodeLanguage.KEY);

        try {
            // Retrieve all defined rules.
            final InputStream stream = getClass().getResourceAsStream(ICodeRulesDefinition.PATH_TO_RULES_XML);
            final RulesDefinition rules = (RulesDefinition) XMLHandler.unmarshal(stream, RulesDefinition.class);
            // Activate all i-Code CNES rules.
            for(final Rule rule : rules.getRules()) {
                defaultProfile.activateRule(ICodeRulesDefinition.getRepositoryKeyForLanguage(), rule.key);
            }
        } catch (JAXBException e) {
            LOGGER.warn(e.getLocalizedMessage(), e);
        }
        // Save the default profile.
        defaultProfile.setDefault(true);
        defaultProfile.done();
    }
}