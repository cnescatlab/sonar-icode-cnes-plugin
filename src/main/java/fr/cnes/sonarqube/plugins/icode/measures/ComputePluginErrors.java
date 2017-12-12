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
package fr.cnes.sonarqube.plugins.icode.measures;

import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetrics.NUMBER_OF_ERRORS;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetrics.REPORT_FILES_ERROR;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * Compute a project global ICode report error.
 * 
 * ICode shall provide a report for each project code file
 * 
 * @see ICodeSensor
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class ComputePluginErrors implements MeasureComputer {
	
	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
	    String[] metricTab = new String[] {NUMBER_OF_ERRORS.key(), REPORT_FILES_ERROR.key()};
		return defContext.newDefinitionBuilder()
	    		.setInputMetrics(metricTab)
	    		.setOutputMetrics(metricTab)
	    		.build();
	}

	@Override
	public void compute(MeasureComputerContext context) {
	    // measure is already defined on files by {@link SetSizeOnFilesSensor}
	    // in scanner stack
	    if (context.getComponent().getType() != Component.Type.FILE) {
	      int sum = 0;
	      for (Measure child : context.getChildrenMeasures(NUMBER_OF_ERRORS.key())) {
	    	  sum += child.getIntValue();
	      }
	      context.addMeasure(NUMBER_OF_ERRORS.key(), sum);
	      
	      int sumOfReportFiles = 0;
	      for (Measure child : context.getChildrenMeasures(REPORT_FILES_ERROR.key())) {
	    	  child.hashCode();
	    	  sumOfReportFiles++;
		  }
	      
		  context.addMeasure(REPORT_FILES_ERROR.key(), "Number of report files : "+sumOfReportFiles+" parse "+REPORT_FILES_ERROR.getName()+" for each file...");	 
	    }
	}
	

}
