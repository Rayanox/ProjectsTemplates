package com.karavel.batch.seo.linking.core.steps.urlgeneration.reader;

import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.core.JobData;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.UrlGenerationWrapper;
import com.karavel.batch.seo.linking.core.steps.urlgeneration.service.UrlGenerationService;
import com.karavel.batch.seo.linking.utils.ConfigurationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

@Component("UrlGenerationReader")
public class UrlGenerationReader implements ItemReader<UrlGenerationWrapper> {

    private final static Logger LOGGER = LoggerFactory.getLogger(UrlGenerationReader.class);
    private static final UrlGenerationWrapper END = null;

    private JobData jobData;
    private boolean readerStarted = false;

    private List<ZoneNode> allNodesZone;
    private int dataLength;
    private int currentIndex = 0;

    private UrlGenerationService fpGenerationService;
    private UrlGenerationService slGenerationService;
    private ConfigurationUtils configurationUtils;

    @Override
    public UrlGenerationWrapper read(){
        if(!readerStarted) {
            initReader();
            readerStarted = true;
        }

        if(currentIndex >= dataLength -1) {
            return END;
        }

        UrlGenerationWrapper urlGenerationWrapper = new UrlGenerationWrapper();
        ZoneNode currentZone = allNodesZone.get(currentIndex++);

        Future futureResponse = null;
        if(isFpNode(currentZone.getZone())) {
            futureResponse = fpGenerationService.callWebService(currentZone);
        } else {
            futureResponse = slGenerationService.callWebService(currentZone);
        }

        urlGenerationWrapper.setFutureUrlResponseOut(futureResponse);
        urlGenerationWrapper.setNode(currentZone);
        return urlGenerationWrapper;
    }

    private boolean isFpNode(ZoneTouristiqueNode currentZone) {
        return configurationUtils.getFpNodeNiveau().equals(currentZone.getNiveau());
    }


    private void initReader() {
        allNodesZone = jobData.getBatchOut().getZoneNodes();
        dataLength = allNodesZone.size();
    }

    @Autowired
    public void setJobData(JobData jobData) {
        this.jobData = jobData;
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
