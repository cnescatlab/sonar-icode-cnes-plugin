package fr.cnes.sonarqube.plugins.icode.rules;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.server.rule.RulesDefinition.Context;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;

import fr.cnes.sonarqube.plugins.icode.languages.ICodeLanguage;

public class ICodeRulesDefinitionTest {

	
	@Test
	public void given_context_when_define_then_repositoryDone() {
		String repositoryKey = ICodeRulesDefinition.REPO_KEY;
		String languageKey = ICodeLanguage.KEY;
		String repositoryName = ICodeRulesDefinition.REPO_NAME;
		NewRepository repository = Mockito.mock(NewRepository.class);
		Context context = Mockito.mock(Context.class);
		Mockito.when(context.createRepository(repositoryKey, languageKey)).thenReturn(repository);
		Mockito.when(repository.setName(repositoryName)).thenReturn(repository);
		ICodeRulesDefinition iCodeRulesDefinition = new ICodeRulesDefinition(){
			protected String rulesDefinitionFilePath() {
				return "/default/bad-icode-rules.xml";
			}
		};
		iCodeRulesDefinition.define(context);
		Mockito.verify(repository).done();
	}
	
	@Test
	public void testRulesDefinitionFilePath(){
		ICodeRulesDefinition icodeRulesDefinition = new ICodeRulesDefinition();
		String expected = ICodeRulesDefinition.PATH_TO_RULES_XML;
		String actual = icodeRulesDefinition.rulesDefinitionFilePath();
		assertEquals(expected, actual);
	}

}
