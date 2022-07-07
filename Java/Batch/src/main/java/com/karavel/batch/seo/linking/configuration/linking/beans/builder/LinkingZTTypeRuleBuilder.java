package com.karavel.batch.seo.linking.configuration.linking.beans.builder;


import com.karavel.batch.seo.linking.common.beans.LinkZTType;
import com.karavel.batch.seo.linking.common.beans.LinkingZTTypeRule;

public class LinkingZTTypeRuleBuilder {

    private LinkingZTTypeRule linkingZTTypeRule;

    public static LinkingZTTypeRuleBuilder init() {
        LinkingZTTypeRuleBuilder builder = new LinkingZTTypeRuleBuilder();
        builder.setLinkingZTTypeRule(new LinkingZTTypeRule());
        return builder;
    }

    public LinkingZTTypeRuleBuilder withLevelZt(Integer levelZt) {
        this.linkingZTTypeRule.setLevelZT(levelZt);
        return this;
    }

    public LinkingZTTypeRuleBuilder withLinkZTType(LinkZTType linkZTType) {
        this.linkingZTTypeRule.setLinkZTType(linkZTType);
        return this;
    }

    public LinkingZTTypeRuleBuilder withTopGeo(boolean topGeo) {
        this.linkingZTTypeRule.setTopGeo(topGeo);
        return this;
    }

    public LinkingZTTypeRule build() {
        return this.linkingZTTypeRule;
    }

    private void setLinkingZTTypeRule(LinkingZTTypeRule linkingZTTypeRule) {
        this.linkingZTTypeRule = linkingZTTypeRule;
    }
}
