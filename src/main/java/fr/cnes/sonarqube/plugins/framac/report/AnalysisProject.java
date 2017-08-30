package fr.cnes.sonarqube.plugins.framac.report;

/**
 * Analyze Frama-C report file
 * 
 * @author Cyrille
 *
 */
public class AnalysisProject  implements ReportInterface{
	
	/** Global metrics measures computed by Frama-C */
	MetricsModuleMeasure globalMetrics;

	public AnalysisProject() {
		super();
		this.globalMetrics = new MetricsModuleMeasure();
	}

	MetricsModuleMeasure getGlobalMetrics() {
		return globalMetrics;
	}

	@Override
	public ReportModuleRuleInterface getModuleCyclomaticMeasure() {
		// TODO Auto-generated method stub
		return this.globalMetrics;
	}

	@Override
	public ReportFunctionRuleInterface[] getCyclomaticMeasureByFunction() {
		// TODO Auto-generated method stub
		return null;
	}
}

class MetricsModuleMeasure implements ReportModuleRuleInterface{
	
	String sloc;
	String decisionPoint;
	String globalVariables;
	String ifStatements;
	String loopStatements;
	String gotoStatements;
	String assignmentStatements;
	String exitPoint;
	String function;
	String functionCall;
	String pointerDereferencing;
	String cyclomaticComplexity;

	@Override
	public String getLoc() {
		// TODO Auto-generated method stub
		return this.sloc;
	}

	@Override
	public String getComplexity() {
		// TODO Auto-generated method stub
		return this.cyclomaticComplexity;
	}

	void setSloc(String sloc) {
		this.sloc = sloc;
	}

	void setDecisionPoint(String decisionPoint) {
		this.decisionPoint = decisionPoint;
	}

	void setGlobalVariables(String globalVariables) {
		this.globalVariables = globalVariables;
	}

	void setIfStatements(String ifStatements) {
		this.ifStatements = ifStatements;
	}

	void setLoopStatements(String loopStatements) {
		this.loopStatements = loopStatements;
	}

	void setGotoStatements(String gotoStatements) {
		this.gotoStatements = gotoStatements;
	}

	void setAssignmentStatements(String assignmentStatements) {
		this.assignmentStatements = assignmentStatements;
	}

	void setExitPoint(String exitPoint) {
		this.exitPoint = exitPoint;
	}

	void setFunction(String function) {
		this.function = function;
	}

	void setFunctionCall(String functionCall) {
		this.functionCall = functionCall;
	}

	void setPointerDereferencing(String pointerDereferencing) {
		this.pointerDereferencing = pointerDereferencing;
	}

	void setCyclomaticComplexity(String cyclomaticComplexity) {
		this.cyclomaticComplexity = cyclomaticComplexity;
	}
}