package fr.cnes.sonarqube.plugins.icode.report;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class AnalysisProject implements ReportInterface{
	
	static final String ANALYSIS_PROJECT_NAME = "analysisProjectName";
	static final String ANALYSIS_PROJECT_VERSION = "analysisProjectVersion";
	
	String analysisProjectName = null;
	String analysisProjectVersion = null;
	AnalysisFile analysisFile = null;
	List<AnalysisRule> listOfAnalysisRule = null;
	AnalysisInformations analysisInformations = null;
	
	@Override
	public String toString() {
		return "AnalysisProject [analysisProjectName=" + analysisProjectName + ", analysisProjectVersion="
				+ analysisProjectVersion + ", analysisFile=" + analysisFile + ", listOfAnalysisRule="
				+ listOfAnalysisRule + ", analysisInformations=" + analysisInformations + "]";
	}

	@Override
	public boolean isF77() {
		boolean res=false;
		if(listOfAnalysisRule != null && listOfAnalysisRule.size()>0){
			if(listOfAnalysisRule.get(0).analysisRuleId.startsWith(AnalysisRule.F77)){
				res=true;
			}
		}
		return res;
	}
	
	@Override
	public boolean isF90() {
		// TODO Auto-generated method stub
		boolean res=false;
		if(listOfAnalysisRule != null && listOfAnalysisRule.size()>0){
			if(listOfAnalysisRule.get(0).analysisRuleId.startsWith(AnalysisRule.F90)){
				res=true;
			}
		}
		return res;
		//return false;
	}

	@Override
	public ReportModuleRuleInterface getModuleCyclomaticMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.COMPLEXITY_SIMPLIFIED)){
				if(analysisRule.result.resultTypePlace.equals("class")){
					res = analysisRule;
					break;
				}
			}
		}
		// TODO Auto-generated method stub
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getCyclomaticMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<ReportFunctionRuleInterface>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.COMPLEXITY_SIMPLIFIED)){
				if(analysisRule.result.resultTypePlace.equals("method")){
					listOfRes.add(analysisRule);
				}
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}
	
	
	@Override
	public ReportModuleRuleInterface getModuleLinesOfCodeMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.LINE_OF_CODE)){
				if(analysisRule.result.resultTypePlace.equals("class")){
					res = analysisRule;
					break;
				}
			}
		}
		// TODO Auto-generated method stub
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getLinesOfCodeMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<ReportFunctionRuleInterface>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.LINE_OF_CODE)){
				if(analysisRule.result.resultTypePlace.equals("method")){
					listOfRes.add(analysisRule);
				}
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}
	
	
	
	@Override
	public ReportModuleRuleInterface getModuleNestingMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.NESTING)){
				if(analysisRule.result.resultTypePlace.equals("class")){
					res = analysisRule;
					break;
				}
			}
		}
		// TODO Auto-generated method stub
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getNestingMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<ReportFunctionRuleInterface>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.NESTING)){
				if(analysisRule.result.resultTypePlace.equals("method")){
					listOfRes.add(analysisRule);
				}
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}
	
	@Override
	public ReportModuleRuleInterface getModuleRatioCommentMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.RATIO_COMMENT)){
				if(analysisRule.result.resultTypePlace.equals("class")){
					res = analysisRule;
					break;
				}
			}
		}
		// TODO Auto-generated method stub
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getRatioCommentMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<ReportFunctionRuleInterface>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.RATIO_COMMENT)){
				if(analysisRule.result.resultTypePlace.equals("method")){
					listOfRes.add(analysisRule);
				}
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}

	@Override
	public ErrorInterface[] getErrors() {
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<ReportFunctionRuleInterface>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if((!analysisRule.analysisRuleId.endsWith(AnalysisRule.RATIO_COMMENT)) &&
					(!analysisRule.analysisRuleId.endsWith(AnalysisRule.COMPLEXITY_SIMPLIFIED)) && 
					(!analysisRule.analysisRuleId.endsWith(AnalysisRule.LINE_OF_CODE)) && 
					(!analysisRule.analysisRuleId.endsWith(AnalysisRule.NESTING))){
				if(analysisRule.result.resultTypePlace.equals("method")){

					// Check if the rule does not exist into ICodeRulesDefinition
					if(isUnexpectedRule(analysisRule)){
						
						// Unexcepted rule analyse is save as a COM.DEFAULT rule issue...
						if(this.isF77()){
							analysisRule.analysisRuleId = AnalysisRule.F77+"COM.DEFAULT";
						}
						if(this.isF90()){
							analysisRule.analysisRuleId = AnalysisRule.F77+"COM.DEFAULT";
						}
						else{
							analysisRule.analysisRuleId = AnalysisRule.SHELL+"COM.DEFAULT";
						}	
					}
					listOfRes.add(analysisRule);
				}
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return (ErrorInterface[]) res;
	}
	
	private boolean isUnexpectedRule(AnalysisRule analysisRule){
		boolean res=true;
		if(analysisRule.analysisRuleId.equals("COM.DATA.FloatCompare")){
			res=false;
		}
		return res;
	}	

	/* ReportInterface */
}