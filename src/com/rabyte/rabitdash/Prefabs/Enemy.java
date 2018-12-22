package com.rabyte.rabitdash.Prefabs;


import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.BulletPool;
import com.rabyte.rabitdash.util.Collidable;
import com.rabyte.rabitdash.util.ITraceFunc;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Enemy extends Aircraft {
    public ITraceFunc traceFunc;
    int width = 20;
    int height = 20;
    public int frame=0;
    public int life=500;
    //TODO
    public static BulletPool bulletPool;
    public Enemy() {
    }

    public Enemy(@NotNull Graphics g) {
        super(g);
        active=true;
        bulletPool=BulletPool.getInstance(g);
    }

    public Enemy(@NotNull Graphics g, Image image) {
        super(g, image);
    }

    @Override
    public boolean isCollide(Collidable object) {
        return super.isCollide(object);
    }

    @Override
    public void collideEvent(Collidable object) {
        super.collideEvent(object);
    }

    @Override
    public double getCollideSize() {
        return super.getCollideSize();
    }

    @Override
    public Vec2 getPos() {
        return super.getPos();
    }

    @Override
    public void setPos(Vec2 pos) {
        super.setPos(pos);
    }

    @Override
    public void draw() {
        if (frame > life) {
            this.active = false;
        }
        //将frame设为负数可以制作延时弹幕
        //TODO 然而方法并不完美
        else if (frame > 0) {
            //颜色入栈
            Color c = graphics.getColor();
            graphics.setColor(Color.cyan);
            pos = pos.add(traceFunc.getTrace(frame));
            graphics.fillRect((int) pos.x, (int) pos.y, width, height);
            //颜色恢复
            graphics.setColor(c);
        } else {
            frame++;
        }

    }


}
