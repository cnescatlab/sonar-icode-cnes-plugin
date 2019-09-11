package fr.cnes.sonar.plugins.icode.converter;

import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;
import fr.cnes.sonar.plugins.icode.model.AnalysisRule;
import fr.cnes.sonar.plugins.icode.model.Result;

public class AnalysisConverter extends ToAttributedValueConverter {

    public AnalysisConverter(Class type, Mapper mapper, ReflectionProvider reflectionProvider, ConverterLookup lookup) {
        super(type, mapper, reflectionProvider, lookup);
    }

    public AnalysisConverter(Class type, Mapper mapper, ReflectionProvider reflectionProvider, ConverterLookup lookup, String valueFieldName) {
        super(type, mapper, reflectionProvider, lookup, valueFieldName);
    }

    public AnalysisConverter(Class type, Mapper mapper, ReflectionProvider reflectionProvider, ConverterLookup lookup, String valueFieldName, Class valueDefinedIn) {
        super(type, mapper, reflectionProvider, lookup, valueFieldName, valueDefinedIn);
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
