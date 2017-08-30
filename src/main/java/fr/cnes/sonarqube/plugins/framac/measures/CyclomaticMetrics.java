package fr.cnes.sonarqube.plugins.framac.measures;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * Define Frama-C metrics into Sonar data base and pattern matching into Frama-C report file.
 * 
 * Frama-C report file contains a global metrics report as below:
 * 
 * <pre>
 * [metrics] Global metrics
 *           ============== 
 *           Sloc = 7
 *           Decision point = 1
 *           Global variables = 0
 *           If = 1
 *           Loop = 0
 *           Goto = 0
 *           Assignment = 0
 *           Exit point = 2
 *           Function = 1
 *           Function call = 11
 *           Pointer dereferencing = 0
 *           Cyclomatic complexity = 1
 * </pre>
 * 
 * Each metrics is associated with a expected report pattern in order to import Frama-C metrics measures 
 *
 * @author Cyrille
 * 
 */
public class CyclomaticMetrics implements Metrics {

	public static final String DOMAIN = FramaCMetrics.DOMAIN;
	
	private static final Map<String,Pattern> mapMetricsPattern = new HashMap<String, Pattern>();
	
	public static Map<String, Pattern> getMapMetricsPattern() {
		if(mapMetricsPattern == null){
			initMapMetricsPattern();
		}
		return mapMetricsPattern;
	}

	public final static Pattern METRICS_PATTERN = Pattern.compile("Global metrics");

