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
public class ICodeMetricsF77Cyclomatic implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for cyclomatic complexity */
	public static final Metric<Integer> F77_CYCLOMATIC = new Metric.Builder(
			"icode-f77-cyclomatic-complexity",
			"F77 : ComplexitySimplified",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for minimum cyclomatic complexity */
	public static final Metric<Integer> F77_CYCLOMATIC_MIN = new Metric.Builder(
			"icode-f77-cyclomatic-complexity-min",
			"F77 : ComplexitySimplified (Minimum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for maximum cyclomatic complexity */
	public static final Metric<Integer> F77_CYCLOMATIC_MAX = new Metric.Builder(
			"icode-f77-cyclomatic-complexity-max",
			"F77 : ComplexitySimplified (Maximum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for mean cyclomatic complexity */
	public static final Metric<Double> F77_CYCLOMATIC_MEAN = new Metric.Builder(
			"icode-f77-cyclomatic-complexity-mean",
			"F77 : ComplexitySimplified (Mean)",
			Metric.ValueType.FLOAT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		res.addAll(Arrays.asList(
				F77_CYCLOMATIC, 
				F77_CYCLOMATIC_MIN,
				F77_CYCLOMATIC_MAX,
				F77_CYCLOMATIC_MEAN));
				
		return res;
	}
}
