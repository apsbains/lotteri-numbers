package com.complexica.lotterinumbers.exceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {

        super("No data found");
    }
}
