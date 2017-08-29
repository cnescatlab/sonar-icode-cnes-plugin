package fr.cnes.sonarqube.plugins.icode.measures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;


/**
 * 
 * Cyclomatic : La complexité cyclomatique (McCabe)
 * Nesting : Le niveau d’imbrication / nesting level
 * RatioComment : Le taux de commentaire
 * LineOfCode : le nombre de lignes de code hors commentaires et lignes vides
 *
 * @author Cyrille FRANCOIS
 * 
 */
public class ICodeMetricsSHELLNesting implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for nested statements */
	public static final Metric<Integer> SHELL_NESTING = new Metric.Builder(
			"icode-shell-nesting",
			"SHELL : Nesting",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Integer> SHELL_NESTING_MIN= new Metric.Builder(
			"icode-shell-nesting-min",
			"SHELL : Nesting (Minimum)",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements (Minimum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Integer> SHELL_NESTING_MAX = new Metric.Builder(
			"icode-shell-nesting-max",
			"SHELL : Nesting (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements (Maximum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Double> SHELL_NESTING_MEAN= new Metric.Builder(
			"icode-shell-nesting-mean",
			"SHELL : Nesting (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Number of nested statements (Mean)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		// SHELL Cyclomatic
		res.addAll(Arrays.asList(
				SHELL_NESTING, 
				SHELL_NESTING_MIN,
				SHELL_NESTING_MAX,
				SHELL_NESTING_MEAN));
				
		return res;
	}
}
