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


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicitCollection;
import com.thoughtworks.xstream.annotations.XStreamInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to unmarshal i-Code xml file.
 *
 * It contains meta data about rules definition.
 *
 * @author lequal
 */
@XStreamAlias("icodelint-rules")
@XStreamImplicitCollection("icodeRules")
@XStreamInclude(Rule.class)
public class RulesDefinition {

    @XStreamAlias("rule")
    public List<Rule> icodeRules;

    /**
     * Getter for accessing definedRules (definition).
     * @return A list of Rule.
     */
    public List<Rule> getRules() {
        // Retrieve issues (called rules)
        List<Rule> rules;
        if(this.icodeRules !=null) {
            rules = this.icodeRules;
        } else {
            rules = new ArrayList<>();
        }
        return rules;
    }

}
