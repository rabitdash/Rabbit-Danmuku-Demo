package com.rabyte.rabitdash.Stages;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Enemy;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;
import com.rabyte.rabitdash.util.BulletPool;
import com.rabyte.rabitdash.util.GameObject;
import com.rabyte.rabitdash.util.Trace;

import java.awt.*;
import java.util.Vector;

public class Stage {
    //TODO 硬编码的stage肯定不好，但是现在只能这么干
    private static BulletPool bulletPool;
    private static Drawable[] drawables;
    public Stage() {
    }

    public Stage(Graphics g)
    {
        bulletPool = BulletPool.getInstance(g);
    }

    //TODO 根据frame数来产生敌人和敌机
    public static Vector<GameObject> level1(Graphics g, int frame, Vec2 playerPos)
    {
        bulletPool = BulletPool.getInstance(g);
        Vector<FixTraceBullet> fixTraceBullets = new Vector<>();
        Vector<Enemy> enemies = new Vector<>();
        Vector<GameObject> drawables = new Vector<>();
        int i;//
        switch (frame)
        {
            case 40:
                fixTraceBullets = bulletPool.getBullets(80);
                for (int k=0;k<20;k++)
                {
                    Vec2 pos = new Vec2(200 + 10*k,100);
                    Enemy enemy = new Enemy(g);
                    enemy.setPos(pos);
                    enemy.traceFunc = new Trace.Linear();
                    enemy.life=1000;
                    enemies.add(enemy);
                }
                i = 0;
                for(FixTraceBullet fixTraceBullet: fixTraceBullets)
                {
                    fixTraceBullet.setPos(enemies.get(i%20).getPos());
                    //自机狙
                    fixTraceBullet.traceFunc = new Trace.Linear(
                            new Vec2(1,0)
                            .angle(playerPos.minus(enemies.get(i%20).getPos())
                            ));
                    fixTraceBullet.life=1000-i%20;
                    fixTraceBullet.frame-=i%20;
                    fixTraceBullet.v = 0.04*(i+20);
                    i++;
                }
                break;
            case 300:
                fixTraceBullets = bulletPool.getBullets(80);
                for (int k=0;k<20;k++)
                {
                    Vec2 pos = new Vec2(10*k,100);
                    Enemy enemy = new Enemy(g);
                    enemy.setPos(pos);
                    enemy.traceFunc = new Trace.Linear(Math.toRadians(180));
                    enemy.life=1000;
                    enemies.add(enemy);
                }
                i = 0;
                for(FixTraceBullet fixTraceBullet: fixTraceBullets)
                {
                    fixTraceBullet.setPos(enemies.get(i%20).getPos());
                    //自机狙
                    fixTraceBullet.traceFunc = new Trace.Linear(
                            new Vec2(1,0)
                                    .angle(playerPos.minus(enemies.get(i%20).getPos())
                                    ));
                    fixTraceBullet.life=1000-i%20;
                    fixTraceBullet.frame-=i%20;
                    fixTraceBullet.v = 0.04*(i+20);
                    i++;
                }
                break;
            case 1000:
                fixTraceBullets = bulletPool.getBullets(200);
                i = 0;
                for(FixTraceBullet fixTraceBullet: fixTraceBullets)
                {
                    Vec2 pos = new Vec2(100+0.1*i,10);
                    fixTraceBullet.setPos(pos);
                    //自机狙
                    fixTraceBullet.traceFunc = new Trace.Linear(
                            new Vec2(1,0)
                            .angle(playerPos.minus(pos)
                            )
                    );
                    fixTraceBullet.life=1000-i;
                    fixTraceBullet.frame-=i;
                    fixTraceBullet.v = 0.004*i;
                    i++;
                }
break;
            case 2000:
                 fixTraceBullets = bulletPool.getBullets(200);
                i = 0;
                for(FixTraceBullet fixTraceBullet: fixTraceBullets)
                {
                    Vec2 pos = new Vec2(50*(i%20),10);
                    fixTraceBullet.setPos(pos);
                    //自机狙
                    fixTraceBullet.traceFunc = new Trace.Cos(new Vec2(1,0).angle(playerPos.minus(pos)));
                    fixTraceBullet.life=1000;
                    fixTraceBullet.frame-=i;
                    i++;
                }
break;
            case 3000:
                fixTraceBullets = bulletPool.getBullets(100);

                i=0;
                for(FixTraceBullet fixTraceBullet:fixTraceBullets)
                {
                    Vec2 pos = new Vec2(50*(i%20),10);
                    fixTraceBullet.setPos(pos);
                    //自机狙
                    fixTraceBullet.traceFunc = new Trace.Round(new Vec2(1,0).angle(playerPos.minus(pos)));
                    fixTraceBullet.life=1000;
                    fixTraceBullet.frame-=i;
                    i++;
                }
                 break;
            case 4000:
                fixTraceBullets = bulletPool.getBullets(200);
                i=0;
                for(FixTraceBullet fixTraceBullet:fixTraceBullets)
                {
                    Vec2 pos = new Vec2(50*(i%20),10);
                    fixTraceBullet.setPos(pos);
                    //自机狙
                    fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1,0).angle(playerPos.minus(pos)));
                    fixTraceBullet.life=1000;
                    fixTraceBullet.frame-=i;
                    i++;
                }
                break;
            case 5000:
                fixTraceBullets = bulletPool.getBullets(200);
                i=0;
                for(FixTraceBullet fixTraceBullet:fixTraceBullets)
                {
                    Vec2 pos = new Vec2(50*(i%20),10);
                    fixTraceBullet.setPos(pos);
                    //自机狙
                    fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1,0).angle(playerPos.minus(pos)));
                    fixTraceBullet.life=1000;
                    fixTraceBullet.frame-=i;
                    i++;
                }
                break;
        }
        drawables.addAll(fixTraceBullets);
        drawables.addAll(enemies);
        return drawables;
    }

}
