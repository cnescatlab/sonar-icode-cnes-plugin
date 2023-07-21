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

import org.sonar.api.rule.RuleKey;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.cnes.sonar.plugins.icode.languages.Fortran77Language;
import fr.cnes.sonar.plugins.icode.languages.Fortran90Language;
import fr.cnes.sonar.plugins.icode.settings.ICodePluginProperties;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Specific i-Code rules definition provided by resource file.
 */
public class ICodeRulesDefinition implements RulesDefinition {

	/** Logger for this class. **/
    private static final Logger LOGGER = Loggers.get(ICodeRulesDefinition.class);

	/** Partial key for repository. **/
	private static final String REPO_KEY_SUFFIX = "-rules";

	public static final String FORTRAN77_LANGUAGE = Fortran77Language.KEY;
	public static final String FORTRAN90_LANGUAGE = Fortran90Language.KEY;

	public static final String FORTRAN77_REPOSITORY = FORTRAN77_LANGUAGE + REPO_KEY_SUFFIX;
	public static final String FORTRAN90_REPOSITORY = FORTRAN90_LANGUAGE + REPO_KEY_SUFFIX;

	/** Path to xml file in resources tree (fortran 77 rules). **/
	public static final String PATH_TO_F77_RULES_XML = "/rules/icode-f77-rules.xml";

	/** Path to xml file in resources tree (fortran 90 rules). **/
	public static final String PATH_TO_F90_RULES_XML = "/rules/icode-f90-rules.xml";

	public static List<NewRule> f77Rules;
	public static List<NewRule> f90Rules;

	/**
	 * Define i-Code rules in SonarQube thanks to xml configuration files.
	 *
	 * @param context SonarQube context.
	 */
	@Override
	public void define(final Context context) {
		this.f77Rules = createFortranRepository(context, FORTRAN77_LANGUAGE, FORTRAN77_REPOSITORY, PATH_TO_F77_RULES_XML);
		this.f90Rules = createFortranRepository(context, FORTRAN90_LANGUAGE, FORTRAN90_REPOSITORY, PATH_TO_F90_RULES_XML);
	}

	/**
	 * Create repositories for each language f77 and f90
	 * 
	 * @param context SonarQube context.
	 * @param language Key of the language.
	 * @param repositoryName Key of the repository.
	 * @param pathToRulesXml Path to the xml file containing the rules.
	 */
	 protected List<NewRule> createFortranRepository(final Context context, final String language, final String repositoryName, final String pathToRulesXml) {
		// Create the repository
		NewRepository repository = context.createRepository(repositoryName, language)
		.setName(ICodePluginProperties.ICODE_NAME);

		List<NewRule> rules = new ArrayList<>();

		try {
			InputStream inputFile = this.getClass().getResourceAsStream(pathToRulesXml);
			
			if (inputFile == null) {
				repository.done();
				return rules;
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			Document doc = dbFactory.newDocumentBuilder().parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("rule");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					String key = element.getElementsByTagName("key").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String internalKey = element.getElementsByTagName("internalKey").item(0).getTextContent();
					String description = element.getElementsByTagName("description").item(0).getTextContent();
					String severity = element.getElementsByTagName("severity").item(0).getTextContent();
					RuleStatus status = RuleStatus.valueOf(element.getElementsByTagName("status").item(0).getTextContent());
					RuleType type = RuleType.valueOf(element.getElementsByTagName("type").item(0).getTextContent());
					String remediationFunctionBaseEffort = element.getElementsByTagName("remediationFunctionBaseEffort").item(0).getTextContent();

					RuleKey ruleKey = RuleKey.of(repositoryName, key);

					NewRule rule = repository.createRule(ruleKey.rule())
						.setName(name)
						.setInternalKey(internalKey)
						.setHtmlDescription(description)
						.setSeverity(severity)
						.setStatus(status)
						.setType(type);
					
					rule.setDebtRemediationFunction(rule.debtRemediationFunctions().constantPerIssue(remediationFunctionBaseEffort));
					rules.add(rule);
					LOGGER.info(String.format("Rule %s created.", rule.toString()));
				}
			}
			repository.done();
		} catch (Exception e) {
			LOGGER.error("Error while creating rules.", e);
		}
		return rules;
	}

	/**
     * Getter for repository key.
	 *
	 * @param language Key of the related language.
     * @return A string "language-key".
     */
    public static String getRepositoryKeyForLanguage(final String language) {
        return language + REPO_KEY_SUFFIX;
    }
}