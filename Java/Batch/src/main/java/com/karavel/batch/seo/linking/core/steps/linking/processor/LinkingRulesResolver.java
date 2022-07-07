package com.karavel.batch.seo.linking.core.steps.linking.processor;

import com.karavel.batch.seo.linking.configuration.linking.beans.LinkingRules;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.configuration.startup.BatchInputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LinkingRulesResolver {

    private final static Logger LOGGER = LoggerFactory.getLogger(LinkingRulesResolver.class);

    private final Map<Integer, LinkingRules> linkingRulesByLevel;

    public LinkingRulesResolver(BatchInputs batchInputs, List<LinkingRules> rules) {
        this.linkingRulesByLevel = rules.stream()
                .filter(this::isValidConf)
                .filter(rule -> matchWithStartupParams(rule, batchInputs))
                .collect(Collectors.toMap(LinkingRules::getApplicableZoneLevel, Function.identity()));
    }

    public Optional<LinkingRules> getLinkingRulesForNode(ZoneNode node) {
        return Optional.ofNullable(this.linkingRulesByLevel.get(node.getZone().getNiveau()));
    }

    private boolean isValidConf(LinkingRules rules) {
        if(Objects.isNull(rules.getApplicableZoneLevel())) {
            LOGGER.warn("A rule is not fully configured. Property 'applicableZoneLevel' is null");
            return false;
        }
        if(CollectionUtils.isEmpty(rules.getApplicableSites())) {
            LOGGER.warn("A rule is not fully configured. Property 'applicableSites' is null or empty");
            return false;
        }
        if(Objects.isNull(rules.getGenerationType())) {
            LOGGER.warn("A rule is not fully configured. Property 'generationType' is null");
            return false;
        }
        return true;
    }

    private boolean matchWithStartupParams(LinkingRules rules, BatchInputs startupParams) {
        return rules.getGenerationType().equals(startupParams.getGenerationType())
                && rules.getApplicableSites().contains(startupParams.getSite());
    }
}
