package com.almeida.commons.exception;

public class BussinessExcpetion extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 2094797697918804608L;

    private String errorCode;

    public BussinessExcpetion(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
