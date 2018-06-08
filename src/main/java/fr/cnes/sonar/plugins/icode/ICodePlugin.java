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
package fr.cnes.sonar.plugins.icode;

import fr.cnes.sonar.plugins.icode.check.ICodeSensor;
import fr.cnes.sonar.plugins.icode.languages.ICodeLanguage;
import fr.cnes.sonar.plugins.icode.languages.ICodeQualityProfile;
import fr.cnes.sonar.plugins.icode.measures.ICodeNestingMetric;
import fr.cnes.sonar.plugins.icode.rules.ICodeRulesDefinition;
import fr.cnes.sonar.plugins.icode.settings.ICodePluginProperties;
import org.sonar.api.Plugin;

/**
 * This class is the entry point for all extensions
 * 
 * @author Cyrille FRANCOIS
 */
public class ICodePlugin implements Plugin {

	public void define(Context context) {
		// Setting plugin ICode
		context.addExtensions(ICodeLanguage.class, ICodeQualityProfile.class);
		context.addExtensions(ICodePluginProperties.getProperties());

		// Metrics definition and computed measures
		context.addExtension(
				ICodeNestingMetric.class
		);

		// Rules definition
		context.addExtension(ICodeRulesDefinition.class);
		
		// Sonar scanner extension
		context.addExtension(ICodeSensor.class);
	}
}
