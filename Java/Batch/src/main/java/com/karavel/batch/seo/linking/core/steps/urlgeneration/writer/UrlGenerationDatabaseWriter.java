package com.karavel.batch.seo.linking.core.steps.urlgeneration.writer;

import com.karavel.batch.seo.linking.core.JobData;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UrlGenerationDatabaseWriter")
public class UrlGenerationDatabaseWriter implements ItemWriter<ZoneNode> {

    @Autowired
    JobData jobData;

    @Override
    public void write(List<? extends ZoneNode> zoneNodes) throws Exception {

        //TODO Coder l'envoi d'insertion en DB.
        zoneNodes.forEach(zoneNode -> {
            int i = 0;
            i++;
        });
    }

    public void setJobData(JobData jobData) {
        this.jobData = jobData;
    }
}
