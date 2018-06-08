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
package fr.cnes.sonar.plugins.icode.measures;

import static fr.cnes.sonar.plugins.icode.measures.ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT;
import static fr.cnes.sonar.plugins.icode.measures.ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT_MEAN;
import static fr.cnes.sonar.plugins.icode.measures.ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT_MIN;
import static fr.cnes.sonar.plugins.icode.measures.ICodeMetricsSHELLRatioComment.SHELL_RATIO_COMMENT_MAX;

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
public class ComputeModuleSHELLRatioCommentStatistics implements MeasureComputer {

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
		
	    String[] metricTab = new String[] {SHELL_RATIO_COMMENT.key(),SHELL_RATIO_COMMENT_MEAN.key(),SHELL_RATIO_COMMENT_MIN.key(),SHELL_RATIO_COMMENT_MAX.key()};
		return defContext.newDefinitionBuilder()
	    		.setInputMetrics(metricTab)
	    		.setOutputMetrics(metricTab)
	    		.build();
	}

	@Override
	public void compute(MeasureComputerContext context) {
		Iterable<Measure> childrenMeasures = null;
		// Create module measures
		if (context.getComponent().getType() != Component.Type.FILE) {
			
			// Search RATIO_COMMENT measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_RATIO_COMMENT.key());
			compute(context, childrenMeasures);
			
			// Search RATIO_COMMENT mean measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_RATIO_COMMENT_MEAN.key());
			computeMean(context, childrenMeasures);

			// Search RATIO_COMMENT minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_RATIO_COMMENT_MIN.key());
			computeMin(context, childrenMeasures);
						
			// Search RATIO_COMMENT minimum measure for children files
			childrenMeasures = context.getChildrenMeasures(SHELL_RATIO_COMMENT_MAX.key());
			computeMax(context, childrenMeasures);
		}
	}

	private void computeMax(MeasureComputerContext context, Iterable<Measure> childrenMeasures) {
		if(childrenMeasures.iterator().hasNext()){
			double max = 0;
			for (Measure child : childrenMeasures){
				if(child.getDoubleValue() > max){
					max = child.getDoubleValue();
				}
			}
			context.addMeasure(SHELL_RATIO_COMMENT_MAX.key(), max);
		}
	}

	private void computeMin(MeasureComputerContext context, Iterable<Measure> childrenMeasures) {
		if(childrenMeasures.iterator().hasNext()){
			double min = 1000;
			for (Measure child : childrenMeasures){
				if(child.getDoubleValue() < min){
					min = child.getDoubleValue();
				}
			}
			context.addMeasure(SHELL_RATIO_COMMENT_MIN.key(), min);
		}
	}

	private void computeMean(MeasureComputerContext context, Iterable<Measure> childrenMeasures) {
		if(childrenMeasures.iterator().hasNext()){
			double sum = 0;
			int nbItem = 0;
			for (Measure child : childrenMeasures) {
				sum += child.getDoubleValue();
				nbItem++;
			}
			context.addMeasure(SHELL_RATIO_COMMENT_MEAN.key(),(nbItem!=0)?sum/nbItem:sum);							
		}
	}

	private void compute(MeasureComputerContext context, Iterable<Measure> childrenMeasures) {
		if(childrenMeasures.iterator().hasNext()){
			double sum = 0;
			for (Measure child : childrenMeasures) {
				sum += child.getDoubleValue();
			}			
			context.addMeasure(SHELL_RATIO_COMMENT.key(),sum);				
		}
	}
}