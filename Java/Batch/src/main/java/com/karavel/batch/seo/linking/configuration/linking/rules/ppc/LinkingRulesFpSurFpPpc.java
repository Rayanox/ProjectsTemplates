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

@Configuration("LinkingRulesFpSurFpPpcConfiguration")
public class LinkingRulesFpSurFpPpc {

    //TODO Demander les regles au SEO car les précédentes règles (legacy) sont tres douteuses (le linking FP sur FP fournit des liens de SL et non FP...).

    @Bean
    LinkingRules linkingRulesFpSurFpPpc() {
        return LinkingRuleBuilder.init()
                .forZoneLevel(7)
                .forSite(SitesEnum.PARTIRPASCHER)
                .forGenerationType(GenerationType.FP)
                .addRule(
                        LinkingZTConfigurationRuleBuilder.init()
                                .withNumber(10)
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

}
