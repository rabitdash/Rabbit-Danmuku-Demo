package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Player extends GameObject implements Drawable, Collidable {
    public static final int IMAGE_WIDTH = 50;
    public static final int IMAGE_HEIGHT = 50;
    public static final double slowV = 0.5;//不按shift速度倍率
    public static final double moveV = 1.5;
    private static BulletPool bulletPool;
    public Vec2 pos;
    public double collideSize = 5;//碰撞体积
    public int hitPoint = 5;
    Image image;

    public Player(@NotNull Graphics g) {
        super();
        graphics = g;
        this.pos = new Vec2();
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu2.jpg").getImage();
        bulletPool = BulletPool.getInstance(g);
    }

    @Override
    public boolean isCollide(Collidable object) {
        return false;
    }

    @Override
    public void collideEvent(Collidable object) {
        hitPoint--;

    }

    @Override
    public double getCollideSize() {
        return collideSize;
    }

    public Vec2 getPos() {
        return pos.add(new Vec2(IMAGE_WIDTH / 2.0, IMAGE_HEIGHT / 2.0));
    }

    public void setPos(Vec2 pos) {
        this.pos = pos.add(new Vec2(-IMAGE_WIDTH / 2.0, -IMAGE_HEIGHT / 2.0));
    }

    public void draw() {


        graphics.drawImage(image, (int) pos.x, (int) pos.y, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        if (hitPoint < 0) {

            //TODO
            Color c = graphics.getColor();
            graphics.setColor(Color.GREEN);
            graphics.drawOval((int) (getPos().x-30), (int) (getPos().y-30), 60, 60);
            graphics.setColor(c);
        }
    }

    public void control(GetKeys getKeys) {
        Vec2 moveDirection = new Vec2();
        if (getKeys.down) {
            moveDirection = moveDirection.add(new Vec2(0, moveV));
        }
        if (getKeys.up) {
            moveDirection = moveDirection.add(new Vec2(0, -moveV));
        }
        if (getKeys.left) {
            moveDirection = moveDirection.add(new Vec2(-moveV, 0));
        }
        if (getKeys.right) {
            moveDirection = moveDirection.add(new Vec2(moveV, 0));
        }
        if (getKeys.shift) {
            moveDirection = moveDirection.dot(slowV);
            Color c = graphics.getColor();
            graphics.setColor(Color.GREEN);
            graphics.fillOval((int) (this.getPos().x - 7.5), (int) (this.getPos().y - 7.5), 10, 10);
            graphics.setColor(c);
        }
        this.pos = this.pos.add(moveDirection);
    }

    //TODO
    public Vector<GameObject> shoot(Graphics g) {
        Vector<FixTraceBullet> fixTraceBullets = bulletPool.getBullets(3);
        int i = 0;
        for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
            fixTraceBullet.setPos(getPos());
            fixTraceBullet.traceFunc = new Trace.Linear(Math.toRadians(-(30 * i) - 60));
            fixTraceBullet.life = 100;
            fixTraceBullet.frame = 0;
            fixTraceBullet.v = 10;
            i++;
        }
        return new Vector<>(fixTraceBullets);
    }

}
