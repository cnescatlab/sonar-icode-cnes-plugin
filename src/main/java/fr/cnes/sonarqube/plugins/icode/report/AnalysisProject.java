/*
	 * This file is part of sonar-icode-cnes-plugin.
	 *
	 * sonar-icode-cnes-plugin is free software: you can redistribute it and/or modify
	 * it under the terms of the GNU General Public License as published by
	 * the Free Software Foundation, either version 3 of the License, or
	 * (at your option) any later version.
	 *
	 * sonar-icode-cnes-plugin is distributed in the hope that it will be useful,
	 * but WITHOUT ANY WARRANTY; without even the implied warranty of
	 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	 * GNU General Public License for more details.
	 *
	 * You should have received a copy of the GNU General Public License
	 * along with sonar-icode-cnes-plugin.  If not, see <http://www.gnu.org/licenses/>.

*/
package fr.cnes.sonarqube.plugins.icode.report;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.cnes.sonarqube.plugins.icode.rules.ICodeRulesDefinition;

/**
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class AnalysisProject implements ReportInterface{
	
	private static final String METHOD_TYPE_PLACE = "method";

	private static final String CLASS_TYPE_PLACE = "class";

	private static final Logger LOGGER = Loggers.get(AnalysisProject.class);
	
	static final String ANALYSIS_PROJECT_NAME = "analysisProjectName";
	static final String ANALYSIS_PROJECT_VERSION = "analysisProjectVersion";
	
	String analysisProjectName = null;
	String analysisProjectVersion = null;
	AnalysisFile analysisFile = null;
	List<AnalysisRule> listOfAnalysisRule = new ArrayList<>();
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
		if(analysisFile != null && analysisFile.languageField != null && !analysisFile.languageField.isEmpty()
				&& analysisFile.languageField.toLowerCase().endsWith(AnalysisRule.F77.toLowerCase())){
			res=true;
		}
		return res;
	}
	
	@Override
	public boolean isF90() {
		boolean res=false;
		if(analysisFile != null && analysisFile.languageField != null && !analysisFile.languageField.isEmpty()
				&& analysisFile.languageField.toLowerCase().endsWith(AnalysisRule.F90.toLowerCase())){
			res=true;
		}
		return res;
	}

	@Override
	public ReportModuleRuleInterface getModuleCyclomaticMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.COMPLEXITY_SIMPLIFIED) 
					&& analysisRule.resultField.resultTypePlace.equals(CLASS_TYPE_PLACE)){
				res = analysisRule;
				break;
			}
		}
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getCyclomaticMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.COMPLEXITY_SIMPLIFIED)
					&& analysisRule.resultField.resultTypePlace.equals(METHOD_TYPE_PLACE)){
				listOfRes.add(analysisRule);
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}
	
	
	@Override
	public ReportModuleRuleInterface getModuleLinesOfCodeMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.LINE_OF_CODE)
					&& analysisRule.resultField.resultTypePlace.equals(CLASS_TYPE_PLACE)){
				res = analysisRule;
				break;
			}
		}
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getLinesOfCodeMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.LINE_OF_CODE)
					&& analysisRule.resultField.resultTypePlace.equals(METHOD_TYPE_PLACE)){
				listOfRes.add(analysisRule);
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}
	
	
	
	@Override
	public ReportModuleRuleInterface getModuleNestingMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.NESTING)
					&& analysisRule.resultField.resultTypePlace.equals(CLASS_TYPE_PLACE)){
				res = analysisRule;
				break;
			}
		}
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getNestingMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.NESTING)
					&& analysisRule.resultField.resultTypePlace.equals(METHOD_TYPE_PLACE)){
				listOfRes.add(analysisRule);
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}
	
	@Override
	public ReportModuleRuleInterface getModuleRatioCommentMeasure() {
		ReportModuleRuleInterface res=null;
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.RATIO_COMMENT)
					&& analysisRule.resultField.resultTypePlace.equals(CLASS_TYPE_PLACE)){
				res = analysisRule;
				break;
			}
		}
		return res;
	}
	
	@Override
	public ReportFunctionRuleInterface[] getRatioCommentMeasureByFunction(){
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if(analysisRule.analysisRuleId.endsWith(AnalysisRule.RATIO_COMMENT)
					&& analysisRule.resultField.resultTypePlace.equals(METHOD_TYPE_PLACE)
					&& !Double.isNaN(Double.parseDouble(analysisRule.resultField.resultValue))){
				listOfRes.add(analysisRule);
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}

	@Override
	public ErrorInterface[] getErrors() {
		ReportFunctionRuleInterface[] res=null;
		List<ReportFunctionRuleInterface> listOfRes = new ArrayList<>();
		for (AnalysisRule analysisRule : listOfAnalysisRule) {
			if((!analysisRule.analysisRuleId.endsWith(AnalysisRule.RATIO_COMMENT)) &&
					(!analysisRule.analysisRuleId.endsWith(AnalysisRule.COMPLEXITY_SIMPLIFIED)) && 
					(!analysisRule.analysisRuleId.endsWith(AnalysisRule.LINE_OF_CODE)) && 
					(!analysisRule.analysisRuleId.endsWith(AnalysisRule.NESTING))
					&& analysisRule.resultField.resultTypePlace.equals(METHOD_TYPE_PLACE)){

				// Check if the rule does not exist into ICodeRulesDefinition
				if(isUnexpectedRule(analysisRule)){
					
					// Unexcepted rule analyse is save as a COM.DEFAULT rule issue...
					analysisRule.analysisRuleId = "COM.DEFAULT";	
				}
				listOfRes.add(analysisRule);
				LOGGER.debug("AnalysisRule: "+analysisRule.toString());
			}
		}
		res = listOfRes.toArray(new ReportFunctionRuleInterface[listOfRes.size()]);
		return res;
	}
	
	private boolean isUnexpectedRule(AnalysisRule analysisRule){
		boolean res=true;
		
		res = !ICodeRulesDefinition.existRule(analysisRule.analysisRuleId);
		
		return res;
	}	

}