	/** Metric for number of lines of code (assuming one C statement equals one line of code): Sloc */
	public static final Metric<Integer> SLOC = new Metric.Builder(
			"framac-sloc",
			"Number of lines of code",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (assuming one C statement equals one line of code)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for average number of lines of code by module */
	public static final Metric<Double> SLOC_MEAN = new Metric.Builder(
			"framac-sloc-mean",
			"Number of lines of code (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Number of lines of code (Mean)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for the maximum number of lines of code by module */
	public static final Metric<Integer> SLOC_MAX = new Metric.Builder(
			"framac-sloc-max",
			"Number of lines of code (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (Maximum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for the minimum number of lines of code */
	public static final Metric<Integer> SLOC_MIN = new Metric.Builder(
			"framac-sloc-min",
			"Number of lines of code (Minimum)",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (Minimum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of decision points (conditional statements (if) and expressions (? :), switc cases, lazy logical operators, loops)*/
	public static final Metric<Integer> DECISION_POINTS = new Metric.Builder(
			"framac-decision-points",
			"Decision points",
			Metric.ValueType.INT)
			.setDescription("Decision points (conditional statements (if) and expressions (? :), switc cases, lazy logical operators, loops)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of global variables */
	public static final Metric<Integer> NUMBER_OF_GLOBAL_VARIABLES = new Metric.Builder(
			"framac-global-variables",
			"Number of global variables",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of if statements */
	public static final Metric<Integer> NUMBER_OF_IF_STATEMENTS = new Metric.Builder(
			"framac-if-statements",
			"Number of if statements",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of loop statements */
	public static final Metric<Integer> NUMBER_OF_LOOP_STATEMENTS = new Metric.Builder(
			"framac-loop-statements",
			"Number of loop statements",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of goto statements */
	public static final Metric<Integer> NUMBER_OF_GOTO_STATEMENTS = new Metric.Builder(
			"framac-goto-statements",
			"Number of goto statements",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of assignment statements */
	public static final Metric<Integer> NUMBER_OF_ASSIGNMENT_STATEMENTS = new Metric.Builder(
			"framac-assignment-statements",
			"Number of assignment statements",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of exit point statements */
	public static final Metric<Integer> NUMBER_OF_EXIT_POINT_STATEMENTS = new Metric.Builder(
			"framac-exit-point-statements",
			"Number of exit point",
			Metric.ValueType.INT)
			.setDescription("Number of exit point (return statements)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of functions declared*/
	public static final Metric<Integer> NUMBER_OF_FUNCTIONS_DECLARED = new Metric.Builder(
			"framac-functions-declared",
			"Number of functions declared",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	
	/** Metric for number of function calls */
	public static final Metric<Integer> NUMBER_OF_FUNCTION_CALLS = new Metric.Builder(
			"framac-function-calls",
			"Number of function calls",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for number of pointer dereferencings */
	public static final Metric<Integer> NUMBER_OF_POINTER_DEREFERENCINGS = new Metric.Builder(
			"framac-pointer-dereferencings",
			"Number of pointer dereferencings",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric for cyclomatic complexity */
	public static final Metric<Integer> CYCLOMATIC = new Metric.Builder(
			"framac-cyclomatic-complexity",
			"Cyclomatic complexity",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for the mean value of cyclomatic complexity */
	public static final Metric<Double> CYCLOMATIC_MEAN = new Metric.Builder(
			"framac-cyclomatic-complexity-mean",
			"Cyclomatic complexity (Mean)",
			Metric.ValueType.FLOAT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for the maximum value of cyclomatic complexity */
	public static final Metric<Integer> CYCLOMATIC_MAX = new Metric.Builder(
			"framac-cyclomatic-complexity-max",
			"Cyclomatic complexity (Maximum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for the minimum value of cyclomatic complexity */
	public static final Metric<Integer> CYCLOMATIC_MIN = new Metric.Builder(
			"framac-cyclomatic-complexity-min",
			"Cyclomatic complexity (Minimum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	private static final String PATTERN_ASSIGNEMENT = " = ";

	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		
		initMapMetricsPattern();
		
		ArrayList<Metric> res=new ArrayList<Metric>();
		res.addAll(Arrays.asList(
				DECISION_POINTS, 
				NUMBER_OF_GLOBAL_VARIABLES,
				NUMBER_OF_IF_STATEMENTS,
				NUMBER_OF_LOOP_STATEMENTS,
				NUMBER_OF_GOTO_STATEMENTS,
				NUMBER_OF_ASSIGNMENT_STATEMENTS,
				NUMBER_OF_EXIT_POINT_STATEMENTS,
				NUMBER_OF_FUNCTIONS_DECLARED,
				NUMBER_OF_FUNCTION_CALLS,
				NUMBER_OF_POINTER_DEREFERENCINGS
				));
		res.addAll(Arrays.asList(
				SLOC,
				SLOC_MEAN,
				SLOC_MAX,
				SLOC_MIN
				));
		res.addAll(Arrays.asList(
				CYCLOMATIC,
				CYCLOMATIC_MEAN,
				CYCLOMATIC_MAX,
				CYCLOMATIC_MIN
				));
		return res;
	}


	private static void initMapMetricsPattern() {
		Pattern[] patterns = new Pattern[]{
				Pattern.compile("Sloc"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Decision point"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Global variables"+PATTERN_ASSIGNEMENT),
				Pattern.compile("If"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Loop"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Goto"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Assignment"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Exit point"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Function"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Function call"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Pointer dereferencing"+PATTERN_ASSIGNEMENT),
				Pattern.compile("Cyclomatic complexity"+PATTERN_ASSIGNEMENT)
		};
		mapMetricsPattern.put(SLOC.getKey(), patterns[0]);
		mapMetricsPattern.put(DECISION_POINTS.getKey(), patterns[1]);
		mapMetricsPattern.put(NUMBER_OF_GLOBAL_VARIABLES.getKey(), patterns[2]);
		mapMetricsPattern.put(NUMBER_OF_IF_STATEMENTS.getKey(), patterns[3]);
		mapMetricsPattern.put(NUMBER_OF_LOOP_STATEMENTS.getKey(), patterns[4]);
		mapMetricsPattern.put(NUMBER_OF_GOTO_STATEMENTS.getKey(), patterns[5]);
		mapMetricsPattern.put(NUMBER_OF_ASSIGNMENT_STATEMENTS.getKey(), patterns[6]);
		mapMetricsPattern.put(NUMBER_OF_EXIT_POINT_STATEMENTS.getKey(), patterns[7]);
		mapMetricsPattern.put(NUMBER_OF_FUNCTIONS_DECLARED.getKey(), patterns[8]);
		mapMetricsPattern.put(NUMBER_OF_FUNCTION_CALLS.getKey(), patterns[9]);
		mapMetricsPattern.put(NUMBER_OF_POINTER_DEREFERENCINGS.getKey(), patterns[10]);
		mapMetricsPattern.put(CYCLOMATIC.getKey(), patterns[11]);
	}

	
	static void analyseReportOut(SensorContext context, InputFile file, String fileRelativePathNameReportOut) {
		StringBuffer warningMsgs = new StringBuffer();
		int nbWarningMsgs = 0;
		StringBuffer errorMsgs = new StringBuffer();
		int nbErrorMsgs = 0;
	    if(existFile(file, fileRelativePathNameReportOut)){
  	    	  
			Path path=file.path().resolve(fileRelativePathNameReportOut);
			try {
				FileChannel reportFile = FileChannel.open(path);
			    long reportFileSize = reportFile.size();
			    if(reportFileSize>0){
			    	errorMsgs.append("Empty report file : "+fileRelativePathNameReportOut);
			    	nbErrorMsgs++;
			    }
			} catch (IOException e) {
				errorMsgs.append("Unexpected error report file for : "+fileRelativePathNameReportOut);
		    	nbErrorMsgs++;
			}
	    }
	    else{
	    	errorMsgs.append("No report file for : "+fileRelativePathNameReportOut);
	    	nbErrorMsgs++;
	    }
		// Add a FramaC report warning
		if(nbWarningMsgs>0){
		      context.<String>newMeasure()
		        .forMetric(FramaCMetrics.REPORT_FILES_WARNING)
		        .on(file)
		        .withValue(warningMsgs.toString())
		        .save();	    	  			
		      context.<Integer>newMeasure()
		        .forMetric(FramaCMetrics.NUMBER_OF_WARNINGS)
		        .on(file)
		        .withValue(nbWarningMsgs)
		        .save();	    	  			
		}
		// Add a FramaC report error
		if(nbErrorMsgs>0){
		      context.<String>newMeasure()
		        .forMetric(FramaCMetrics.REPORT_FILES_ERROR)
		        .on(file)
		        .withValue(errorMsgs.toString())
		        .save();	    	  			
		      context.<Integer>newMeasure()
		        .forMetric(FramaCMetrics.NUMBER_OF_ERRORS)
		        .on(file)
		        .withValue(nbErrorMsgs)
		        .save();	    	  			
		}
		// TODO: add metrics results
	}
	
	static private boolean existFile(InputFile file, String fileRelativePathNameReport) {
		boolean res=false;
		Path path = file.path().resolve(fileRelativePathNameReport);
		res = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
		return res;
	}
//	Number of decision points (conditional statements (if) and expressions (? :), switc cases, lazy logical operators, loops).

}
