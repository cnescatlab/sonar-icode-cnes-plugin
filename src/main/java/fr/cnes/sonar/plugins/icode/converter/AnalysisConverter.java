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
package fr.cnes.sonar.plugins.icode.converter;

import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;
import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import fr.cnes.sonar.plugins.icode.model.Result;

/**
 * Single purpose class to convert i-Code result into usable java objects.
 */
public class AnalysisConverter extends ToAttributedValueConverter {

    public AnalysisConverter(Class type, Mapper mapper, ReflectionProvider reflectionProvider, ConverterLookup lookup, String valueFieldName) {
        super(type, mapper, reflectionProvider, lookup, valueFieldName);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext unmarshallingContext) {
        Object r = super.unmarshal(reader, unmarshallingContext);
        if (r.getClass() == AnalysisRule.class) {
            Result result = new Result();
            reader.moveDown();
            result.setFileName(reader.getAttribute("fileName"));
            result.setResultLine(reader.getAttribute("resultLine"));
            result.setResultTypePlace(reader.getAttribute("resultTypePlace"));
            result.setResultValue(reader.getAttribute("resultValue"));
            if(reader.hasMoreChildren()) {
                reader.moveDown();
                result.setResultMessage(reader.getValue());
                reader.moveUp();
            }
            reader.moveUp();
            ((AnalysisRule) r).setResult(result);
        }
        return r;
    }
}
