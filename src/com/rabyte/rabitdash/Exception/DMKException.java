package com.rabyte.rabitdash.Exception;

public class DMKException extends Exception {
    DMKException() {
        super();
    }

    DMKException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
