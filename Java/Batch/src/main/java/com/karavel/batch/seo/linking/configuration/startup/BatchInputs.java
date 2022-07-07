package com.karavel.batch.seo.linking.configuration.startup;


import java.util.Date;

import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.exceptions.GenerationTypeNotProvidedException;
import com.karavel.batch.seo.linking.exceptions.SiteIdPropertyNotSetException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchInputs {

    private GenerationType generationType;
    private SitesEnum site;

    //TODO A retirer les props ci-dessous
    private Date purgeDate;
    private Date insertionDate;

    public BatchInputs(GlobalConfiguration configuration) {
        if(configuration.getLvSite() == null)
            throw new SiteIdPropertyNotSetException();

        this.site = SitesEnum.getSiteIfExisted(configuration.getLvSite())
                             .orElseThrow(() -> new RuntimeException("SiteId not existing inside of SiteEnum enum"));
        this.generationType = getGenerationTypeFromParameters();
    }

    private GenerationType getGenerationTypeFromParameters() {
        String generationTypeString = System.getProperty("generationType");
        if(generationTypeString == null)
            throw new GenerationTypeNotProvidedException();

        return GenerationType.buildFromStringValue(generationTypeString)
                .orElseThrow(() -> new GenerationTypeNotProvidedException("'generationType' given does not exist in the GenerationType enum"));
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    public SitesEnum getSite() {
        return site;
    }

    public void setSite(SitesEnum site) {
        this.site = site;
    }

    public Date getPurgeDate() {
        return purgeDate;
    }

    public void setPurgeDate(Date purgeDate) {
        this.purgeDate = purgeDate;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }
}

