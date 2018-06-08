package fr.cnes.sonar.plugins.icode.measures;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinitionContext;

public class ComputePluginErrorsTest {

	@Test
	public void callDefine() {
		MeasureComputerDefinition measureComputerDefinition = new MeasureComputerDefinition() {
			
			@Override
			public Set<String> getOutputMetrics() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<String> getInputMetrics() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		MeasureComputer.MeasureComputerDefinition.Builder builder = Mockito.mock(MeasureComputer.MeasureComputerDefinition.Builder.class);
		Mockito.when(builder.setInputMetrics(new String[] {ICodeMetrics.NUMBER_OF_ERRORS.key(), ICodeMetrics.REPORT_FILES_ERROR.key()})).thenReturn(builder);
		Mockito.when(builder.setOutputMetrics(new String[] {ICodeMetrics.NUMBER_OF_ERRORS.key(), ICodeMetrics.REPORT_FILES_ERROR.key()})).thenReturn(builder);
		Mockito.when(builder.build()).thenReturn(measureComputerDefinition);
		MeasureComputerDefinitionContext measureComputerDefinitionContext = Mockito.mock(MeasureComputerDefinitionContext.class); 
		Mockito.when(measureComputerDefinitionContext.newDefinitionBuilder()).thenReturn(builder);
		ComputePluginErrors computePluginErrors = new ComputePluginErrors();
		computePluginErrors.define(measureComputerDefinitionContext);
	}

	@Test
	public void given_context_when_compute_then_newComputedMeasures(){
		org.sonar.api.ce.measure.Component componentFile = Mockito.mock(org.sonar.api.ce.measure.Component.class);
		Mockito.when(componentFile.getType()).thenReturn(Component.Type.DIRECTORY);
		MeasureComputerContext context = Mockito.mock(MeasureComputerContext.class);
		Mockito.when(context.getComponent()).thenReturn(componentFile);
		ArrayList<Measure> childsMeasures = new ArrayList<Measure>();
		Measure aMeasure = Mockito.mock(Measure.class);
		Mockito.when(aMeasure.getIntValue()).thenReturn(1);
		childsMeasures.add(aMeasure);
		Mockito.when(context.getChildrenMeasures(ICodeMetrics.NUMBER_OF_ERRORS.key())).thenReturn(childsMeasures);
		Mockito.when(context.getChildrenMeasures(ICodeMetrics.REPORT_FILES_ERROR.key())).thenReturn(childsMeasures);
		ComputePluginErrors computePluginErrors = new ComputePluginErrors();
		computePluginErrors.compute(context);
		Mockito.verify(context).addMeasure(ICodeMetrics.NUMBER_OF_ERRORS.key(), 1);
		Mockito.verify(context).addMeasure(ICodeMetrics.REPORT_FILES_ERROR.key(), "Number of report files : "+"1"+" parse "+ ICodeMetrics.REPORT_FILES_ERROR.getName()+" for each file...");
	}

}
