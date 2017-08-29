package fr.cnes.sonarqube.plugins.icode.measures;

//import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetrics.DBG;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLLinesOfCode.SHELL_LOC;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLLinesOfCode.SHELL_LOC_MEAN;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLLinesOfCode.SHELL_LOC_MIN;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsSHELLLinesOfCode.SHELL_LOC_MAX;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * Compute lines of code into the project.
 * 
 * Each file lines of code is provided by the analyse report file
 * 
 * @see ICodeSensor
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class ComputeModuleSHELLLinesOfCodeStatistics implements MeasureComputer {

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
		
	    return defContext.newDefinitionBuilder()
	    		.setInputMetrics(new String[] {SHELL_LOC.key(),SHELL_LOC_MEAN.key(),SHELL_LOC_MIN.key(),SHELL_LOC_MAX.key()})
	    		.setOutputMetrics(new String[] {SHELL_LOC.key(),SHELL_LOC_MEAN.key(),SHELL_LOC_MIN.key(),SHELL_LOC_MAX.key()})//,DBG.key()})
	    		.build();
	}

	@Override
	public void compute(MeasureComputerContext context) {
		Iterable<Measure> childrenMeasures = null;
		// Create module measures
		if (context.getComponent().getType() != Component.Type.FILE) {
			
			// Search Lines of Code measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_LOC.key());
			if(childrenMeasures.iterator().hasNext()){
				int sum = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getIntValue();
				}			
				context.addMeasure(SHELL_LOC.key(),sum);				
			}
			
			// Search Lines of Code mean measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_LOC_MEAN.key());
			if(childrenMeasures.iterator().hasNext()){
				double sum = 0;
				int nbItem = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getDoubleValue();
					nbItem++;
				}
				context.addMeasure(SHELL_LOC_MEAN.key(),(nbItem!=0)?sum/nbItem:sum);							
			}

			// Search Lines of Code minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_LOC_MIN.key());
			if(childrenMeasures.iterator().hasNext()){
				int min = 1000;
				for (Measure child : childrenMeasures){
					if(child.getIntValue() < min){
						min = child.getIntValue();
					}
				}
				context.addMeasure(SHELL_LOC_MIN.key(), min);
			}
						
			// Search Lines of Code minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_LOC_MAX.key());
			if(childrenMeasures.iterator().hasNext()){
				int max = 0;
				for (Measure child : childrenMeasures){
					if(child.getIntValue() > max){
						max = child.getIntValue();
					}
				}
				context.addMeasure(SHELL_LOC_MAX.key(), max);
			}
		}
	}
}