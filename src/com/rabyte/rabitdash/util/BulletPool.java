package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Prefabs.Bullet;

import java.awt.*;
import java.util.Vector;

//子弹对象池
public class BulletPool {
    private static final int MAX_BULLET_NUM = 1000;//最大子弹数
    private static final int INCREASE_SIZE = 100; //空间不足时增加n个子弹对象
    private static BulletPool _instance = null;
    public static Vector<Bullet> bullets = new Vector<Bullet>();;
    private int bulletNum = 200;//子弹对象池的大小
    //singleton

    private BulletPool() {
        for (int i = 0; i < bulletNum; ++i) {
            bullets.addElement(new Bullet());
        }
    }

    private BulletPool(Graphics g) {
        for (int i = 0; i < bulletNum; ++i) {
            bullets.addElement(new Bullet(g));
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

    public Bullet getObject() {
        //TODO Optimizition required
        for (Bullet e :
                bullets) {
            if (!e.active) {
                e.active = true;
                return e;
            }
        }
        if (createObject()) {
            for (Bullet e : bullets) {
                if (!e.active) {
                    e.active = true;
                    return e;
                }
            }
        }
        return null;
    }

    public Vector<Bullet> getObject(int bulletNum) {
        //TODO Optimizition required
        Vector<Bullet> bulletVector = new Vector<Bullet>();
        bulletNum = getBulletNum(bulletNum, bulletVector);
        if (createObject()) {
            bulletNum = getBulletNum(bulletNum, bulletVector);
        }
        if (bulletNum == 0) {
            return bulletVector;
        } else
            return null;
    }

    // auto generated deduplicated code
    // in getObject()
    // so forget about the func name, it doesnt make sense
    private int getBulletNum(int bulletNum, Vector<Bullet> bulletVector) {
        for (Bullet e :
                bullets) {
            if (!e.active && bulletNum > 0) {
                e.active = true;
                bulletVector.add(e);
                bulletNum--;
            }
        }
        return bulletNum;
    }

    public boolean createObject() {
        if (bullets.size() < MAX_BULLET_NUM) {
            for (int i = 0; i < INCREASE_SIZE; ++i) {
                bullets.addElement(new Bullet());
            }
            System.out.println("create");
            return true;
        } else {
            return false;
        }
    }
}
