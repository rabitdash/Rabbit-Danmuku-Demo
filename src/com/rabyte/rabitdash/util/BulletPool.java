package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;

import java.awt.*;
import java.util.Vector;

//子弹对象池
public class BulletPool {
    private static final int MAX_BULLET_NUM = 10000;//最大子弹数
    private static final int INCREASE_SIZE = 1000; //空间不足时增加n个子弹对象
    public static Vector<FixTraceBullet> fixTraceBullets = new Vector<FixTraceBullet>();
    public static int count = 0;
    ;
    private static BulletPool _instance = null;
    private static Graphics graphics;
    private int bulletNum = 4000;//子弹对象池的大小
    //singleton

    private BulletPool() {
    }

    private BulletPool(Graphics g) {
        count = 0;
        graphics = g;
        for (int i = 0; i < bulletNum; ++i) {
            fixTraceBullets.addElement(new FixTraceBullet(g));
        }
    }

    public static BulletPool getInstance(Graphics g) {
        if (_instance == null) {
            _instance = new BulletPool(g);
        }
        return _instance;
    }

    public static int getActiveBullet() {
        Vector<FixTraceBullet> a = new Vector<>(fixTraceBullets);
        a.removeIf(fixTraceBullet -> !fixTraceBullet.isActive());
        return a.size();
    }

    public FixTraceBullet getBullets() {
        //TODO Optimizition required
        for (FixTraceBullet e :
                fixTraceBullets) {
            if (!e.active) {
                e.active = true;
                e.life=600;
                e.frame=0;
                e.v = 1;
                return e;
            }
        }
        if (createObject()) {
            for (FixTraceBullet e : fixTraceBullets) {
                if (!e.active) {
                    e.active = true;
                    e.life=600;
                    e.frame=0;
                    e.v = 1;
                    return e;
                }
            }
        }
        return null;
    }

    public Vector<FixTraceBullet> getBullets(int bulletNum) {
        //TODO Optimizition required
        Vector<FixTraceBullet> fixTraceBulletVector = new Vector<FixTraceBullet>();
        for (FixTraceBullet e :
                fixTraceBullets) {
                if (!e.active && fixTraceBulletVector.size() < bulletNum) {
                    e.active = true;
                    e.life=600;
                    e.frame=0;
                    e.v = 1;
                    fixTraceBulletVector.add(e);
                } else if(fixTraceBulletVector.size() == bulletNum) {
//                    System.out.println(fixTraceBulletVector.size());
                    return fixTraceBulletVector;
            }
        }
        if (createObject()) {
            for (FixTraceBullet e :
                    fixTraceBullets) {
                if (!e.active) {
                    if (fixTraceBulletVector.size() < bulletNum) {
                        e.active = true;
                        e.frame=0;
                        e.life=600;
                        e.v = 1;
                        fixTraceBulletVector.add(e);
                    } else {
                        return fixTraceBulletVector;
                    }
                }
            }
        }
        return null;
    }

    public boolean createObject() {
        if (fixTraceBullets.size() < MAX_BULLET_NUM) {
            for (int i = 0; i < INCREASE_SIZE; ++i) {
                fixTraceBullets.addElement(new FixTraceBullet(graphics));
            }
//            System.out.println("create");
            return true;
        } else {
            return false;
        }
    }
}
