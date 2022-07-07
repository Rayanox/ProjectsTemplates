package com.karavel.batch.seo.linking.configuration.linking.rules.ppc;

import com.karavel.batch.seo.linking.common.beans.LinkZTType;
import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.configuration.linking.beans.LinkingRules;
import com.karavel.batch.seo.linking.configuration.linking.beans.builder.LinkingRuleBuilder;
import com.karavel.batch.seo.linking.configuration.linking.beans.builder.LinkingZTConfigurationRuleBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkingRulesSlPpc {

    //TODO Demander les regles au SEO car les précédentes règles (legacy) n'etaient plus fonctionnelles quand j'ai récupéré le projet.

    @Bean
    LinkingRules linkingRulesSlZt2Ppc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(2)
                .forSite(SitesEnum.PARTIRPASCHER)
                .forGenerationType(GenerationType.SL)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(7)
                                .withLevel(2)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(1)
                                .withGroupName("0")
                                .build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(4)
                                .withLevel(3)
                                .withGroupName("1")
                                .build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(4)
                                .withLevel(2)
                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                .withLevelZt(1)
                                .withGroupName("2")
                                .build()
                ).build();
    }

    @Bean
    LinkingRules linkingRulesSlZt3Ppc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(3)
                .forSite(SitesEnum.PARTIRPASCHER)
                .forGenerationType(GenerationType.SL)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(7)
                                .withLevel(2)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(1)
                                .withGroupName("0")
                                .build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(4)
                                .withLevel(3)
                                .withGroupName("1")
                                .build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(4)
                                .withLevel(2)
                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                .withLevelZt(1)
                                .withGroupName("2")
                                .build()
                ).build();
    }

    @Bean
    LinkingRules linkingRulesSlZt4Ppc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(4)
                .forSite(SitesEnum.PARTIRPASCHER)
                .forGenerationType(GenerationType.SL)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(7)
                                .withLevel(2)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(1)
                                .withGroupName("0")
                                .build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(4)
                                .withLevel(3)
                                .withGroupName("1")
                                .build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(4)
                                .withLevel(2)
                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                .withLevelZt(1)
                                .withGroupName("2")
                                .build()
                ).build();
    }

}
