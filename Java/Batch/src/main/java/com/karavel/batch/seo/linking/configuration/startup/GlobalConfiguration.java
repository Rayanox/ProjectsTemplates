package com.karavel.batch.seo.linking.configuration.startup;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "conf.karavel.brand")
public class GlobalConfiguration {

    private Long lvSite;
    private Long langueId;
    private String siteName;
    private List<String> noeudsASupprimer;
    private List<String> noeudsInterditsDansBlocLinking;
    private Boolean filtreDestinationAvecOffre;
    private List<Integer> listeNiveauxNoeudsAPurger;
    private Long GeneratedIdPlage;
    private List<Integer> sejlistZoneNiveaux;
    private Integer fpZoneNiveau;
    private Integer sejlistNodeVlNiveau;

    public List<String> getNoeudsASupprimer() {
        return noeudsASupprimer;
    }

    public void setNoeudsASupprimer(List<String> noeudsASupprimer) {
        this.noeudsASupprimer = noeudsASupprimer;
    }

    public List<String> getNoeudsInterditsDansBlocLinking() {
        return noeudsInterditsDansBlocLinking;
    }

    public void setNoeudsInterditsDansBlocLinking(List<String> noeudsInterditsDansBlocLinking) {
        this.noeudsInterditsDansBlocLinking = noeudsInterditsDansBlocLinking;
    }

    public Boolean getFiltreDestinationAvecOffre() {
        return filtreDestinationAvecOffre;
    }

    public void setFiltreDestinationAvecOffre(Boolean filtreDestinationAvecOffre) {
        this.filtreDestinationAvecOffre = filtreDestinationAvecOffre;
    }

    public List<Integer> getListeNiveauxNoeudsAPurger() {
        return listeNiveauxNoeudsAPurger;
    }

    public void setListeNiveauxNoeudsAPurger(List<Integer> listeNiveauxNoeudsAPurger) {
        this.listeNiveauxNoeudsAPurger = listeNiveauxNoeudsAPurger;
    }

    public Long getGeneratedIdPlage() {
        return GeneratedIdPlage;
    }

    public void setGeneratedIdPlage(Long generatedIdPlage) {
        GeneratedIdPlage = generatedIdPlage;
    }

    public List<Integer> getSejlistZoneNiveaux() {
        return sejlistZoneNiveaux;
    }

    public void setSejlistZoneNiveaux(List<Integer> sejlistZoneNiveaux) {
        this.sejlistZoneNiveaux = sejlistZoneNiveaux;
    }

    public Integer getFpZoneNiveau() {
        return fpZoneNiveau;
    }

    public void setFpZoneNiveau(Integer fpZoneNiveau) {
        this.fpZoneNiveau = fpZoneNiveau;
    }

    public Integer getSejlistNodeVlNiveau() {
        return sejlistNodeVlNiveau;
    }

    public void setSejlistNodeVlNiveau(Integer sejlistNodeVlNiveau) {
        this.sejlistNodeVlNiveau = sejlistNodeVlNiveau;
    }

    public Long getLvSite() {
        return lvSite;
    }

    public void setLvSite(Long lvSite) {
        this.lvSite = lvSite;
    }

    public Long getLangueId() {
        return langueId;
    }

    public void setLangueId(Long langueId) {
        this.langueId = langueId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
