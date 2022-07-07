package com.karavel.batch.seo.linking.configuration.annotations;

import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Service
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlSejlistService {

    SitesEnum[] applicableSites();
}
