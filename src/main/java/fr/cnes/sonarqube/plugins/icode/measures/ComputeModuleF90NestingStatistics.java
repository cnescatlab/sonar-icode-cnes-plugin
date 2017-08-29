package fr.cnes.sonarqube.plugins.icode.measures;

//import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetrics.DBG;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90Nesting.F90_NESTING;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90Nesting.F90_NESTING_MEAN;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90Nesting.F90_NESTING_MIN;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF90Nesting.F90_NESTING_MAX;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * Compute NESTING complexity into the project.
 * 
 * Each file NESTING complexited is provided by the analyse report file
 * 
 * @see ICodeSensor
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class ComputeModuleF90NestingStatistics implements MeasureComputer {

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
		
	    return defContext.newDefinitionBuilder()
	    		.setInputMetrics(new String[] {F90_NESTING.key(),F90_NESTING_MEAN.key(),F90_NESTING_MIN.key(),F90_NESTING_MAX.key()})
	    		.setOutputMetrics(new String[] {F90_NESTING.key(),F90_NESTING_MEAN.key(),F90_NESTING_MIN.key(),F90_NESTING_MAX.key()})//,DBG.key()})
	    		.build();
	}

	@Override
	public void compute(MeasureComputerContext context) {
		Iterable<Measure> childrenMeasures = null;
		// Create module measures
		if (context.getComponent().getType() != Component.Type.FILE) {
			
			// Search NESTING measure for children files
			childrenMeasures = context.getChildrenMeasures(F90_NESTING.key());
			if(childrenMeasures.iterator().hasNext()){
				int sum = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getIntValue();
				}			
				context.addMeasure(F90_NESTING.key(),sum);				
			}
			
			// Search NESTING mean measure for children files
			childrenMeasures = context.getChildrenMeasures(F90_NESTING_MEAN.key());
			if(childrenMeasures.iterator().hasNext()){
				double sum = 0;
				int nbItem = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getDoubleValue();
					nbItem++;
				}
				context.addMeasure(F90_NESTING_MEAN.key(),(nbItem!=0)?sum/nbItem:sum);							
			}

			// Search NESTING minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(F90_NESTING_MIN.key());
			if(childrenMeasures.iterator().hasNext()){
				int min = 1000;
//				String msg = "";
				for (Measure child : childrenMeasures){
//					msg += "child value for type "+context.getComponent().getType()+" = "+child.getIntValue();
					if(child.getIntValue() < min){
						min = child.getIntValue();
					}
				}
				context.addMeasure(F90_NESTING_MIN.key(), min);
//				context.addMeasure(DBG.key(), msg);
			}
						
			// Search NESTING minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(F90_NESTING_MAX.key());
			if(childrenMeasures.iterator().hasNext()){
				int max = 0;
				for (Measure child : childrenMeasures){
					if(child.getIntValue() > max){
						max = child.getIntValue();
					}
				}
				context.addMeasure(F90_NESTING_MAX.key(), max);
			}
		}
	}
}