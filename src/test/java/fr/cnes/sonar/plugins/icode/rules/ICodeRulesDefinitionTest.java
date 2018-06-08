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
import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.server.rule.RulesDefinition.Context;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;

import static org.junit.Assert.assertEquals;

public class ICodeRulesDefinitionTest {

	
	@Test
	public void given_context_when_define_then_repositoryDone() {
		String repositoryKey = ICodeRulesDefinition.getRepositoryKeyForLanguage();
		String languageKey = ICodeLanguage.KEY;
		String repositoryName = ICodeRulesDefinition.getRepositoryName();
		NewRepository repository = Mockito.mock(NewRepository.class);
		Context context = Mockito.mock(Context.class);
		Mockito.when(context.createRepository(repositoryKey, languageKey)).thenReturn(repository);
		Mockito.when(repository.setName(repositoryName)).thenReturn(repository);
		ICodeRulesDefinition iCodeRulesDefinition = new ICodeRulesDefinition(){
			public String rulesDefinitionFilePath() {
				return "/default/bad-icode-rules.xml";
			}
		};
		iCodeRulesDefinition.define(context);
		Mockito.verify(repository).done();
	}
	
	@Test
	public void testRulesDefinitionFilePath(){
		ICodeRulesDefinition icodeRulesDefinition = new ICodeRulesDefinition();
		String expected = new ICodeRulesDefinition().rulesDefinitionFilePath();
		String actual = icodeRulesDefinition.rulesDefinitionFilePath();
		assertEquals(expected, actual);
	}

}
