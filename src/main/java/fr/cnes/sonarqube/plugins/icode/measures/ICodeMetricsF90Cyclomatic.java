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
package fr.cnes.sonarqube.plugins.icode.measures;

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
public class ICodeMetricsF90Cyclomatic implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for cyclomatic complexity */
	public static final Metric<Integer> F90_CYCLOMATIC = new Metric.Builder(
			"icode-f90-cyclomatic-complexity",
			"F90 : ComplexitySimplified",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for minimum cyclomatic complexity */
	public static final Metric<Integer> F90_CYCLOMATIC_MIN = new Metric.Builder(
			"icode-f90-cyclomatic-complexity-min",
			"F90 : ComplexitySimplified (Minimum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for maximum cyclomatic complexity */
	public static final Metric<Integer> F90_CYCLOMATIC_MAX = new Metric.Builder(
			"icode-f90-cyclomatic-complexity-max",
			"F90 : ComplexitySimplified (Maximum)",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric project for mean cyclomatic complexity */
	public static final Metric<Double> F90_CYCLOMATIC_MEAN = new Metric.Builder(
			"icode-f90-cyclomatic-complexity-mean",
			"F90 : ComplexitySimplified (Mean)",
			Metric.ValueType.FLOAT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		res.addAll(Arrays.asList(
				F90_CYCLOMATIC, 
				F90_CYCLOMATIC_MIN,
				F90_CYCLOMATIC_MAX,
				F90_CYCLOMATIC_MEAN));
				
		return res;
	}
}
