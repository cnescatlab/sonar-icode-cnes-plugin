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

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.Collections;
import java.util.List;

/**
 * Executed during sonar-scanner call.
 * Import i-Code nesting metric into SonarQube.
 */
public class ICodeNestingMetric implements Metrics, MeasureComputer {

    /** Name of the category in which defined metrics should be displayed **/
    public static final String DOMAIN = "Size";

    /** Key of the nesting metric. **/
    public static final String KEY = "icode-nesting-max";

    /** Metric for maximum nested levels **/
    public static final Metric<Integer> NESTING_MAX = new Metric.Builder(
            KEY,
            "Nesting (Maximum)",
            Metric.ValueType.INT)
            .setDescription("Maximum number of nested statements levels.")
            .setDirection(Metric.DIRECTION_WORST)
            .setQualitative(false)
            .setHidden(false)
            .setDomain(DOMAIN).create();

    /**
     * Save the Nesting metric into SonarQube.
     *
     * @return The list of metrics containing nesting.
     */
    @Override
    public List<Metric> getMetrics() {
        return Collections.singletonList(NESTING_MAX);
    }

    /**
     * Define which measures are expected in in- and out- put.
     *
     * @param defContext Context for the measure.
     * @return The definition of the computer.
     */
    @Override
    public MeasureComputerDefinition define(final MeasureComputerDefinitionContext defContext) {
        MeasureComputerDefinition.Builder def = defContext.newDefinitionBuilder();
        def.setInputMetrics(KEY);
        def.setOutputMetrics(KEY);
        return def.build();
    }

    /**
     * Compute nesting for high level components.
     *
     * @param context Context of the computation.
     */
    @Override
    public void compute(final MeasureComputerContext context) {
        Iterable<Measure> children = context.getChildrenMeasures(KEY);
        if(children.iterator().hasNext()){
            int max = 0;
            for (final Measure child : children){
                max = Math.max(max, child.getIntValue());
            }
            context.addMeasure(KEY, max);
        }
    }
}
