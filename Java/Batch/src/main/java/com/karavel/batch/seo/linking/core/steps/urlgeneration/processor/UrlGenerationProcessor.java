package com.karavel.batch.seo.linking.core.steps.urlgeneration.processor;

import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.UrlGenerationWrapper;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.UrlGenerationService;
import com.karavel.batch.seo.linking.utils.ConfigurationUtils;
import com.karavel.batch.seo.linking.utils.ZoneTouristiqueTreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.Future;

@Component("UrlGenerationProcessor")
public class UrlGenerationProcessor<T> implements ItemProcessor<UrlGenerationWrapper, ZoneNode> {

    private ConfigurationUtils configurationUtils;

    private UrlGenerationService fpGenerationService;
    private UrlGenerationService slGenerationService;

    @Override
    public ZoneNode process(UrlGenerationWrapper urlGenerationWrapper) {

        Future futureResponse = urlGenerationWrapper.getFutureUrlResponseOut();
        ZoneNode currentNode = urlGenerationWrapper.getNode();

        String uri;
        if(isFpNode(currentNode)) {
            uri = fpGenerationService.getResponseUrl(futureResponse);
        }else {
            uri = slGenerationService.getResponseUrl(futureResponse);
        }

        setUri(currentNode, uri);

        return currentNode;
    }

    private boolean isFpNode(ZoneNode currentNode) {
        return configurationUtils.getFpNodeNiveau().equals(currentNode.getZone().getNiveau());
    }

    private void setUri(ZoneNode node, String uri) {
        if(uri == null || uri.equals("/hotel/vp.html"))
            return;

        if(node.getZone().getNiveau() != ZoneTouristiqueTreeUtils.LEVEL_ACCOMODATION) {
            uri = uri.replaceAll("#.", "").replaceAll("\\?.*", "");
            node.getZone().getUriData().addUriForTheme(uri, node.getTheme());
        } else
            setUriForFpNode(node.getZone(), uri);
    }

    /**
     * Add same URI for all themes
     * @param node
     * @param uri
     */
    private void setUriForFpNode(ZoneTouristiqueNode node, String uri) {
        node.getLinkingFPList().getAllThemesOfLinking().forEach(theme -> {
            node.getUriData().addUriForTheme(uri, theme);
        });
    }


    @Autowired
    public void setConfigurationUtils(ConfigurationUtils configurationUtils) {
        this.configurationUtils = configurationUtils;
    }

    @Autowired
    public void setFpGenerationService(UrlGenerationService fpGenerationService) {
        this.fpGenerationService = fpGenerationService;
    }

    @Autowired
    public void setSlGenerationService(UrlGenerationService slGenerationService) {
        this.slGenerationService = slGenerationService;
    }
}
