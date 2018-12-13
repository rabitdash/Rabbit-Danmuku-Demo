package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.util.GameObject;
import com.rabyte.rabitdash.Math.Vec2;

import java.awt.*;

public class Bullet extends GameObject implements Runnable {
    Vec2 pos;
    Vec2 v;
    Image image;
    double lifeTime;//存活寿命，寿命到则active=false存入子弹对象池


    public Bullet()
    {
        pos = new Vec2();
        v = new Vec2();
        active = false;
    }

    public Bullet(Vec2 position, Vec2 velocity)
    {
        this.pos = position;
        this.v = velocity;
        active = false;
    }


    @Override
    public void run() {
        draw();
    }

    Object draw()
    {
        return null;
    }
}
