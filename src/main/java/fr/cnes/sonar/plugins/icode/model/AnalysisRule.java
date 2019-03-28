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
package fr.cnes.sonar.plugins.icode.model;

import fr.cnes.icode.datas.CheckResult;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 * Class used to unmarshal i-Code xml file.
 *
 * It contains an issue and a violated rule.
 *
 * @author lequal
 */
public class AnalysisRule {

    @XmlAttribute
    public String analysisRuleId;
    @XmlElement
    public Result result;

    /**
     * Default constructor.
     */
    public AnalysisRule() {
        this.analysisRuleId = "";
        this.result = new Result();
        this.result.fileName = "";
        this.result.resultId = "";
        this.result.resultMessage = "";
        this.result.resultLine = "";
        this.result.resultNamePlace = "";
        this.result.resultTypePlace = "";
        this.result.resultValue = "";
    }

    /**
     * Construct a new AnalysisRule from a CheckResult from i-Code core library.
     *
     * @param checkResult A CheckResult from i-Code library.
     */
    public AnalysisRule(final CheckResult checkResult) {
        this.analysisRuleId = checkResult.getName();
        this.result = new Result();
        this.result.fileName = checkResult.getFile().getPath();
        this.result.resultId = checkResult.getId();
        this.result.resultMessage = checkResult.getMessage();
        this.result.resultLine = String.valueOf(checkResult.getLine());
        this.result.resultNamePlace = checkResult.getLocation();
        this.result.resultTypePlace = Objects.isNull(checkResult.getLocation()) || checkResult.getLocation().isEmpty() ? "class" : "method";
        this.result.resultValue = String.valueOf(checkResult.getValue());
    }

}
