package com.karavel.batch.seo.linking.exceptions;

public class SiteIdPropertyNotSetException extends RuntimeException {

    private static final String MESSAGE = "The 'conf.karavel.brand.lvSite' property must be set in the property file";

    public SiteIdPropertyNotSetException() {
        super(MESSAGE);
    }
}
