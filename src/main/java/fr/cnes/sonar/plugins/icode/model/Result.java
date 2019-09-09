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

/**
 * Class used to unmarshal i-Code xml file.
 *
 * It contains an issue or a metric.
 *
 * @author lequal
 */
public class Result {

    private String fileName;
    private String resultLine;
    private String resultTypePlace;
    private String resultValue;
    private String resultMessage;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResultLine() {
        return resultLine;
    }

    public void setResultLine(String resultLine) {
        this.resultLine = resultLine;
    }

    public String getResultTypePlace() {
        return resultTypePlace;
    }

    public void setResultTypePlace(String resultTypePlace) {
        this.resultTypePlace = resultTypePlace;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
