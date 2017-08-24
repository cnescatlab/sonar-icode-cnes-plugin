package fr.cnes.sonarqube.plugins.icode.report;

import fr.cnes.sonarqube.plugins.icode.measures.ReportFunctionRuleInterface;

public class AnalysisRule implements ReportFunctionRuleInterface{
	static final String RESULT = "result";
	static final String ANALYSIS_RULE_ID = "analysisRuleId";
	
	public static final String F77 = "F77";
	public static final String F90 = "F90";
	
	public static final String COMPLEXITY_SIMPLIFIED = "MET.ComplexitySimplified";
	public static final String NESTING = "MET.Nesting";
	public static final String LINE_OF_CODE = "MET.LineOfCode";
	public static final String RATIO_COMMENT = "MET.RatioComment";

	String analysisRuleId;
	Result result;
	
	@Override
	public String toString() {
		return "AnalysisRule [analysisRuleId=" + analysisRuleId + ", result=" + result + "]";
	}
	
	/* ReportRuleInterface */
	
	@Override
	public String getValue() {
		return result.resultValue;
	}

	@Override
	public String getFunction() {
		// TODO Auto-generated method stub
		return result.resultNamePlace;
	}

	@Override
	public String getLine() {
		// TODO Auto-generated method stub
		return result.resultLine;
	}
	
	
}