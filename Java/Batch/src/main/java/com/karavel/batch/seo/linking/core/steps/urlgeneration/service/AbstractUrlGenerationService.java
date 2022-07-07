package com.karavel.batch.seo.linking.core.steps.urlgeneration.service;

import com.karavel.batch.seo.linking.configuration.startup.GlobalConfiguration;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

public abstract class AbstractUrlGenerationService<I, O> implements UrlGenerationService<I, O> {

    protected final Logger logger;

    private static  final Long TIMEOUT = 10L;
    protected GlobalConfiguration globalConfiguration;

    protected AbstractUrlGenerationService(Class classImpl) {
        this.logger = LoggerFactory.getLogger(classImpl);
    }

    @Override
    public Future<O> callWebService(ZoneNode node) {
        I in = createInputService(node);
        return (Future<O>) Executors.newSingleThreadExecutor().submit(() -> serviceCall(in));
    }

    @Override
    public String getResponseUrl(Future<O> futureResponse) {
        try {
            O response = futureResponse.get(TIMEOUT, TimeUnit.SECONDS);
            return mapUrlFromResponseData(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract O serviceCall(I in) throws Exception;

    protected abstract String mapUrlFromResponseData(O responseOut);
    protected abstract I createInputService(ZoneNode node);

    @Autowired
    public void setGlobalConfiguration(GlobalConfiguration globalConfiguration) {
        this.globalConfiguration = globalConfiguration;
    }
}
