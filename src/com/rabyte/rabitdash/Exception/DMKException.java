package com.rabyte.rabitdash.Exception;

import javax.swing.*;

public class DMKException extends Exception {
    DMKException() {
        super();
    }

    DMKException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        JOptionPane.showMessageDialog(null, this.getMessage());
    }
}
