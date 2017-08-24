package fr.cnes.sonarqube.plugins.icode;

import org.sonar.api.Plugin;

import fr.cnes.sonarqube.plugins.icode.measures.ComputeModuleComplexityStatistics;
import fr.cnes.sonarqube.plugins.icode.measures.ComputePluginErrors;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeMetrics;
import fr.cnes.sonarqube.plugins.icode.measures.ICodeSensor;
import fr.cnes.sonarqube.plugins.icode.rules.ICodeRulesDefinition;

/**
 * This class is the entry point for all extensions
 * 
 * @author Cyrille FRANCOIS
 */
public class ICodePlugin implements Plugin {

	public void define(Context context) {

		// Metrics definition and computed measures
		context.addExtensions(
				ICodeMetrics.class, ComputeModuleComplexityStatistics.class, ComputePluginErrors.class);		

		// Rules definition
		context.addExtension(ICodeRulesDefinition.class);	
		
		// Sonar scaner extension
		context.addExtension(
				ICodeSensor.class);		
	}
}
