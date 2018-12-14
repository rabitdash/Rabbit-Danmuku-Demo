package com.rabyte.rabitdash.util;


import java.awt.*;

public class GameObject{
    public boolean active = false;
    //»æÖÆµÄ´°Ìå
    public Graphics graphics;

    public GameObject() {
    }

    public GameObject(Graphics graphics) {
        this.graphics = graphics;
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
