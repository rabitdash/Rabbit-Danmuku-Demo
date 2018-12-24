package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.BulletGenerator;
import com.rabyte.rabitdash.util.Collidable;
import com.rabyte.rabitdash.util.Trace;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Vector;

public class Boss extends Enemy {
    public int hitpoint = 200;

    public Boss(@NotNull Graphics g) {
        super(g);
        width = 40;
        height = 40;
        collideSize = 10;
    }

    @Override
    public Vector<GameObject> shoot(Vec2 pos) {
        int i = 0;
        Vector<GameObject> res = new Vector<>();
        Vector<FixTraceBullet> fixTraceBullets = new Vector<>();
        if (this.frame < 0) {
            return res;
        }
        //����λ��`
        if (frame < life / 3) {
            System.out.println("para 1");
            i = 0;
            fixTraceBullets = bulletPool.getBullets(30);
            for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
                fixTraceBullet.setPos(this.getPos());
                //Բ�ε�Ļ
                fixTraceBullet.traceFunc = new Trace.Linear(Math.toRadians(360.0/fixTraceBullets.size()*i));
                fixTraceBullet.v = 3;
                i++;
            }
        } else if (frame < life / 5*2) {
            System.out.println("para2");
            fixTraceBullets = bulletPool.getBullets(5);
            i = 0;

            for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
                fixTraceBullet.setPos(this.getPos());
                //�Ի���
                fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).
                        angle(pos.minus(this.getPos()))
                        + Math.toRadians(10 * (i - 1)));
                fixTraceBullet.v = 5;
                i++;
            }
            this.traceFunc = new Trace.Linear(new Vec2(1, 0).
                    angle(pos.minus(this.getPos())));

        } else {

            System.out.println("para3");
            fixTraceBullets = bulletPool.getBullets(20);
            i = 5;


            for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
                fixTraceBullet.setPos(this.getPos());
                //�Ի���
                fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).
                        angle(pos.minus(this.getPos()))
                        + Math.toRadians(10 * (i - 15)));
                fixTraceBullet.v = 0.3 * i;
                i++;
            }
            this.traceFunc = new Trace.Halt();
        }
//        res.addAll(BulletGenerator.RoundDanmuku(fixTraceBullets, this.getPos(), 100));
//        System.out.println("asdf:" + res.isEmpty());
        res.addAll(fixTraceBullets);
        return res;
    }

    @Override
    public void collideEvent(Collidable object) {
        this.hitpoint--;
        if (hitpoint < 0) {
            this.active = false;
        }
    }

    @Override
    public void draw() {
        //System.out.println("boss draw called");
        if (frame > life) {
            this.active = false;
        }
        //��frame��Ϊ��������������ʱ��Ļ
        //TODO Ȼ��������������
        else if (frame > 0) {
            //��ɫ��ջ
            Color c = graphics.getColor();
            graphics.setColor(Color.MAGENTA);
            pos = pos.add(traceFunc.getTrace(frame));
            graphics.fillRect((int) pos.x, (int) pos.y, width, height);
            if(hitpoint < 5)
            {graphics.setColor(Color.RED);
                graphics.drawOval((int) pos.x-20, (int) pos.y-20,80, 80);
            }
            //��ɫ�ָ�
            graphics.setColor(c);
            frame++;

        } else {
            frame++;
        }
    }
}
