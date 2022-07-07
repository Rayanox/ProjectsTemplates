package com.karavel.batch.seo.linking.core.steps.urlgeneration.service;


import com.karavel.batch.seo.linking.core.common.ZoneNode;

import java.util.concurrent.Future;

public interface UrlGenerationService<I, O> {

    Future<O> callWebService(ZoneNode node);

    String getResponseUrl(Future<O> futureResponse);
}
