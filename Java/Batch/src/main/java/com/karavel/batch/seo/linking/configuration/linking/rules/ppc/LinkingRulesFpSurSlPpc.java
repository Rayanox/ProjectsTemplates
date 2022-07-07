package com.karavel.batch.seo.linking.configuration.linking.rules.ppc;

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
public class LinkingRulesFpSurSlPpc {

    @Bean
    LinkingRules linkingRulesFpSurSlZt2Ppc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(2)
                .forSite(SitesEnum.PARTIRPASCHER)
                .forGenerationType(GenerationType.TOP_HOTEL_POUR_SEJLIST)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(15)
                                .withLevel(4)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(2)
                                .withGroupName("0")
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(4)
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
                                                .withLevel(4)
                                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                                .withLevelZt(1)
                                                .build()
                                        ).build()
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(3)
                                .withLevel(4)
                                .withGroupName("1")
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
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(2)
                                .withLevel(4)
                                .withGroupName("2")
                                .withLinkZTType(LinkZTType.DIFFERENT_ZTn)
                                .withLevelZt(1)
                                .build()
                ).build();
    }

    @Bean
    LinkingRules linkingRulesFpSurSlZt3Ppc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(3)
                .forSite(SitesEnum.PARTIRPASCHER)
                .forGenerationType(GenerationType.TOP_HOTEL_POUR_SEJLIST)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(15)
                                .withLevel(4)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(3)
                                .withGroupName("0")
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(4)
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
                                                .withLevel(4)
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
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(3)
                                .withLevel(4)
                                .withGroupName("1")
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
                                .withRemoteRule(
                                        LinkingZTConfigurationRuleBuilder.init()
                                                .withLevel(4)
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
                                .withLevel(4)
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
                                .withGroupName("2")
                                .build()
                ).build();
    }

}
