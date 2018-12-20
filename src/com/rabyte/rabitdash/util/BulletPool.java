package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Prefabs.FixTraceBullet;

import java.awt.*;
import java.util.Vector;

//子弹对象池
public class BulletPool {
    private static final int MAX_BULLET_NUM = 1000;//最大子弹数
    private static final int INCREASE_SIZE = 200; //空间不足时增加n个子弹对象
    private static BulletPool _instance = null;
    public static Vector<FixTraceBullet> fixTraceBullets = new Vector<FixTraceBullet>();;
    private int bulletNum = 200;//子弹对象池的大小
    private static Graphics graphics;
    //singleton

    private BulletPool() {
        for (int i = 0; i < bulletNum; ++i) {
            fixTraceBullets.addElement(new FixTraceBullet());
        }
    }

    private BulletPool(Graphics g) {
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

    public static BulletPool getInstance() {
        if (_instance == null) {
            _instance = new BulletPool();
        }
        return _instance;
    }

    public FixTraceBullet getObject() {
        //TODO Optimizition required
        for (FixTraceBullet e :
                fixTraceBullets) {
            if (!e.active) {
                e.active = true;
                return e;
            }
        }
        if (createObject()) {
            for (FixTraceBullet e : fixTraceBullets) {
                if (!e.active) {
                    e.active = true;
                    return e;
                }
            }
        }
        return null;
    }

    public Vector<FixTraceBullet> getObject(int bulletNum) {
        //TODO Optimizition required
        Vector<FixTraceBullet> fixTraceBulletVector = new Vector<FixTraceBullet>();
        for (FixTraceBullet e :
                fixTraceBullets) {
            if (!e.active && bulletNum > 0) {
                e.active = true;
                fixTraceBulletVector.add(e);
                bulletNum--;
            }
        }
        if (createObject()) {
            for (FixTraceBullet e :
                    fixTraceBullets) {
                if (!e.active && bulletNum > 0) {
                    e.active = true;
                    fixTraceBulletVector.add(e);
                    bulletNum--;
                }
            }
        }
        if (bulletNum == 0) {
            return fixTraceBulletVector;
        } else
            return null;
    }

    // auto generated deduplicated code
    // in getObject()
    // so forget about the func name, it doesnt make sense
    private int getBulletNum(int bulletNum, Vector<FixTraceBullet> fixTraceBulletVector) {

        return bulletNum;
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
