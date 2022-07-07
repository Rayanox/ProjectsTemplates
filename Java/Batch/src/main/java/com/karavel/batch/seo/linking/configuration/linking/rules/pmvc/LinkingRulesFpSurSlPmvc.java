package com.karavel.batch.seo.linking.configuration.linking.rules.pmvc;

import com.karavel.batch.seo.linking.common.beans.LinkZTType;
import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.configuration.linking.beans.LinkingRules;
import com.karavel.batch.seo.linking.configuration.linking.beans.builder.LinkingRuleBuilder;
import com.karavel.batch.seo.linking.configuration.linking.beans.builder.LinkingZTConfigurationRuleBuilder;
import com.karavel.batch.seo.linking.configuration.linking.beans.builder.LinkingZTTypeRuleBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkingRulesFpSurSlPmvc {

    @Bean
    LinkingRules linkingRulesFpSurSlZt2Pmvc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(2)
                .forSite(SitesEnum.PROMOVACANCES)
                .forSite(SitesEnum.PROMOVACANCES_BELGIQUE)
                .forGenerationType(GenerationType.TOP_HOTEL_POUR_SEJLIST)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(15)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(2)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                        .withLevelZt(1)
                                        .build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(3)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                                .withLevelZt(2)
                                                .build()
                                        )
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(1)
                                                .build()
                                        ).build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(2)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                        .withLevelZt(1)
                                        .build()
                                ).build()
                ).build();
    }

    @Bean
    LinkingRules linkingRulesFpSurSlZt3Pmvc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(3)
                .forSite(SitesEnum.PROMOVACANCES)
                .forSite(SitesEnum.PROMOVACANCES_BELGIQUE)
                .forGenerationType(GenerationType.TOP_HOTEL_POUR_SEJLIST)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(15)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(3)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                        .withLevelZt(2)
                                        .build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(3)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                                .withLevelZt(2)
                                                .build()
                                        )
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(1)
                                                .build()
                                        ).build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(2)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                        .withLevelZt(1)
                                        .build()
                                ).build()
                ).build();
    }

    @Bean
    LinkingRules linkingRulesFpSurSlZt4Pmvc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(4)
                .forSite(SitesEnum.PROMOVACANCES)
                .forSite(SitesEnum.PROMOVACANCES_BELGIQUE)
                .forGenerationType(GenerationType.TOP_HOTEL_POUR_SEJLIST)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(15)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(4)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                        .withLevelZt(3)
                                        .build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(3)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                                .withLevelZt(2)
                                                .build()
                                        )
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(1)
                                                .build()
                                        ).build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(2)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                        .withLevelZt(1)
                                        .build()
                                ).build()
                ).build();
    }

    @Bean
    LinkingRules linkingRulesFpSurSlZt5Pmvc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(5)
                .forSite(SitesEnum.PROMOVACANCES)
                .forSite(SitesEnum.PROMOVACANCES_BELGIQUE)
                .forGenerationType(GenerationType.TOP_HOTEL_POUR_SEJLIST)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(15)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                        .withLevelZt(2)
                                        .build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(3)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                                .withLevelZt(2)
                                                .build()
                                        )
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(1)
                                                .build()
                                        ).build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(2)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                        .withLevelZt(1)
                                        .build()
                                ).build()
                ).build();
    }

    @Bean
    LinkingRules linkingRulesFpSurSlZt6Pmvc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(6)
                .forSite(SitesEnum.PROMOVACANCES)
                .forSite(SitesEnum.PROMOVACANCES_BELGIQUE)
                .forGenerationType(GenerationType.TOP_HOTEL_POUR_SEJLIST)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(15)
                                .withLevel(7)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(5)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(7)
                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                        .withLevelZt(4)
                                        .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                                .withLevel(7)
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(3)
                                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                                        .withLevel(7)
                                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                                        .withLevelZt(1)
                                                        .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                                                .withLevel(7)
                                                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                                                .withLevelZt(1)
                                                                .build()
                                                        ).build()
                                                ).build()
                                        ).build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(3)
                                .withLevel(6)
                                .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                        .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                        .withLevelZt(3)
                                        .build()
                                )
                                .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                        .withLevelZt(2)
                                        .build()
                                )
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(6)
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                                .withLevelZt(2)
                                                .build()
                                        )
                                        .withLinkingZTTypeRule(LinkingZTTypeRuleBuilder.init()
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(1)
                                                .build()
                                        )
                                        .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                                .withLevel(6)
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(1)
                                                .build()
                                        ).build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(2)
                                .withLevel(2)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(1)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(2)
                                        .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                        .withLevelZt(1)
                                        .build()
                                ).build()
                ).build();
    }

}
