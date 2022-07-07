package com.karavel.batch.seo.linking.core;

import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.configuration.startup.BatchInputs;
import com.karavel.batch.seo.linking.dto.BatchOuts;
import org.springframework.stereotype.Component;

@Component
//@JobScope
public class JobData {

    private BatchInputs batchInputs;
    private BatchOuts batchOut;

    private GenerationType generationType;



    public BatchInputs getBatchInputs() {
        return batchInputs;
    }

    public void setBatchInputs(BatchInputs batchInputs) {
        this.batchInputs = batchInputs;
    }

    public BatchOuts getBatchOut() {
        return batchOut;
    }

    public void setBatchOut(BatchOuts batchOut) {
        this.batchOut = batchOut;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }
}
