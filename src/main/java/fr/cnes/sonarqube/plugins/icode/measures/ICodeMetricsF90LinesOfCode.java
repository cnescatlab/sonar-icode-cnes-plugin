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
public class ICodeMetricsF90LinesOfCode implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for number of lines of code */
	public static final Metric<Integer> F90_LOC = new Metric.Builder(
			"icode-f90-loc",
			"F90 : LineOfCode",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for minimum number of lines of code */
	public static final Metric<Integer> F90_LOC_MIN = new Metric.Builder(
			"icode-f90-loc-min",
			"F90 : LineOfCode (Minimum)",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (Minimum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum number of lines of code */
	public static final Metric<Integer> F90_LOC_MAX = new Metric.Builder(
			"icode-f90-loc-max",
			"F90 : LineOfCode (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (Maximum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for mean number of lines of code */
	public static final Metric<Double> F90_LOC_MEAN = new Metric.Builder(
			"icode-f90-loc-mean",
			"F90 : LineOfCode (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Number of lines of code (Mean)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		// F90 Cyclomatic
		res.addAll(Arrays.asList(
				F90_LOC, 
				F90_LOC_MIN,
				F90_LOC_MAX,
				F90_LOC_MEAN));
				
		return res;
	}
}
