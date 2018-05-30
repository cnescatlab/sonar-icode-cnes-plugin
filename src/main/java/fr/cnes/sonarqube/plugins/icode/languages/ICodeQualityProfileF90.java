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

package fr.cnes.sonarqube.plugins.icode.languages;

import java.util.Collection;
import java.util.List;

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

import fr.cnes.sonarqube.plugins.icode.rules.ICodeRulesDefinition;

/**
 * Default Quality profile for the projects having files of language "icode"
 */
public final class ICodeQualityProfileF90 implements BuiltInQualityProfilesDefinition  {
	 static final String I_CODE_RULES_PROFILE_NAME = "ICode Rules F90";

	@Override
	public void define(Context context) {
		NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile(
				I_CODE_RULES_PROFILE_NAME, ICodeLanguage.KEY);
		
		// Get rules repository
		List<String> allKeys = ICodeRulesDefinition.getDefaultRuleKeys();
		for (String ruleKey : allKeys) {
			if(!ruleKey.startsWith("F77") && !ruleKey.startsWith("SH")) {
				profile.activateRule(ICodeRulesDefinition.REPO_KEY, ruleKey);
			}
		}
		
		profile.done();
		
	}

}