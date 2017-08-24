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
	    return defContext.newDefinitionBuilder()
	    		.setInputMetrics(new String[] {NUMBER_OF_ERRORS.key(), REPORT_FILES_ERROR.key()})
	    		.setOutputMetrics(new String[] {NUMBER_OF_ERRORS.key(), REPORT_FILES_ERROR.key()})
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
	        sumOfReportFiles++;
	      }
	      
		  context.addMeasure(REPORT_FILES_ERROR.key(), "Number of report files : "+sumOfReportFiles+" parse "+REPORT_FILES_ERROR.getName()+" for each file...");	 
	    }
	}
	

}
