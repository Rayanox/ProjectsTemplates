package com.karavel.batch.seo.linking.core.steps.linking.writer;

import com.karavel.batch.seo.linking.core.JobData;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LinkingWriter")
public class LinkingWriter implements ItemWriter<ZoneNode> {

    @Autowired
    JobData jobData;

    @Override
    public void write(List<? extends ZoneNode> zoneNodes) throws Exception {
        //TODO a coder l'ajout en DB
        int i = 0;
        i++;
    }

    public void setJobData(JobData jobData) {
        this.jobData = jobData;
    }
}
