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

//TODO Les regles semblent incorrectes car on link avec des SL ... voir avec le SEO pour obtenir de nouvelles regles si un jour on a un peu de temps libre mais ça restera surement buggué ad vitame etername

@Configuration("LinkingRulesFpSurFpPmvcConfiguration")
public class LinkingRulesFpSurFpPmvc {


    @Bean
    LinkingRules linkingRulesFpSurFpPmvc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(7)
                .forSite(SitesEnum.PROMOVACANCES)
                .forSite(SitesEnum.PROMOVACANCES_BELGIQUE)
                .forGenerationType(GenerationType.FP)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(9)
                                .withLevel(5)
                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                .withLevelZt(4)
                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                        .withLevel(4)
                                        .withLinkZTType(LinkZTType.SAME_ZTn)
                                        .withLevelZt(3)
                                        .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                                .withLevel(3)
                                                .withLinkZTType(LinkZTType.SAME_ZTn)
                                                .withLevelZt(2)
                                                .withRemoteRule(LinkingZTConfigurationRuleBuilder.init()
                                                        .withLevel(3)
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
                                ).build()
                ).addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(6)
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
                                        .withLevel(3)
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
                                .withNumber(5)
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
