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
public class ICodeMetricsF90RatioComment implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for ratio comment */
	public static final Metric<Double> F90_RATIO_COMMENT = new Metric.Builder(
			"icode-f90-ratio-comment",
			"F90 : RatioComment",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Double> F90_RATIO_COMMENT_MIN= new Metric.Builder(
			"icode-f90-ratio-comment-min",
			"F90 : RatioComment (Minimum)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Minimum)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Double> F90_RATIO_COMMENT_MAX = new Metric.Builder(
			"icode-f90-ratio-comment-max",
			"F90 : RatioComment (Maximum)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Maximum)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Double> F90_RATIO_COMMENT_MEAN= new Metric.Builder(
			"icode-f90-ratio-comment-mean",
			"F90 : RatioComment (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Mean)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		res.addAll(Arrays.asList(
				F90_RATIO_COMMENT, 
				F90_RATIO_COMMENT_MIN,
				F90_RATIO_COMMENT_MAX,
				F90_RATIO_COMMENT_MEAN));
				
		return res;
	}
}
