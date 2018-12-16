package com.rabyte.rabitdash.util;


import java.awt.*;

public class GameObject {
    //»æÖÆµÄ´°Ìå
    public static Graphics graphics;
    public boolean active = false;

    public GameObject() {
    }

    public GameObject(Graphics g) {
        graphics = g;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
