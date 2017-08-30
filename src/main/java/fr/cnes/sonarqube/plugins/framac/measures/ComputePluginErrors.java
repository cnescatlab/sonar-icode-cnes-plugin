package fr.cnes.sonarqube.plugins.framac.measures;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * Compute a project global Frama-C report error.
 * 
 * Frama-C shall provide a report for each project code file
 * 
 * @see FramaCSensor
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class ComputePluginErrors implements MeasureComputer {
	
	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
	    return defContext.newDefinitionBuilder()
	    		.setInputMetrics(new String[] {FramaCMetrics.NUMBER_OF_ERRORS.key(), FramaCMetrics.REPORT_FILES_ERROR.key()})
	    		.setOutputMetrics(new String[] {FramaCMetrics.NUMBER_OF_ERRORS.key(), FramaCMetrics.REPORT_FILES_ERROR.key()})
	    		.build();
	}

	@Override
	public void compute(MeasureComputerContext context) {
	    // measure is already defined on files by {@link SetSizeOnFilesSensor}
	    // in scanner stack
	    if (context.getComponent().getType() != Component.Type.FILE) {
	      int sum = 0;
	      for (Measure child : context.getChildrenMeasures(FramaCMetrics.NUMBER_OF_ERRORS.key())) {
	        sum += child.getIntValue();
	      }
	      context.addMeasure(FramaCMetrics.NUMBER_OF_ERRORS.key(), sum);
	      
	      int sumOfReportFiles = 0;
	      for (Measure child : context.getChildrenMeasures(FramaCMetrics.REPORT_FILES_ERROR.key())) {
	        sumOfReportFiles++;
	      }
	      
		  context.addMeasure(FramaCMetrics.REPORT_FILES_ERROR.key(), "Number of report files : "+sumOfReportFiles+" parse "+FramaCMetrics.REPORT_FILES_ERROR.getName()+" for each file...");	 
	    }
	}
	

}
