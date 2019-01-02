package com.rabyte.rabitdash.Stages;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Aircraft.Boss;
import com.rabyte.rabitdash.Prefabs.Aircraft.Enemy;
import com.rabyte.rabitdash.util.BulletPool;
import com.rabyte.rabitdash.util.Trace;

import java.awt.*;
import java.util.Vector;

public class Stage {
    //TODO 硬编码的stage肯定不好，但是现在只能这么干
    private static BulletPool bulletPool;
//    private static Drawable[] drawables;

    public Stage() {
    }

    public Stage(Graphics g) {
        bulletPool = BulletPool.getInstance(g);
    }

    //TODO 根据frame数来产生敌机
    public static Vector<Enemy> level1(Graphics g, int frame, Vec2 playerPos) {
        bulletPool = BulletPool.getInstance(g);
//        Vector<FixTraceBullet> fixTraceBullets = new Vector<>();
        Vector<Enemy> enemies = new Vector<>();
//        Vector<GameObject> gameObjects = new Vector<>();
//        int i;//
        switch (frame) {
            case 100:
                for (int k = 0; k < 10; k++) {
                    Vec2 pos = new Vec2(-200 + 50 * k, 50);
                    Enemy enemy = new Enemy(g);
                    enemy.setPos(pos);
                    enemy.traceFunc = new Trace.Cos();
                    enemy.lifeFrame = 400 + 10 * (20 - k);
                    enemy.existFrame = 0;
                    enemy.collideSize = 15;
                    enemies.add(enemy);
                }
                break;
            case 400:
                for (int k = 0; k < 10; k++) {
                    Vec2 pos = new Vec2(600 - 50 * k, 100);
                    Enemy enemy = new Enemy(g);
                    enemy.setPos(pos);
                    enemy.traceFunc = new Trace.Cos(Math.toRadians(180));
                    enemy.lifeFrame = 400 + 10 * (20 - k);
                    enemy.existFrame = 0;
                    enemy.collideSize = 15;
                    enemies.add(enemy);
                }
            case 1000:
                Boss boss = new Boss(g);
                boss.setPos(new Vec2(300, 60));
                boss.traceFunc = new Trace.Round(Math.toRadians(60));
                boss.lifeFrame = 1300;
                enemies.add(boss);
                break;
            case 2000:
                boss = new Boss(g);
                boss.setPos(new Vec2(300, 60));
                boss.traceFunc = new Trace.Round(Math.toRadians(60));
                boss.lifeFrame = 1300;
                enemies.add(boss);
//            case 300:
//                fixTraceBullets = bulletPool.getBullets(80);
//                for (int k = 0; k < 20; k++) {
//                    Vec2 pos = new Vec2(10 * k, 100);
//                    Enemy enemy = new Enemy(g);
//                    enemy.setPos(pos);
//                    enemy.traceFunc = new Trace.Linear(Math.toRadians(180));
//                    enemy.lifeFrame = 1000;
//                    enemies.add(enemy);
//                }
//                i = 0;
//                for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
//                    fixTraceBullet.setPos(enemies.get(i % 20).getPos());
//                    //自机狙
//                    fixTraceBullet.traceFunc = new Trace.Linear(
//                            new Vec2(1, 0)
//                                    .angle(playerPos.minus(enemies.get(i % 20).getPos())
//                                    ));
//                    fixTraceBullet.lifeFrame = 1000 - i % 20;
//                    fixTraceBullet.existFrame -= i % 20;
//                    fixTraceBullet.v = 0.04 * (i + 20);
//                    i++;
//                }
//                break;
//            case 1000:
//                fixTraceBullets = bulletPool.getBullets(200);
//                i = 0;
//                for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
//                    Vec2 pos = new Vec2(100 + 0.1 * i, 10);
//                    fixTraceBullet.setPos(pos);
//                    //自机狙
//                    fixTraceBullet.traceFunc = new Trace.Linear(
//                            new Vec2(1, 0)
//                                    .angle(playerPos.minus(pos)
//                                    )
//                    );
//                    fixTraceBullet.lifeFrame = 1000 - i;
//                    fixTraceBullet.existFrame -= i;
//                    fixTraceBullet.v = 0.004 * i;
//                    i++;
//                }
//                break;
//            case 2000:
//                fixTraceBullets = bulletPool.getBullets(200);
//                i = 0;
//                for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
//                    Vec2 pos = new Vec2(50 * (i % 20), 10);
//                    fixTraceBullet.setPos(pos);
//                    //自机狙
//                    fixTraceBullet.traceFunc = new Trace.Cos(new Vec2(1, 0).angle(playerPos.minus(pos)));
//                    fixTraceBullet.lifeFrame = 1000;
//                    fixTraceBullet.existFrame -= i;
//                    i++;
//                }
//                break;
//            case 4000:
//                fixTraceBullets = bulletPool.getBullets(200);
//                i = 0;
//                for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
//                    Vec2 pos = new Vec2(50 * (i % 20), 10);
//                    fixTraceBullet.setPos(pos);
//                    //自机狙
//                    fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).angle(playerPos.minus(pos)));
//                    fixTraceBullet.lifeFrame = 1000;
//                    fixTraceBullet.existFrame -= i;
//                    i++;
//                }
//                break;
//            case 5000:
//                fixTraceBullets = bulletPool.getBullets(200);
//                i = 0;
//                for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
//                    Vec2 pos = new Vec2(50 * (i % 20), 10);
//                    fixTraceBullet.setPos(pos);
//                    //自机狙
//                    fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).angle(playerPos.minus(pos)));
//                    fixTraceBullet.lifeFrame = 1000;
//                    fixTraceBullet.existFrame -= i;
//                    i++;
//                }
//                break;
        }
//        gameObjects.addAll(fixTraceBullets);
//        gameObjects.addAll(enemies);
        return enemies;
    }

}
