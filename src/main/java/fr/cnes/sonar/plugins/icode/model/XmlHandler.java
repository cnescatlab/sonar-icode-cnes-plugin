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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.InputStream;

/**
 * Class used to unmarshal i-Code xml file (results and rules definition).
 *
 * It contains useful methods to handle xml files.
 */
public class XmlHandler {

    /**
     * Private constructor for this utility class.
     */
    private XmlHandler(){}

    /**
     * This method use XStream to unmarshal XML report: it transform simply
     * XML into our Java Object by reading annotations on model classes.
     *
     * @param file Stream of the xml file to import as Java Objects.
     * @param cls Destination class for unmarshalling.
     * @return AnalysisReport: the main structure of the report.
     */
    public static Object unmarshal(final InputStream file, final Class<?> cls){
        final XStream xStream = new XStream() {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(final Class definedIn, final String fieldName) {
                        return (definedIn != Object.class) && super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        xStream.processAnnotations(cls);
        return xStream.fromXML(file);
    }

}