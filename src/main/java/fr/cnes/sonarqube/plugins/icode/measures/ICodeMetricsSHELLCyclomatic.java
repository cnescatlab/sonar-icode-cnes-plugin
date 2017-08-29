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
public class ICodeMetricsSHELLCyclomatic implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for cyclomatic complexity */
	public static final Metric<Integer> SHELL_CYCLOMATIC = new Metric.Builder(
			"icode-shell-cyclomatic-complexity",
			"SHELL : ComplexitySimplified",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for minimum cyclomatic complexity */
	public static final Metric<Integer> SHELL_CYCLOMATIC_MIN = new Metric.Builder(
			"icode-shell-cyclomatic-complexity-min",
			"SHELL : ComplexitySimplified (Minimum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for maximum cyclomatic complexity */
	public static final Metric<Integer> SHELL_CYCLOMATIC_MAX = new Metric.Builder(
			"icode-shell-cyclomatic-complexity-max",
			"SHELL : ComplexitySimplified (Maximum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for mean cyclomatic complexity */
	public static final Metric<Double> SHELL_CYCLOMATIC_MEAN = new Metric.Builder(
			"icode-shell-cyclomatic-complexity-mean",
			"SHELL : ComplexitySimplified (Mean)",
			Metric.ValueType.FLOAT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		// SHELL Cyclomatic
		res.addAll(Arrays.asList(
				SHELL_CYCLOMATIC, 
				SHELL_CYCLOMATIC_MIN,
				SHELL_CYCLOMATIC_MAX,
				SHELL_CYCLOMATIC_MEAN));
				
		return res;
	}
}
