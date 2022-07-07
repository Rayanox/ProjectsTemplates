package com.karavel.batch.seo.linking.configuration.converter;


import org.apache.commons.collections.ListUtils;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@ConfigurationPropertiesBinding
public class IntegerListConverter implements Converter<String, List<Integer>> {

    private static final String SPLIT_CHARACTER = ";";

    @Override
    public List<Integer> convert(String source) {
        if(source == null)
            return ListUtils.EMPTY_LIST;
        try {
            return Stream.of(source.split(SPLIT_CHARACTER))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

        }catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage() + ". Property value " + source + "should contain a list of only Integers");
        }
    }
}
