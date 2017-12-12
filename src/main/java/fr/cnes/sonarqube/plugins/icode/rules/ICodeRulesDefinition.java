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

package fr.cnes.sonarqube.plugins.icode.rules;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

import fr.cnes.sonarqube.plugins.icode.languages.ICodeLanguage;

/**
 * Specific ICode Rules definition provided by resource file
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class ICodeRulesDefinition implements RulesDefinition {

	static final String PATH_TO_RULES_XML = "/example/icode-rules.xml";

	public static final String KEY = "rules";
	
	public static final String REPO_KEY = ICodeLanguage.KEY + "-" + KEY;
	protected static final String REPO_NAME = ICodeLanguage.NAME;

	protected String rulesDefinitionFilePath() {
		return PATH_TO_RULES_XML;
	}

	private void defineRulesForLanguage(Context context, String repositoryKey, String repositoryName,
			String languageKey) {
		NewRepository repository = context.createRepository(repositoryKey, languageKey).setName(repositoryName);

		InputStream rulesXml = this.getClass().getResourceAsStream(rulesDefinitionFilePath());
		if (rulesXml != null) {
			RulesDefinitionXmlLoader rulesLoader = new RulesDefinitionXmlLoader();
			rulesLoader.load(repository, rulesXml, StandardCharsets.UTF_8.name());
		}
		repository.done();
	}

	@Override
	public void define(Context context) {
		defineRulesForLanguage(context, REPO_KEY, REPO_NAME, ICodeLanguage.KEY);
	}

	public static String getRepositoryKeyForLanguage() {
		    return ICodeLanguage.KEY + "-" + KEY;
	 }
	
	/** Check if the ruleKey exist. 
	 * 
	 * @param analysisRuleId
	 * @return Always true with ICode
	 */
	public static boolean existRule(String analysisRuleId){
		boolean res = true;
		if(analysisRuleId == null) return false;
		return res;
	}
}

