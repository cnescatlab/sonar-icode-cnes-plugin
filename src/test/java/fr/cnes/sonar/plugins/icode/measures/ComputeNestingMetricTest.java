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
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinitionContext;
import org.sonar.api.measures.Metric;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ComputeNestingMetricTest {

    @Test
    public void test_metric_definition() {
        MeasureComputerDefinition measureComputerDefinition = mock(MeasureComputerDefinition.class);
        MeasureComputer.MeasureComputerDefinition.Builder builder = mock(MeasureComputer.MeasureComputerDefinition.Builder.class);
        List<Metric> allMetrics = (new ICodeNestingMetric()).getMetrics();
        String[] metricsKey = new String[]{allMetrics.get(0).key()};
        when(builder.setInputMetrics(metricsKey)).thenReturn(builder);
        when(builder.setOutputMetrics(metricsKey)).thenReturn(builder);
        when(builder.build()).thenReturn(measureComputerDefinition);
        MeasureComputerDefinitionContext measureComputerDefinitionContext = mock(MeasureComputerDefinitionContext.class);
        when(measureComputerDefinitionContext.newDefinitionBuilder()).thenReturn(builder);
        ICodeNestingMetric compute = new ICodeNestingMetric();
        compute.define(measureComputerDefinitionContext);
    }

    @Test
    public void test_given_context_when_compute_then_newComputedMeasures() {
        org.sonar.api.ce.measure.Component componentFile = mock(org.sonar.api.ce.measure.Component.class);
        when(componentFile.getType()).thenReturn(Component.Type.DIRECTORY);
        MeasureComputerContext context = mock(MeasureComputerContext.class);
        when(context.getComponent()).thenReturn(componentFile);
        ArrayList<Measure> childsMeasures = new ArrayList<>();
        Measure aMeasureMin = mock(Measure.class);
        when(aMeasureMin.getIntValue()).thenReturn(1);
        when(aMeasureMin.getDoubleValue()).thenReturn(1.);
        Measure aMeasureMax = mock(Measure.class);
        when(aMeasureMax.getIntValue()).thenReturn(5);
        when(aMeasureMax.getDoubleValue()).thenReturn(5.);
        childsMeasures.add(aMeasureMin);
        childsMeasures.add(aMeasureMax);
        when(context.getChildrenMeasures(ICodeNestingMetric.NESTING_MAX.key())).thenReturn(childsMeasures);
        ICodeNestingMetric computeModule = new ICodeNestingMetric();
        computeModule.compute(context);
        Mockito.verify(context).addMeasure(ICodeNestingMetric.NESTING_MAX.key(), 5);
    }

    @Test
    public void test_compute_metric_without_children() {

        // Create a mock for the context.
        MeasureComputerContext context = mock(MeasureComputerContext.class);

        final Iterable iter = () -> new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };

        when(context.getChildrenMeasures(ICodeNestingMetric.KEY)).thenReturn(iter);
        doAnswer(invocationOnMock -> fail()).when(context).addMeasure(anyString(), anyInt());

        final ICodeNestingMetric nestingMetric = new ICodeNestingMetric();
        nestingMetric.compute(context);
        Assertions.assertTrue(true);
    }
}
