package fr.cnes.sonarqube.plugins.icode.report;

import java.util.ArrayList;
import java.util.List;

import fr.cnes.sonarqube.plugins.icode.measures.ReportFunctionRuleInterface;
import fr.cnes.sonarqube.plugins.icode.measures.ReportInterface;
import fr.cnes.sonarqube.plugins.icode.measures.ReportModuleRuleInterface;

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
	public boolean isF90() {
		// TODO Auto-generated method stub
		return false;
	}

	/* ReportInterface */
}