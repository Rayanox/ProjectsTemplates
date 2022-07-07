package com.karavel.batch.seo.linking.configuration.startup;

import com.karavel.batch.seo.linking.configuration.annotations.UrlSejlistService;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.UrlGenerationService;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.fp.DefaultUrlFpService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SenderConfig {

    @Bean
    UrlGenerationService fpGenerationService(DefaultUrlFpService defaultUrlFpService) {
        return defaultUrlFpService;
    }

    @Bean
    UrlGenerationService slGenerationService(List<UrlGenerationService> urlGenerationServices, GlobalConfiguration conf) {
        return urlGenerationServices.stream()
                .filter(service -> isServiceOfSite(service, conf))
                .findFirst()
                .orElseThrow(() -> new NoSuchBeanDefinitionException("A configuration is missing -> No bean of type UrlGenerationService has bean found for SL Url generation for this SiteId: " + conf.getLvSite()));
    }

    private boolean isServiceOfSite(UrlGenerationService service, GlobalConfiguration conf) {
        Class<UrlGenerationService> serviceClass = (Class<UrlGenerationService>) service.getClass();

        if(!serviceClass.isAnnotationPresent(UrlSejlistService.class))
            return false;

        return Arrays.stream(serviceClass.getAnnotation(UrlSejlistService.class).applicableSites())
                .anyMatch(site -> site.getLvSite().equals(conf.getLvSite()));
    }

}
