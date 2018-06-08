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
public class ICodeMetricsF90LinesOfCode implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for number of lines of code */
	public static final Metric<Integer> F90_LOC = new Metric.Builder(
			"icode-f90-loc",
			"F90 : LineOfCode",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for minimum number of lines of code */
	public static final Metric<Integer> F90_LOC_MIN = new Metric.Builder(
			"icode-f90-loc-min",
			"F90 : LineOfCode (Minimum)",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (Minimum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum number of lines of code */
	public static final Metric<Integer> F90_LOC_MAX = new Metric.Builder(
			"icode-f90-loc-max",
			"F90 : LineOfCode (Maximum)",
			Metric.ValueType.INT)
			.setDescription("Number of lines of code (Maximum)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for mean number of lines of code */
	public static final Metric<Double> F90_LOC_MEAN = new Metric.Builder(
			"icode-f90-loc-mean",
			"F90 : LineOfCode (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Number of lines of code (Mean)")
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<>();
		
		res.addAll(Arrays.asList(
				F90_LOC, 
				F90_LOC_MIN,
				F90_LOC_MAX,
				F90_LOC_MEAN));
				
		return res;
	}
}
