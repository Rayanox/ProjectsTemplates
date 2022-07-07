package com.karavel.batch.seo.linking.core.steps.linking.reader;

import com.karavel.batch.seo.linking.core.JobData;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinkingReader implements ItemReader<ZoneNode> {

    private boolean readerStarted = false;
    private JobData jobData;

    private List<ZoneNode> allNodesZone;
    private int dataLength;
    private int currentIndex = 0;
    private static final ZoneNode END = null;

    @Override
    public ZoneNode read() {
        if(!readerStarted) {
            initReader();
            readerStarted = true;
        }

        if(currentIndex >= dataLength -1) {
            return END;
        }

        return allNodesZone.get(currentIndex++);
    }

    private void initReader() {
        this.allNodesZone = jobData.getBatchOut().getZoneNodes();
        this.dataLength = allNodesZone.size();
    }

    @Autowired
    public void setJobData(JobData jobData) {
        this.jobData = jobData;
    }
}
