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

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.sonar.api.internal.apachecommons.lang.StringUtils;
import org.sonar.api.internal.google.common.base.Objects;
import org.sonar.api.internal.google.common.collect.Maps;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRepositoryImpl;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.cnes.sonarqube.plugins.icode.languages.ICodeLanguage;

/**
 * Specific ICode Rules definition provided by resource file
 * 
 * @author Cyrille FRANCOIS
 *
 */
public final class ICodeRulesDefinition implements RulesDefinition {

	private static final String PATH_TO_RULES_XML = "/example/icode-rules.xml";

	public static final String KEY = "rules";
	
	public static final String REPO_KEY = ICodeLanguage.KEY + "-" + KEY;
	protected static final String REPO_NAME = ICodeLanguage.NAME;

	private static NewRepository repository;

	protected String rulesDefinitionFilePath() {
		return PATH_TO_RULES_XML;
	}

	private void defineRulesForLanguage(Context context, String repositoryKey, String repositoryName,
			String languageKey) {
		repository = context.createRepository(repositoryKey, languageKey).setName(repositoryName);

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
		return true;
	}
}

