/*
 * Example Plugin for SonarQube
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package fr.cnes.sonarqube.plugins.icode.rules;

import org.sonar.api.rule.RuleKey;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition;

/**
 * Specific ICode Rules definition provided by resource file 
 * 
 * @author Cyrille FRANCOIS
 *
 */
public final class ICodeRulesDefinition implements RulesDefinition {

  private static final String FORTRAN = "fortran";

  private static final String PATH_TO_RULES_XML = "/example/icode-rules.xml";

  protected static final String KEY = "icode";
  protected static final String NAME = "ICode";

  public static final String REPO_KEY = FORTRAN + "-" + KEY;
  protected static final String REPO_NAME = FORTRAN + "-" + NAME;
  
  public static final RuleKey RULE_CYCLO = RuleKey.of(REPO_KEY, "ComplexitySimplified");

  protected String rulesDefinitionFilePath() {
    return PATH_TO_RULES_XML;
  }

  private void defineRulesForLanguage(
		  Context context, 
		  String repositoryKey, 
		  String repositoryName, 
		  String languageKey) {
//    NewRepository repository = context.createRepository(repositoryKey, languageKey).setName(repositoryName);
//
//    InputStream rulesXml = this.getClass().getResourceAsStream(rulesDefinitionFilePath());
//    if (rulesXml != null) {
//      RulesDefinitionXmlLoader rulesLoader = new RulesDefinitionXmlLoader();
//      rulesLoader.load(repository, rulesXml, StandardCharsets.UTF_8.name());
//    }
//
//    repository.done();
	    NewRepository repository = context.createRepository(repositoryKey, languageKey).setName(repositoryName);

	    NewRule x1Rule = repository.createRule(RULE_CYCLO.rule())
	      .setName("Stupid rule")
	      .setHtmlDescription("Generates an issue on every line 1 of Java files")

	      // optional tags
	      .setTags("style", "stupid")

	      // optional status. Default value is READY.
	      .setStatus(RuleStatus.BETA)

	      // default severity when the rule is activated on a Quality profile. Default value is MAJOR.
	      .setSeverity(Severity.MINOR);

	    x1Rule.setDebtRemediationFunction(x1Rule.debtRemediationFunctions().linearWithOffset("1h", "30min"));

	    // don't forget to call done() to finalize the definition
	    repository.done();
  }

  @Override
  public void define(Context context) {
    defineRulesForLanguage(context, REPO_KEY, REPO_NAME, FORTRAN);
  }

}
