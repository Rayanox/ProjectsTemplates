package com.karavel.batch.seo.linking.core.steps.urlgeneration;


import com.karavel.batch.seo.linking.core.common.ZoneNode;
import java.util.concurrent.Future;

public class UrlGenerationWrapper {

    private ZoneNode node;
    private Future futureUrlResponseOut;

    public ZoneNode getNode() {
        return node;
    }

    public void setNode(ZoneNode node) {
        this.node = node;
    }

    public Future getFutureUrlResponseOut() {
        return futureUrlResponseOut;
    }

    public void setFutureUrlResponseOut(Future futureUrlResponseOut) {
        this.futureUrlResponseOut = futureUrlResponseOut;
    }
}
