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

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamInclude;
import fr.cnes.icode.data.CheckResult;
import fr.cnes.sonar.plugins.icode.converter.AnalysisConverter;

import java.util.Objects;

/**
 * Class used to unmarshal i-Code xml file.
 * <p>
 * It contains an issue and a violated rule.
 */
@XStreamInclude(Result.class)
@XStreamConverter(value = AnalysisConverter.class, strings = {"result"})
public class AnalysisRule {

    private String analysisRuleId;
    private Result result;

    public String getAnalysisRuleId() {
        return analysisRuleId;
    }

    public Result getResult() {
        return result;
    }

    public void setAnalysisRuleId(String analysisRuleId) {
        this.analysisRuleId = analysisRuleId;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * Default constructor.
     */
    public AnalysisRule() {
        this.analysisRuleId = "";
        this.result = new Result();
        this.result.setFileName("");
        this.result.setResultId("");
        this.result.setResultLine("");
        this.result.setResultMessage("");
        this.result.setResultTypePlace("");
        this.result.setResultValue("");
    }

    /**
     * Construct a new AnalysisRule from a CheckResult from i-Code core library.
     *
     * @param checkResult A CheckResult from i-Code library.
     */
    public AnalysisRule(final CheckResult checkResult) {
        this.analysisRuleId = checkResult.getName();
        this.result = new Result();
        this.result.setFileName(checkResult.getFile().getPath());
        this.result.setResultId(checkResult.getId());
        this.result.setResultLine(String.valueOf(checkResult.getLine()));
        this.result.setResultMessage(checkResult.getMessage());
        this.result.setResultTypePlace(Objects.isNull(checkResult.getLocation()) || checkResult.getLocation().isEmpty() ? "class" : "method");
        this.result.setResultValue(String.valueOf(checkResult.getValue()));
    }

}


