package fr.cnes.sonarqube.plugins.framac;

import org.sonar.api.Plugin;

import fr.cnes.sonarqube.plugins.framac.measures.ComputePluginErrors;
import fr.cnes.sonarqube.plugins.framac.measures.ComputeProjectCyclomaticStatistics;
import fr.cnes.sonarqube.plugins.framac.measures.ComputeProjectLocStatistics;
import fr.cnes.sonarqube.plugins.framac.measures.CyclomaticMetrics;
import fr.cnes.sonarqube.plugins.framac.measures.FramaCMetrics;
import fr.cnes.sonarqube.plugins.framac.measures.FramaCSensor;
import fr.cnes.sonarqube.plugins.framac.rules.FramaCRulesDefinition;

/**
 * This class is the entry point for all extensions
 * 
 * @author Cyrille FRANCOIS
 */
public class FramaCPlugin implements Plugin {

	public void define(Context context) {

		// Metrics definition and computed measures
		context.addExtensions(
				FramaCMetrics.class, ComputePluginErrors.class,
				CyclomaticMetrics.class, ComputeProjectCyclomaticStatistics.class,ComputeProjectLocStatistics.class);

		// Rules definition
		context.addExtension(FramaCRulesDefinition.class);
		
		// Sonar scanner extension (Sensor)
		context.addExtension(
				FramaCSensor.class);		
	}
}
