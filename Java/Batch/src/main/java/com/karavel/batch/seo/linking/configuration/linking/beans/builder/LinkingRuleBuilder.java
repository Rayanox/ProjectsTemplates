package com.karavel.batch.seo.linking.configuration.linking.beans.builder;


import com.karavel.batch.seo.linking.common.beans.LinkingZTConfigurationRule;
import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.configuration.linking.beans.LinkingRules;

import java.util.ArrayList;
import java.util.List;

public class LinkingRuleBuilder {

    private List<LinkingZTConfigurationRule> rules = new ArrayList<>();
    private LinkingRules linkingRulesBuilt;

    public static LinkingRuleBuilder init(){
        LinkingRuleBuilder builder = new LinkingRuleBuilder();
        builder.setLinkingRulesBuilt(new LinkingRules());
        return builder;
    }


    public LinkingRuleBuilder forZoneLevel(Integer zoneLevel) {
        this.linkingRulesBuilt.setApplicableZoneLevel(zoneLevel);
        return this;
    }

    public LinkingRuleBuilder forGenerationType(GenerationType generationType) {
        this.linkingRulesBuilt.setGenerationType(generationType);
        return this;
    }

    public LinkingRuleBuilder forSite(SitesEnum site) {
        this.linkingRulesBuilt.getApplicableSites().add(site);
        return this;
    }

    public LinkingRuleBuilder addRule(LinkingZTConfigurationRule rule) {
        this.rules.add(rule);
        return this;
    }


    public LinkingRules build() {
        this.linkingRulesBuilt.setRules(rules);
        return this.linkingRulesBuilt;
    }


    private void setRules(List<LinkingZTConfigurationRule> rules) {
        this.rules = rules;
    }

    private void setLinkingRulesBuilt(LinkingRules linkingRulesBuilt) {
        this.linkingRulesBuilt = linkingRulesBuilt;
    }
}
