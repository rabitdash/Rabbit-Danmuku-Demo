package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.Collidable;

import java.awt.*;

public class VarTraceBullet extends Bullet implements Collidable, Drawable {
    public Vec2 v;

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

    @Override
    public Vec2 getPos() {
        return null;
    }

    public void setV(Vec2 v) {
        this.v = v;
    }

    @Override
    public void draw() {
        if (frame > life) {
            this.active = false;
        }
        //��frame��Ϊ��������������ʱ��Ļ
        //TODO Ȼ��������������
        else if (frame > 0) {
            Color c = graphics.getColor();
//            graphics.drawImage(image, (int) pos.x, (int) pos.y, imageWidth, imageHeight, null);
            graphics.setColor(Color.RED);
            graphics.fillOval((int) pos.x, (int) pos.y, imageWidth, imageHeight);
            pos = pos.add(v);
            //��ɫ�ָ�
            frame++;
            graphics.setColor(c);
//            System.out.println(pos.x + " " + pos.y);
        } else {
            frame++;
        }
    }
}
