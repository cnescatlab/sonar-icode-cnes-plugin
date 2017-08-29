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
public class ICodeMetricsF90Nesting implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for nested statements */
	public static final Metric<Integer> F90_NESTING = new Metric.Builder(
			"icode-f90-nesting",
			"F90 : Nesting",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Integer> F90_NESTING_MIN= new Metric.Builder(
			"icode-f90-nesting-min",
			"F90 : Nesting (Minimum)",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements (Minimum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Integer> F90_NESTING_MAX = new Metric.Builder(
			"icode-f90-nesting-max",
			"F90 : Nesting (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements (Maximum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Double> F90_NESTING_MEAN= new Metric.Builder(
			"icode-f90-nesting-mean",
			"F90 : Nesting (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Number of nested statements (Mean)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		// F90 Cyclomatic
		res.addAll(Arrays.asList(
				F90_NESTING, 
				F90_NESTING_MIN,
				F90_NESTING_MAX,
				F90_NESTING_MEAN));
				
		return res;
	}
}
