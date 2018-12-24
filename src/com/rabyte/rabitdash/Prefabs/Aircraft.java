package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.Collidable;
import com.rabyte.rabitdash.util.GetKeys;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
//TODO
public class Aircraft extends GameObject implements Drawable, Collidable {
    public int width = 50;
    public int height = 50;
    public double slowV = 0.5;//不按shift速度倍率
    public double moveV = 1.5;//一般移动速度
    public Vec2 pos;
    public double collideSize = 10;//碰撞体积
    Image image;

    public Aircraft() {
        super();
        this.pos = new Vec2();
    }

    public Aircraft(@NotNull Graphics g) {
        super();
        graphics = g;
        this.pos = new Vec2();
    }
    public Aircraft(@NotNull Graphics g, Image image) {
        super();
        graphics = g;
        this.pos = new Vec2();
        this.image = image;
        this.height =image.getHeight(null);
        this.width =image.getWidth(null);
    }

    @Override
    public boolean isCollide(Collidable object) {
        if (!this.active)
            return false;
        return this.getPos().minus(object.getPos()).len()
                < Math.abs(this.getCollideSize() + object.getCollideSize());
    }

    @Override
    public void collideEvent(Collidable object) {

    }

    @Override
    public double getCollideSize() {
        return collideSize;
    }

    public Vec2 getPos() {
        return pos.add(new Vec2(width / 2.0, height / 2.0));
    }

    public void setPos(Vec2 pos) {
        this.pos = pos.add(new Vec2(-width / 2.0, -height / 2.0));
    }

    public void draw() {
        graphics.drawImage(image, (int) pos.x, (int) pos.y, width, height, null);
    }

    public void control(GetKeys getKeys) {
    }
}


