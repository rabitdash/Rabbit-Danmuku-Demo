package com.rabyte.rabitdash.util;


import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;

import java.awt.*;

public class GameObject implements Drawable, Collidable {
    //»æÖÆµÄ´°Ìå
    public static Graphics graphics;
    public boolean active = false;

    public GameObject() {
    }

    public GameObject(Graphics g) {
        graphics = g;
    }

    @Override
    public void draw() {

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

    @Override
    public boolean isCollide(Collidable object) {
        return false;
    }

    @Override
    public void collideEvent(Collidable object) {

    }

    @Override
    public double getCollideSize() {
        return 0;
    }

    public Vec2 getPos() {
        return null;
    }

    public void setPos(Vec2 pos) {
    }
}
