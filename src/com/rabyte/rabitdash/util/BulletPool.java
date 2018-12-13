package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Prefabs.Bullet;

import java.util.Vector;
//子弹对象池
public class BulletPool {
    private static final int MAX_BULLET_NUM = 1000;//最大子弹数
    private static final int INCREASE_SIZE = 100; //空间不足时增加n个子弹对象
    public Vector<Bullet> bullets;
    private static BulletPool _instance = null;
    private int bulletNum = 200;//子弹对象池的大小
    //singleton

    private BulletPool() {
        for (int i = 0; i < bulletNum; ++i) {
            bullets.add(new Bullet());
        }
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
                return e;
            }
        }
        if (createObject()) {
            for (Bullet e : bullets) {
                if (!e.active) {
                    return e;
                }
            }
        }
        return null;
    }

    public boolean createObject() {
        if (bullets.size() < MAX_BULLET_NUM) {
            for (int i = 0; i < INCREASE_SIZE; ++i) {
                bullets.add(new Bullet());

            }
            return true;
        } else {
            return false;
        }
    }
}
