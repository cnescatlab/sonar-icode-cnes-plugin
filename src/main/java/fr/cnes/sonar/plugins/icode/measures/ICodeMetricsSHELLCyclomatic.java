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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;


/**
 * 
 * Cyclomatic : La complexité cyclomatique (McCabe)
 * Nesting : Le niveau d’imbrication / nesting level
 * RatioComment : Le taux de commentaire
 * LineOfCode : le nombre de lignes de code hors commentaires et lignes vides
 *
 * @author Cyrille FRANCOIS
 * 
 */
public class ICodeMetricsSHELLCyclomatic implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for cyclomatic complexity */
	public static final Metric<Integer> SHELL_CYCLOMATIC = new Metric.Builder(
			"icode-shell-cyclomatic-complexity",
			"SHELL : ComplexitySimplified",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for minimum cyclomatic complexity */
	public static final Metric<Integer> SHELL_CYCLOMATIC_MIN = new Metric.Builder(
			"icode-shell-cyclomatic-complexity-min",
			"SHELL : ComplexitySimplified (Minimum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for maximum cyclomatic complexity */
	public static final Metric<Integer> SHELL_CYCLOMATIC_MAX = new Metric.Builder(
			"icode-shell-cyclomatic-complexity-max",
			"SHELL : ComplexitySimplified (Maximum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for mean cyclomatic complexity */
	public static final Metric<Double> SHELL_CYCLOMATIC_MEAN = new Metric.Builder(
			"icode-shell-cyclomatic-complexity-mean",
			"SHELL : ComplexitySimplified (Mean)",
			Metric.ValueType.FLOAT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<>();
		
		res.addAll(Arrays.asList(
				SHELL_CYCLOMATIC, 
				SHELL_CYCLOMATIC_MIN,
				SHELL_CYCLOMATIC_MAX,
				SHELL_CYCLOMATIC_MEAN));
				
		return res;
	}
}
