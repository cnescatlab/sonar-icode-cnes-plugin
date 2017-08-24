package fr.cnes.sonarqube.plugins.icode.measures;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.cnes.sonarqube.plugins.icode.report.XmlReportReader;
import fr.cnes.sonarqube.plugins.icode.rules.ICodeRulesDefinition;

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
public class ICodeMetrics implements Metrics {
	
	private static final Logger LOGGER = Loggers.get(ICodeMetrics.class);

	public static final String DOMAIN = "ICode";
	
	/** Metric for cyclomatic complexity */
	public static final Metric<Integer> F77_CYCLOMATIC = new Metric.Builder(
			"icode-f77-cyclomatic-complexity",
			"F77 : ComplexitySimplified",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for cyclomatic complexity */
	public static final Metric<Integer> F90_CYCLOMATIC = new Metric.Builder(
			"icode-f90-cyclomatic-complexity",
			"F90 : ComplexitySimplified",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for number of lines of code */
	public static final Metric<Integer> F77_LOC = new Metric.Builder(
			"icode-f77-loc",
			"F77 : LineOfCode",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of lines of code */
	public static final Metric<Integer> F90_LOC = new Metric.Builder(
			"icode-f90-loc",
			"F90 : LineOfCode",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for nested statements */
	public static final Metric<Integer> F77_NESTING = new Metric.Builder(
			"icode-f77-nesting",
			"F77 : Nesting",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for nested statements */
	public static final Metric<Integer> F90_NESTING = new Metric.Builder(
			"icode-f90-nesting",
			"F90 : Nesting",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for ratio comment */
	public static final Metric<Float> F77_RATIO_COMMENT = new Metric.Builder(
			"icode-f77-ratio-comment",
			"F77 : RatioComment",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for ratio comment */
	public static final Metric<Float> F90_RATIO_COMMENT = new Metric.Builder(
			"icode-f90-ratio-comment",
			"F90 : RatioComment",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
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

	/** Metric project for maximum cyclomatic complexity */
	public static final Metric<Integer> F90_CYCLOMATIC_MAX = new Metric.Builder(
			"icode-f90-cyclomatic-complexity-max",
			"F90 : ComplexitySimplified (Maximum)",
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

	/** Metric project for minimum cyclomatic complexity */
	public static final Metric<Integer> F90_CYCLOMATIC_MIN = new Metric.Builder(
			"icode-f90-cyclomatic-complexity-min",
			"F90 : ComplexitySimplified (Minimum)",
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

	/** Metric project for mean cyclomatic complexity */
	public static final Metric<Double> F90_CYCLOMATIC_MEAN = new Metric.Builder(
			"icode-f90-cyclomatic-complexity-mean",
			"F90 : ComplexitySimplified (Mean)",
			Metric.ValueType.FLOAT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for maximum number of lines of code */
	public static final Metric<Integer> F77_LOC_MAX = new Metric.Builder(
			"icode-f77-loc-max",
			"F77 : LineOfCode (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (Maximum)")
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

	/** Metric project for maximum nested statements */
	public static final Metric<Integer> F77_NESTING_MAX = new Metric.Builder(
			"icode-f77-nesting-max",
			"F77 : Nesting (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements (Maximum)")
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
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Float> F77_RATIO_COMMENT_MAX = new Metric.Builder(
			"icode-f77-ratio-comment-max",
			"F77 : RatioComment (Maximum)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Maximum)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Float> F90_RATIO_COMMENT_MAX = new Metric.Builder(
			"icode-f90-ratio-comment-max",
			"F90 : RatioComment (Maximum)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Maximum)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for number of warnings */
	public static final Metric<Integer> NUMBER_OF_WARNINGS = new Metric.Builder(
			"icode-warnings",
			"Number of warning messages",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for number of errors */
	public static final Metric<Integer> NUMBER_OF_ERRORS = new Metric.Builder(
			"icode-errors",
			"Number of error messages",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for report files warning */
	public static final Metric<String> REPORT_FILES_WARNING = new Metric.Builder(
			"icode-report-files-warning",
			"Report files warning",
			Metric.ValueType.STRING)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for report files error */
	public static final Metric<String> REPORT_FILES_ERROR = new Metric.Builder(
			"icode-report-files-error",
			"Report files error",
			Metric.ValueType.STRING)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for report files error */
	public static final Metric<String> DBG = new Metric.Builder(
			"icode-dbg",
			"Debug report",
			Metric.ValueType.STRING)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res=new ArrayList<Metric>();
		
		res.addAll(Arrays.asList(
				F77_CYCLOMATIC, 
				F77_LOC,
				F77_NESTING,
				F77_RATIO_COMMENT));
		
		res.addAll(Arrays.asList(
				F90_CYCLOMATIC, 
				F90_LOC,
				F90_NESTING,
				F90_RATIO_COMMENT));
		
		res.addAll(Arrays.asList(
				F77_CYCLOMATIC_MAX, 
				F77_LOC_MAX,
				F77_NESTING_MAX,
				F77_RATIO_COMMENT_MAX));
		
		res.addAll(Arrays.asList(
				F90_CYCLOMATIC_MAX, 
				F90_LOC_MAX,
				F90_NESTING_MAX,
				F90_RATIO_COMMENT_MAX));
		
		//TODO : TBC 
		res.addAll(Arrays.asList(
				F77_CYCLOMATIC_MIN,
				F77_CYCLOMATIC_MEAN, 
				DBG
				));
		
		res.addAll(Arrays.asList(
				NUMBER_OF_WARNINGS, 
				NUMBER_OF_ERRORS,
				REPORT_FILES_WARNING,
				REPORT_FILES_ERROR));
		
		return res;
	}
}
