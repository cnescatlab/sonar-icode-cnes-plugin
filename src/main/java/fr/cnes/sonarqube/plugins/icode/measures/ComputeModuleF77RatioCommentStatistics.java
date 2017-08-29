package fr.cnes.sonarqube.plugins.icode.measures;

import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77RatioComment.F77_RATIO_COMMENT;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MEAN;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MIN;
import static fr.cnes.sonarqube.plugins.icode.measures.ICodeMetricsF77RatioComment.F77_RATIO_COMMENT_MAX;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

/**
 * Compute RATIO_COMMENT into the project.
 * 
 * Each file RATIO_COMMENT is provided by the analyse report file
 * 
 * @see ICodeSensor
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class ComputeModuleF77RatioCommentStatistics implements MeasureComputer {

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
		
	    return defContext.newDefinitionBuilder()
	    		.setInputMetrics(new String[] {F77_RATIO_COMMENT.key(),F77_RATIO_COMMENT_MEAN.key(),F77_RATIO_COMMENT_MIN.key(),F77_RATIO_COMMENT_MAX.key()})
	    		.setOutputMetrics(new String[] {F77_RATIO_COMMENT.key(),F77_RATIO_COMMENT_MEAN.key(),F77_RATIO_COMMENT_MIN.key(),F77_RATIO_COMMENT_MAX.key()})//,DBG.key()})
	    		.build();
	}

	@Override
	public void compute(MeasureComputerContext context) {
		Iterable<Measure> childrenMeasures = null;
		// Create module measures
		if (context.getComponent().getType() != Component.Type.FILE) {
			
			// Search RATIO_COMMENT measure for children files
			childrenMeasures = context.getChildrenMeasures(F77_RATIO_COMMENT.key());
			if(childrenMeasures.iterator().hasNext()){
				double sum = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getDoubleValue();
				}			
				context.addMeasure(F77_RATIO_COMMENT.key(),sum);				
			}
			
			// Search RATIO_COMMENT mean measure for children files
			childrenMeasures = context.getChildrenMeasures(F77_RATIO_COMMENT_MEAN.key());
			if(childrenMeasures.iterator().hasNext()){
				double sum = 0;
				int nbItem = 0;
				for (Measure child : childrenMeasures) {
					sum += child.getDoubleValue();
					nbItem++;
				}
				context.addMeasure(F77_RATIO_COMMENT_MEAN.key(),(nbItem!=0)?sum/nbItem:sum);							
			}

			// Search RATIO_COMMENT minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(F77_RATIO_COMMENT_MIN.key());
			if(childrenMeasures.iterator().hasNext()){
				double min = 1000;
				for (Measure child : childrenMeasures){
					if(child.getDoubleValue() < min){
						min = child.getDoubleValue();
					}
				}
				context.addMeasure(F77_RATIO_COMMENT_MIN.key(), min);
			}
						
			// Search RATIO_COMMENT minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(F77_RATIO_COMMENT_MAX.key());
			if(childrenMeasures.iterator().hasNext()){
				double max = 0;
				for (Measure child : childrenMeasures){
					if(child.getDoubleValue() > max){
						max = child.getDoubleValue();
					}
				}
				context.addMeasure(F77_RATIO_COMMENT_MAX.key(), max);
			}
		}
	}
}