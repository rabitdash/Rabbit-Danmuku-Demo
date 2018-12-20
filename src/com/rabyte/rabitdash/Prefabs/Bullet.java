package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.Collidable;
import com.rabyte.rabitdash.util.GameObject;

public class Bullet extends GameObject implements Drawable, Collidable {
    public static int imageWidth = 10;
    public static int imageHeight = 10;
    public Vec2 pos;
    public double collideSize = 114;//��ײ���
    public int life = 1000;//�������������֡������=0��active=false�������ӵ������
    public int frame = 0;//�Ѵ���֡��

    //TODO ����ͼ��Ϊ���˶��������
    @Override
    public void draw() {

    }

    @Override
    public boolean isCollide(Collidable object) {
        if (!this.active)
            return false;
//        System.out.println(this.getPos().minus(object.getPos()).len());
        return this.getPos().minus(object.getPos()).len()
                < Math.abs(this.getCollideSize() + object.getCollideSize());
    }

    @Override
    public void collideEvent(Collidable object) {

    }

    @Override
    public double getCollideSize() {
        return 0;
    }

    public Vec2 getPos() {
        return pos.add(new Vec2(imageWidth / 2.0, imageHeight / 2.0));
    }

    public void setPos(Vec2 pos) {
        this.pos = pos.add(new Vec2(-imageWidth / 2.0, -imageHeight / 2.0));
    }

    //image = new ImageIcon(getClass().getResource("/resources/images/test.png"));
}
