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
 * along with sonar-icode-cnes-plugin.  If not, see @XmlElement
    public String http://www.gnu.org/licenses/;.
 */
package fr.cnes.sonar.plugins.icode.model;


/**
 * Class used to unmarshal i-Code xml file.
 *
 * It contains data about defined rule.
 *
 * @author lequal
 */
public class Rule {


    private String key;
    private String name;
    private String internalKey;
    private String description;
    private String severity;
    private String cardinality;
    private String status;
    private String type;
    private String tag;
    private String remediationFunction;
    private String remediationFunctionBaseEffort;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalKey() {
        return internalKey;
    }

    public void setInternalKey(String internalKey) {
        this.internalKey = internalKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRemediationFunction() {
        return remediationFunction;
    }

    public void setRemediationFunction(String remediationFunction) {
        this.remediationFunction = remediationFunction;
    }

    public String getRemediationFunctionBaseEffort() {
        return remediationFunctionBaseEffort;
    }

    public void setRemediationFunctionBaseEffort(String remediationFunctionBaseEffort) {
        this.remediationFunctionBaseEffort = remediationFunctionBaseEffort;
    }
}
