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
package fr.cnes.sonarqube.plugins.icode;

import org.sonar.api.Plugin;

import fr.cnes.sonarqube.plugins.icode.languages.ICodeLanguage;
import fr.cnes.sonarqube.plugins.icode.languages.ICodeQualityProfile;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF77CyclomaticStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF77LinesOfCodeStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF77NestingStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF77RatioCommentStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF90CyclomaticStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF90LinesOfCodeStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF90NestingStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleF90RatioCommentStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleSHELLCyclomaticStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleSHELLLinesOfCodeStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleSHELLNestingStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleSHELLRatioCommentStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputePluginErrors;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetrics;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77Cyclomatic;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77LinesOfCode;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77Nesting;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77RatioComment;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90Cyclomatic;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90LinesOfCode;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90Nesting;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90RatioComment;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLCyclomatic;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLLinesOfCode;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLNesting;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLRatioComment;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeSensor;
import fr.cnes.sonarqube.plugins.icode.rules.ICodeRulesDefinition;
import fr.cnes.sonarqube.plugins.icode.settings.ICodeLanguageProperties;
import fr.cnes.sonarqube.plugins.icode.web.ICodeDashboard;

/**
 * This class is the entry point for all extensions
 * 
 * @author Cyrille FRANCOIS
 */
public class ICodePlugin implements Plugin {

	public void define(Context context) {
		// Setting plugin ICode
		context.addExtensions(ICodeLanguage.class, ICodeQualityProfile.class);
		context.addExtensions(ICodeLanguageProperties.getProperties());

		// Metrics definition and computed measures
		context.addExtensions(ICodeMetrics.class, ComputePluginErrors.class,

				ICodeMetricsF77Cyclomatic.class, ComputeModuleF77CyclomaticStatistics.class,

				ICodeMetricsF77LinesOfCode.class, ComputeModuleF77LinesOfCodeStatistics.class,

				ICodeMetricsF77RatioComment.class, ComputeModuleF77RatioCommentStatistics.class,

				ICodeMetricsF77Nesting.class, ComputeModuleF77NestingStatistics.class,

				ICodeMetricsF90Cyclomatic.class, ComputeModuleF90CyclomaticStatistics.class,

				ICodeMetricsF90LinesOfCode.class, ComputeModuleF90LinesOfCodeStatistics.class,

				ICodeMetricsF90RatioComment.class, ComputeModuleF90RatioCommentStatistics.class,

				ICodeMetricsF90Nesting.class, ComputeModuleF90NestingStatistics.class,

				ICodeMetricsSHELLCyclomatic.class, ComputeModuleSHELLCyclomaticStatistics.class,

				ICodeMetricsSHELLLinesOfCode.class, ComputeModuleSHELLLinesOfCodeStatistics.class,

				ICodeMetricsSHELLRatioComment.class, ComputeModuleSHELLRatioCommentStatistics.class,

				ICodeMetricsSHELLNesting.class, ComputeModuleSHELLNestingStatistics.class

		);

		// Rules definition
		context.addExtension(ICodeRulesDefinition.class);
		
		// Dashboard
		context.addExtension(
				ICodeDashboard.class);
		
		// Sonar scanner extension
		context.addExtension(ICodeSensor.class);
	}
}
