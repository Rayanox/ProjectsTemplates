package com.karavel.batch.seo.linking.exceptions;


import com.karavel.batch.seo.linking.common.GenerationType;

public class GenerationTypeNotProvidedException extends RuntimeException {

    private static final String MESSAGE = "The generation type must be set in arguments program using -DgenerationType=value with one of those values: " + GenerationType.toStringCodeValues();

    public GenerationTypeNotProvidedException() {
        super(MESSAGE);
    }

    public GenerationTypeNotProvidedException(String message) {
        super(message);
    }
}
