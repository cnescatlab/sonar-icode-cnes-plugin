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

public class AnalysisRule implements ReportFunctionRuleInterface{
	static final String RESULT = "result";
	static final String ANALYSIS_RULE_ID = "analysisRuleId";
	
	public static final String F77 = "F77";
	public static final String F90 = "F90";
	public static final String SHELL = "SHELL";
	
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

	@Override
	public String getLoc() {
		// TODO Auto-generated method stub
		return "0";
	}

	@Override
	public String getComplexity() {
		// TODO Auto-generated method stub
		return "0";
	}

	@Override
	public String getLineDescriptor() {
		// TODO Auto-generated method stub
		return result.resultLine;
	}

	@Override
	public String getRuleKey() {
		// TODO Auto-generated method stub
		return analysisRuleId;
	}

	@Override
	public String getDescription() {
		String res="";
		if(result != null && result.resultValue != null){
			res = result.resultValue;
		}
		return res;
	}
	
	
}