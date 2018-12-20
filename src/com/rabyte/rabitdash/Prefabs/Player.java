package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.Collidable;
import com.rabyte.rabitdash.util.GameObject;
import com.rabyte.rabitdash.util.GetKeys;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject implements Drawable, Collidable {
    public static final int IMAGE_WIDTH = 50;
    public static final int IMAGE_HEIGHT = 50;
    public Vec2 pos;
    public double collideSize = 100;//碰撞体积
    public static final double slowV = 0.5;//不按shift速度倍率
    Image image;

    @Override
    public boolean isCollide(Collidable object) {
        return false;
    }

    @Override
    public void collideEvent(Collidable object) {

    }

    @Override
    public double getCollideSize() {
        return collideSize;
    }

    public Player(@NotNull Graphics g)
    {
        super();
        graphics = g;
        this.pos = new Vec2();
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu2.jpg").getImage();
    }
    public Vec2 getPos() {
        return pos.add(new Vec2(IMAGE_WIDTH / 2.0, IMAGE_HEIGHT / 2.0));
    }

    public void setPos(Vec2 pos) {
        this.pos = pos.add(new Vec2(-IMAGE_WIDTH / 2.0, -IMAGE_HEIGHT / 2.0));
    }

    public void draw()
    {
        graphics.drawImage(image, (int) pos.x, (int) pos.y, IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }

    public void control(GetKeys getKeys)
    {
        Vec2 moveDirection = new Vec2();
        if(getKeys.down)
        {
            moveDirection = moveDirection.add(new Vec2(0,5));
        }
        if (getKeys.up)
        {
            moveDirection = moveDirection.add(new Vec2(0,-5));
        }
        if(getKeys.left)
        {
            moveDirection = moveDirection.add(new Vec2(-5,0));
        }
        if(getKeys.right)
        {
            moveDirection = moveDirection.add(new Vec2(5,0));
        }
        if(getKeys.shift)
        {
            moveDirection = moveDirection.dot(slowV);
        }
        this.pos = this.pos.add(moveDirection);
    }

}
