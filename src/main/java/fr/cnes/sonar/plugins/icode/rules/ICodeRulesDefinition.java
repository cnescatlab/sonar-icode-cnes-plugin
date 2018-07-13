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
package fr.cnes.sonar.plugins.icode.rules;

import fr.cnes.sonar.plugins.icode.languages.ICodeLanguage;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Specific i-Code rules definition provided by resource file.
 *
 * @author Cyrille FRANCOIS
 */
public class ICodeRulesDefinition implements RulesDefinition {

	/** Partial key for repository. **/
	private static final String KEY = "rules";

	/** Path to xml file in resources tree. **/
	public static final String PATH_TO_RULES_XML = "/rules/icode-rules.xml";

	@Override
	public void define(Context context) {
		final NewRepository repository = context
                .createRepository(getRepositoryKeyForLanguage(), ICodeLanguage.KEY)
                .setName(getRepositoryName());

		final InputStream rulesXml = this.getClass().getResourceAsStream(rulesDefinitionFilePath());
		if (rulesXml != null) {
			final RulesDefinitionXmlLoader rulesLoader = new RulesDefinitionXmlLoader();
			rulesLoader.load(repository, rulesXml, StandardCharsets.UTF_8.name());
		}
		repository.done();
	}

    /**
     * Getter for repository key.
     *
     * @return A string "language-key".
     */
    public static String getRepositoryKeyForLanguage() {
        return ICodeLanguage.KEY + "-" + KEY;
    }

    /**
     * Getter for repository name.
     *
     * @return A string.
     */
    public static String getRepositoryName() {
        return ICodeLanguage.NAME;
    }

    /**
     * Getter for the path to rules file.
     *
     * @return A path in String format.
     */
	public String rulesDefinitionFilePath() {
		return PATH_TO_RULES_XML;
	}
}

