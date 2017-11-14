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
public class ICodeMetricsSHELLRatioComment implements Metrics {
	
	public static final String DOMAIN = "ICode";
	
	/** Metric for ratio comment */
	public static final Metric<Double> SHELL_RATIO_COMMENT = new Metric.Builder(
			"icode-shell-ratio-comment",
			"SHELL : RatioComment",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Double> SHELL_RATIO_COMMENT_MIN= new Metric.Builder(
			"icode-shell-ratio-comment-min",
			"SHELL : RatioComment (Minimum)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Minimum)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Double> SHELL_RATIO_COMMENT_MAX = new Metric.Builder(
			"icode-shell-ratio-comment-max",
			"SHELL : RatioComment (Maximum)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Maximum)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	
	/** Metric project for maximum ratio comment */
	public static final Metric<Double> SHELL_RATIO_COMMENT_MEAN= new Metric.Builder(
			"icode-shell-ratio-comment-mean",
			"SHELL : RatioComment (Mean)",
			Metric.ValueType.FLOAT)
			.setDescription("Ratio Comment (Mean)")
			.setDirection(Metric.DIRECTION_BETTER) // Metric.DIRECTION_NONE, Metric.DIRECTION_BETTER, Metric.DIRECTION_WORST
            .setQualitative(false) // by default false, tru => Highlighted into gui		
			.setDomain(DOMAIN).create();
	

	
	@SuppressWarnings({ "rawtypes" })
	public List<Metric> getMetrics() {
		ArrayList<Metric> res = new ArrayList<Metric>();
		
		res.addAll(Arrays.asList(
				SHELL_RATIO_COMMENT, 
				SHELL_RATIO_COMMENT_MIN,
				SHELL_RATIO_COMMENT_MAX,
				SHELL_RATIO_COMMENT_MEAN));
				
		return res;
	}
}
