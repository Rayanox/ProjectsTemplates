package com.karavel.batch.seo.linking.configuration.linking.beans.builder;


import com.karavel.batch.seo.linking.common.beans.LinkZTType;
import com.karavel.batch.seo.linking.common.beans.LinkingZTConfigurationRule;
import com.karavel.batch.seo.linking.common.beans.LinkingZTTypeRule;
import com.karavel.batch.seo.linking.common.beans.Thematique;

import java.util.ArrayList;

public class LinkingZTConfigurationRuleBuilder {

    private LinkingZTConfigurationRule ruleBuilt;

    public static LinkingZTConfigurationRuleBuilder init() {
        LinkingZTConfigurationRuleBuilder builder = new LinkingZTConfigurationRuleBuilder();
        builder.setRuleBuilt(new LinkingZTConfigurationRule());
        return builder;
    }

    public LinkingZTConfigurationRuleBuilder withNumber(Integer number) {
        this.ruleBuilt.setNumber(number);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withLevel(Integer level) {
        this.ruleBuilt.setLevel(level);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withLevelZt(Integer levelZt) {
        this.ruleBuilt.setLevelZT(levelZt);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withTopPrioLocation(Integer topPrioLocation) {
        this.ruleBuilt.setTopPrioLocation(topPrioLocation);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withTopPrioCamping(Integer topPrioCamping) {
        this.ruleBuilt.setTopPrioCamping(topPrioCamping);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withThematique(Thematique thematique) {
        this.ruleBuilt.setThematique(thematique);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withRemoteRule(LinkingZTConfigurationRule rule) {
        this.ruleBuilt.setRemote(rule);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withLinkingZTTypeRule(LinkingZTTypeRule linkingZTTypeRule) {
        if(this.ruleBuilt.getLinkingZTTypeRules() == null)
            this.ruleBuilt.setLinkingZTTypeRules(new ArrayList<>());

        this.ruleBuilt.getLinkingZTTypeRules().add(linkingZTTypeRule);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withGroupName(String groupName) {
        this.ruleBuilt.setGroupName(groupName);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withLinkZTType(LinkZTType linkZTType) {
        this.ruleBuilt.setLinkZTType(linkZTType);
        return this;
    }

    public LinkingZTConfigurationRuleBuilder withTopGeo(boolean topGeo) {
        this.ruleBuilt.setTopGeo(topGeo);
        return this;
    }

    public LinkingZTConfigurationRule build() {
        return this.ruleBuilt;
    }


    private void setRuleBuilt(LinkingZTConfigurationRule ruleBuilt) {
        this.ruleBuilt = ruleBuilt;
    }
}
