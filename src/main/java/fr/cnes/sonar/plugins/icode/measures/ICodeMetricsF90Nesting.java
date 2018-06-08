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
public class ICodeMetricsF90Nesting implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for nested statements */
	public static final Metric<Integer> F90_NESTING = new Metric.Builder(
			"icode-f90-nesting",
			"F90 : Nesting",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Integer> F90_NESTING_MIN= new Metric.Builder(
			"icode-f90-nesting-min",
			"F90 : Nesting (Minimum)",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements (Minimum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Integer> F90_NESTING_MAX = new Metric.Builder(
			"icode-f90-nesting-max",
			"F90 : Nesting (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of nested statements (Maximum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum nested statements */
	public static final Metric<Double> F90_NESTING_MEAN= new Metric.Builder(
			"icode-f90-nesting-mean",
			"F90 : Nesting (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Number of nested statements (Mean)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<>();
		
		res.addAll(Arrays.asList(
				F90_NESTING, 
				F90_NESTING_MIN,
				F90_NESTING_MAX,
				F90_NESTING_MEAN));
				
		return res;
	}
}
