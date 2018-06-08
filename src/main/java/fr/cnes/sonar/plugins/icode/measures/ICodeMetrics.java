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
public class ICodeMetrics implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	
	/** Metric for number of warnings */
	public static final Metric<Integer> NUMBER_OF_WARNINGS = new Metric.Builder(
			"icode-warnings",
			"Number of warning messages",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for number of errors */
	public static final Metric<Integer> NUMBER_OF_ERRORS = new Metric.Builder(
			"icode-errors",
			"Number of error messages",
			Metric.ValueType.INT)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for report files warning */
	public static final Metric<String> REPORT_FILES_WARNING = new Metric.Builder(
			"icode-report-files-warning",
			"Report files warning",
			Metric.ValueType.STRING)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for report files error */
	public static final Metric<String> REPORT_FILES_ERROR = new Metric.Builder(
			"icode-report-files-error",
			"Report files error",
			Metric.ValueType.STRING)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();

	/** Metric for report files error */
	public static final Metric<String> DBG = new Metric.Builder(
			"icode-dbg",
			"Debug report",
			Metric.ValueType.STRING)
			.setDirection(Metric.DIRECTION_WORST) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<>();
		
		res.addAll(Arrays.asList(
				NUMBER_OF_WARNINGS, 
				NUMBER_OF_ERRORS,
				REPORT_FILES_WARNING,
				REPORT_FILES_ERROR));
		
		return res;
	}
}
