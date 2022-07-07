package com.karavel.batch.seo.linking.configuration.startup;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "conf.karavel.brand.url")
public class EnvironmentUrlConfig {

    private String serviceCatalogueSejourRechercheSejourService;
    private String serviceCatalogueSejourUrlGeneratorService;
    private String serviceCatalogueSejourUrlGeneratorFpService;
    private String serviceCatalogueSejourCatalogueSejourService;
    private String serviceCatalogueSejourReferentielgeographique;
    private String serviceManagementCatalogueSejourReferentielgeographique;

    public String getServiceCatalogueSejourRechercheSejourService() {
        return serviceCatalogueSejourRechercheSejourService;
    }

    public void setServiceCatalogueSejourRechercheSejourService(String serviceCatalogueSejourRechercheSejourService) {
        this.serviceCatalogueSejourRechercheSejourService = serviceCatalogueSejourRechercheSejourService;
    }

    public String getServiceCatalogueSejourUrlGeneratorService() {
        return serviceCatalogueSejourUrlGeneratorService;
    }

    public void setServiceCatalogueSejourUrlGeneratorService(String serviceCatalogueSejourUrlGeneratorService) {
        this.serviceCatalogueSejourUrlGeneratorService = serviceCatalogueSejourUrlGeneratorService;
    }

    public String getServiceCatalogueSejourUrlGeneratorFpService() {
        return serviceCatalogueSejourUrlGeneratorFpService;
    }

    public void setServiceCatalogueSejourUrlGeneratorFpService(String serviceCatalogueSejourUrlGeneratorFpService) {
        this.serviceCatalogueSejourUrlGeneratorFpService = serviceCatalogueSejourUrlGeneratorFpService;
    }

    public String getServiceCatalogueSejourCatalogueSejourService() {
        return serviceCatalogueSejourCatalogueSejourService;
    }

    public void setServiceCatalogueSejourCatalogueSejourService(String serviceCatalogueSejourCatalogueSejourService) {
        this.serviceCatalogueSejourCatalogueSejourService = serviceCatalogueSejourCatalogueSejourService;
    }

    public String getServiceCatalogueSejourReferentielgeographique() {
        return serviceCatalogueSejourReferentielgeographique;
    }

    public void setServiceCatalogueSejourReferentielgeographique(String serviceCatalogueSejourReferentielgeographique) {
        this.serviceCatalogueSejourReferentielgeographique = serviceCatalogueSejourReferentielgeographique;
    }

    public String getServiceManagementCatalogueSejourReferentielgeographique() {
        return serviceManagementCatalogueSejourReferentielgeographique;
    }

    public void setServiceManagementCatalogueSejourReferentielgeographique(String serviceManagementCatalogueSejourReferentielgeographique) {
        this.serviceManagementCatalogueSejourReferentielgeographique = serviceManagementCatalogueSejourReferentielgeographique;
    }
}
