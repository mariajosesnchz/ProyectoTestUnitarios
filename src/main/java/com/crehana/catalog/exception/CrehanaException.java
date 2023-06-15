package com.crehana.catalog.exception;

public class CrehanaException extends RuntimeException {

    private static final long serialVersionUID = -5274357034970222787L;

    public CrehanaException(String shortDescription) {
        super(shortDescription);
    }
}
