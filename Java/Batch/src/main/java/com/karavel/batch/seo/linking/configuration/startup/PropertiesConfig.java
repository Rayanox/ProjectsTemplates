
package com.karavel.batch.seo.linking.configuration.startup;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class PropertiesConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Configuration
    @Profile("default")
    @PropertySource(
            value = StringUtils.EMPTY,
            factory = DefaultCustomPropertySource.class
    )
    static class DefaultConfig {
    }

    @Configuration
    @Profile({"fram"})
    @PropertySource("classpath:seo.linking.batch/conf/fram/application.properties")
    static class FramConfig {
    }


    //    @Configuration
//    @Profile({"fram", "parc7"})
//    @PropertySource({
//            "classpath:seo.linking.batch/conf/fram/application.properties",
//            "classpath:seo.linking.batch/conf/fram/application-parc7.properties"})
//    static class FramParc7Config {
//    }

    @Configuration
    @Profile({"pmvc-be"})
    @PropertySource("classpath:seo.linking.batch/conf/pmvc-be/application.properties")
    static class PmvcBeConfig {
    }
}
