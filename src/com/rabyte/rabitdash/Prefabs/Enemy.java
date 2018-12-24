package com.rabyte.rabitdash.Prefabs;


import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Vector;

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
        this.collideSize=12;
    }

    public Enemy(@NotNull Graphics g, Image image) {
        super(g, image);
    }

    @Override
    public void collideEvent(Collidable object) {
        this.active = false;
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
            frame++;
        } else {
            frame++;
        }

    }

    public Vector<GameObject> shoot(Vec2 pos)
    {
        if(this.frame < 0)
        {
            return new Vector<>();
        }
        Vector<GameObject> res = new Vector<>();
        //给了位置
        Vector<FixTraceBullet> fixTraceBullets = bulletPool.getBullets(3);
        int i = 0;

        if(pos != null)
        {
            //自机狙
            for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
                fixTraceBullet.setPos(this.getPos());
                fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).angle(pos.minus(this.getPos())));
                fixTraceBullet.life = 500;
                fixTraceBullet.frame -= 40*i;
                fixTraceBullet.v = 5;
                i++;
            }
        }
        else
        {
            for (FixTraceBullet fixTraceBullet: fixTraceBullets)
            {
                fixTraceBullet.setPos(this.getPos());
                fixTraceBullet.traceFunc = new Trace.Linear(Math.toRadians(90));
                fixTraceBullet.life = 1000;
                fixTraceBullet.frame -= 20*i;
                i++;
            }
        }

        res.addAll(fixTraceBullets);
        return res;
    }


}
