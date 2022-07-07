package com.karavel.batch.seo.linking.configuration.linking.beans;


import com.karavel.batch.seo.linking.common.beans.LinkingZTConfigurationRule;
import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.common.GenerationType;

import java.util.ArrayList;
import java.util.List;

public class LinkingRules {

    private Integer applicableZoneLevel;
    private List<SitesEnum> applicableSites;
    private GenerationType generationType;

    public LinkingRules() {
        this.applicableSites = new ArrayList<>();
    }

    private List<LinkingZTConfigurationRule> rules;

    public List<LinkingZTConfigurationRule> getRules() {
        return rules;
    }

    public void setRules(List<LinkingZTConfigurationRule> rules) {
        this.rules = rules;
    }

    public Integer getApplicableZoneLevel() {
        return applicableZoneLevel;
    }

    public void setApplicableZoneLevel(Integer applicableZoneLevel) {
        this.applicableZoneLevel = applicableZoneLevel;
    }

    public List<SitesEnum> getApplicableSites() {
        return applicableSites;
    }

    public void setApplicableSites(List<SitesEnum> applicableSites) {
        this.applicableSites = applicableSites;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }
}
