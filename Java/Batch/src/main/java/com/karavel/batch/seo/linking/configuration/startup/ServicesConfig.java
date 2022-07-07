package com.karavel.batch.seo.linking.configuration.startup;

import com.karavel.catalogue.sejour.jax_ws.ICatalogueSejourServiceJaxWsFacade;
import com.karavel.catalogue.sejour.jax_ws.IRechercheSejourServiceJaxWsFacade;
import com.karavel.catalogue.sejour.jax_ws.IReferentielGeographiqueManagementServiceJaxWsFacade;
import com.karavel.catalogue.sejour.jax_ws.IReferentielGeographiqueServiceJaxWsFacade;
import com.karavel.url.referentiel.generator.ecotour.IUrlGeneratorEcotour;
import com.karavel.url.referentiel.generator.fram.IUrlGeneratorFram;
import com.karavel.url.referentiel.generator.framb2c.IUrlGeneratorFramB2c;
import com.karavel.url.referentiel.generator.odigeo.IUrlGeneratorOdigeo;
import com.karavel.url.referentiel.generator.pmvc.IUrlGenerator;
import com.karavel.url.referentiel.generator.ppc.IUrlGeneratorPPC;
import com.karavel.url.referentiel.precalcul.service.UrlReferentielPrecalculGenerationService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    private EnvironmentUrlConfig environmentConfig;


    @Bean(name = "geographiqueReferentielWebService")
    public IReferentielGeographiqueServiceJaxWsFacade geographieReferentielWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IReferentielGeographiqueServiceJaxWsFacade.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourReferentielgeographique());
        return (IReferentielGeographiqueServiceJaxWsFacade) factoryBean.create();
    }

    @Bean(name = "managementGeographieWebService")
    public IReferentielGeographiqueManagementServiceJaxWsFacade managementGeographieWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IReferentielGeographiqueManagementServiceJaxWsFacade.class);
        factoryBean.setAddress(environmentConfig.getServiceManagementCatalogueSejourReferentielgeographique());
        return (IReferentielGeographiqueManagementServiceJaxWsFacade) factoryBean.create();
    }

    @Bean(name = "catalogueSejourWebService")
    public ICatalogueSejourServiceJaxWsFacade catalogueSejourWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(ICatalogueSejourServiceJaxWsFacade.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourCatalogueSejourService());
        return (ICatalogueSejourServiceJaxWsFacade) factoryBean.create();
    }


    /******************
     *                *
     *     URLs FP    *
     *                *
     ******************/

    @Bean
    public UrlReferentielPrecalculGenerationService getUrlFpDefaultWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(UrlReferentielPrecalculGenerationService.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorFpService());
        return (UrlReferentielPrecalculGenerationService) factoryBean.create();
    }

    @Bean
    public IUrlGeneratorEcotour getUrlFpEcotourWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IUrlGeneratorEcotour.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorFpService());
        return (IUrlGeneratorEcotour) factoryBean.create();
    }

    @Bean
    public IUrlGeneratorPPC getUrlFpPpcWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IUrlGeneratorPPC.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorFpService());
        return (IUrlGeneratorPPC) factoryBean.create();
    }




    /******************
     *                *
     *     URLs SL    *
     *                *
     ******************/


    @Bean
    public IUrlGeneratorFramB2c getUrlSlFramWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IUrlGeneratorFramB2c.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorService());
        return (IUrlGeneratorFramB2c) factoryBean.create();
    }

    @Bean
    public IUrlGeneratorFram getUrlSlFramB2BWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IUrlGeneratorFram.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorService());
        return (IUrlGeneratorFram) factoryBean.create();
    }

    @Bean
    public IRechercheSejourServiceJaxWsFacade getUrlSlEcotourWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IRechercheSejourServiceJaxWsFacade.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorService());
        return (IRechercheSejourServiceJaxWsFacade) factoryBean.create();
    }

    @Bean
    public IUrlGeneratorOdigeo getUrlSlOdigeoWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IUrlGeneratorOdigeo.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorService());
        return (IUrlGeneratorOdigeo) factoryBean.create();
    }

    @Bean
    public IUrlGenerator getUrlSlPmvcWebService() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IUrlGenerator.class);
        factoryBean.setAddress(environmentConfig.getServiceCatalogueSejourUrlGeneratorService());
        return (IUrlGenerator) factoryBean.create();
    }





    @Autowired
    public void setEnvironmentConfig(EnvironmentUrlConfig environmentConfig) {
        this.environmentConfig = environmentConfig;
    }
}
