package com.rabyte.rabitdash.Prefabs;



import com.rabyte.rabitdash.components.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.Collidable;

import java.awt.*;

public class GameObject implements Collidable, Drawable {
    //»æÖÆµÄ´°Ìå
    public static Graphics graphics;
    public boolean active = true;

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

    @Override
    public void draw() {

    }
}
