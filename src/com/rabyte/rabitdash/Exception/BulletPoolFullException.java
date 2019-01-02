package com.rabyte.rabitdash.Exception;

public class BulletPoolFullException extends DMKException{
    public BulletPoolFullException() {
        super("×Óµ¯³ØÂú");
    }

    public BulletPoolFullException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
