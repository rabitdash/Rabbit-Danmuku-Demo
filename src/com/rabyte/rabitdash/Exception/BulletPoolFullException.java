package com.rabyte.rabitdash.Exception;

public class BulletPoolFullException extends DMKException{
    public BulletPoolFullException() {
        super("�ӵ�����");
    }

    public BulletPoolFullException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
