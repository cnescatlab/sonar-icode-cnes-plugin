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

import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinitionContext;
import org.sonar.api.measures.Metric;

import java.util.ArrayList;
import java.util.List;

public class ComputeNestingMetricTest {

    @Test
    public void callDefine() {
        MeasureComputerDefinition measureComputerDefinition = Mockito.mock(MeasureComputerDefinition.class);
        MeasureComputer.MeasureComputerDefinition.Builder builder = Mockito.mock(MeasureComputer.MeasureComputerDefinition.Builder.class);
        List<Metric> allMetrics = (new ICodeNestingMetric()).getMetrics();
        String[] metricsKey = new String[]{allMetrics.get(0).key()};
        Mockito.when(builder.setInputMetrics(metricsKey)).thenReturn(builder);
        Mockito.when(builder.setOutputMetrics(metricsKey)).thenReturn(builder);
        Mockito.when(builder.build()).thenReturn(measureComputerDefinition);
        MeasureComputerDefinitionContext measureComputerDefinitionContext = Mockito.mock(MeasureComputerDefinitionContext.class);
        Mockito.when(measureComputerDefinitionContext.newDefinitionBuilder()).thenReturn(builder);
        ICodeNestingMetric compute = new ICodeNestingMetric();
        compute.define(measureComputerDefinitionContext);
    }

    @Test
    public void given_context_when_compute_then_newComputedMeasures() {
        org.sonar.api.ce.measure.Component componentFile = Mockito.mock(org.sonar.api.ce.measure.Component.class);
        Mockito.when(componentFile.getType()).thenReturn(Component.Type.DIRECTORY);
        MeasureComputerContext context = Mockito.mock(MeasureComputerContext.class);
        Mockito.when(context.getComponent()).thenReturn(componentFile);
        ArrayList<Measure> childsMeasures = new ArrayList<>();
        Measure aMeasureMin = Mockito.mock(Measure.class);
        Mockito.when(aMeasureMin.getIntValue()).thenReturn(1);
        Mockito.when(aMeasureMin.getDoubleValue()).thenReturn(1.);
        Measure aMeasureMax = Mockito.mock(Measure.class);
        Mockito.when(aMeasureMax.getIntValue()).thenReturn(5);
        Mockito.when(aMeasureMax.getDoubleValue()).thenReturn(5.);
        childsMeasures.add(aMeasureMin);
        childsMeasures.add(aMeasureMax);
        Mockito.when(context.getChildrenMeasures(ICodeNestingMetric.NESTING_MAX.key())).thenReturn(childsMeasures);
        ICodeNestingMetric computeModule = new ICodeNestingMetric();
        computeModule.compute(context);
        Mockito.verify(context).addMeasure(ICodeNestingMetric.NESTING_MAX.key(), 5);
    }


}
