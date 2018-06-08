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
package fr.cnes.sonar.plugins.icode.report;

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
	Result resultField;
	
	@Override
	public String toString() {
		return "AnalysisRule [analysisRuleId=" + analysisRuleId + ", result=" + resultField + "]";
	}
	
	/* ReportRuleInterface */
	
	@Override
	public String getValue() {
		return resultField.resultValue;
	}

	@Override
	public String getFunction() {
		return resultField.resultNamePlace;
	}

	@Override
	public String getLine() {
		return resultField.resultLine;
	}

	@Override
	public String getLoc() {
		return "0";
	}

	@Override
	public String getComplexity() {
		return "0";
	}

	@Override
	public String getLineDescriptor() {
		return resultField.resultLine;
	}

	@Override
	public String getRuleKey() {
		return analysisRuleId;
	}

	@Override
	public String getDescription() {
		String res="";
		if(resultField != null && resultField.resultValue != null){
			res = resultField.resultValue;
		}
		return res;
	}
	
	
}