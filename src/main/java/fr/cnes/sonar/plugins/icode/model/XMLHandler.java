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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Class used to unmarshal i-Code xml file (results and rules definition).
 *
 * It contains useful methods to handle xml files.
 *
 * @author lequal
 */
public class XMLHandler {

    /**
     * This method use JAXB to unmarshal XML report: it transform simply
     * XML into our Java Object by reading annotations on model classes.
     *
     * @param file File descriptor of the report to import as Java Objects.
     * @param cls Destination class for unmarshalling.
     * @return AnalysisReport: the main structure of the report.
     * @throws JAXBException Exception during conversion can be met.
     */
    static public Object unmarshal(final File file, final Class<?> cls) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return jaxbUnmarshaller.unmarshal(file);
    }

    /**
     * This method use JAXB to unmarshal XML report: it transform simply
     * XML into our Java Object by reading annotations on model classes.
     *
     * @param file Stream of the xml file to import as Java Objects.
     * @param cls Destination class for unmarshalling.
     * @return AnalysisReport: the main structure of the report.
     * @throws JAXBException Exception during conversion can be met.
     */
    static public Object unmarshal(final InputStream file, final Class<?> cls) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return jaxbUnmarshaller.unmarshal(file);
    }

}
