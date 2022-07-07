package com.karavel.batch.seo.linking.exceptions;

public class ProfileNotSetException extends Exception {

    private static final String MESSAGE = "The Spring profile MUST be set to indicates which brand process to. Example: -Dspring.profiles.active=pmvc";

    public ProfileNotSetException() {
        super(MESSAGE);
    }
}
