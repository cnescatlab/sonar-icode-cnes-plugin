package fr.cnes.sonarqube.plugins.framac.measures;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * Compute cyclomatic complexity into the project.
 * 
 * Each file cyclomatic complexited is computed by Frama-C (option -metrics) and stored into a result file
 * 
 * @see FramaCSensor
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class ComputeProjectCyclomaticStatistics implements MeasureComputer {

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
		
	    return defContext.newDefinitionBuilder()
	    		.setInputMetrics(new String[] {CyclomaticMetrics.CYCLOMATIC.key(),CyclomaticMetrics.CYCLOMATIC_MEAN.key(),CyclomaticMetrics.CYCLOMATIC_MIN.key(),CyclomaticMetrics.CYCLOMATIC_MAX.key()})
	    		.setOutputMetrics(new String[] {CyclomaticMetrics.CYCLOMATIC.key(),CyclomaticMetrics.CYCLOMATIC_MEAN.key(),CyclomaticMetrics.CYCLOMATIC_MIN.key(),CyclomaticMetrics.CYCLOMATIC_MAX.key()})
	    		.build();
	}

	@Override
	public void compute(MeasureComputerContext context) {
		Iterable<Measure> childrenMeasures = null;
		// Create module measures
		if (context.getComponent().getType() != Component.Type.FILE) {
			
			// Search Cyclomatic measure for children files
			childrenMeasures = context.getChildrenMeasures(CyclomaticMetrics.CYCLOMATIC.key());
			if(childrenMeasures.iterator().hasNext()){
				int sum = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getIntValue();
				}			
				context.addMeasure(CyclomaticMetrics.CYCLOMATIC.key(),sum);				
			}
			
			// Search Cyclomatic mean measure for children files
			childrenMeasures = context.getChildrenMeasures(CyclomaticMetrics.CYCLOMATIC_MEAN.key());
			if(childrenMeasures.iterator().hasNext()){
				double sum = 0;
				int nbItem = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getDoubleValue();
					nbItem++;
				}
				context.addMeasure(CyclomaticMetrics.CYCLOMATIC_MEAN.key(),(nbItem!=0)?sum/nbItem:sum);							
			}

			// Search Cyclomatic minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(CyclomaticMetrics.CYCLOMATIC_MIN.key());
			if(childrenMeasures.iterator().hasNext()){
				int min = 1000;
//				String msg = "";
				for (Measure child : childrenMeasures){
//					msg += "child value for type "+context.getComponent().getType()+" = "+child.getIntValue();
					if(child.getIntValue() < min){
						min = child.getIntValue();
					}
				}
				context.addMeasure(CyclomaticMetrics.CYCLOMATIC_MIN.key(), min);
//				context.addMeasure(DBG.key(), msg);
			}
						
			// Search Cyclomatic minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(CyclomaticMetrics.CYCLOMATIC_MAX.key());
			if(childrenMeasures.iterator().hasNext()){
				int max = 0;
				for (Measure child : childrenMeasures){
					if(child.getIntValue() > max){
						max = child.getIntValue();
					}
				}
				context.addMeasure(CyclomaticMetrics.CYCLOMATIC_MAX.key(), max);
			}
		}
	}
}