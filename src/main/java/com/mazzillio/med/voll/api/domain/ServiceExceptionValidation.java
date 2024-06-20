package com.mazzillio.med.voll.api.domain;

public class ServiceExceptionValidation extends RuntimeException {
    public ServiceExceptionValidation(String message) {
        super(message);
    }
}
