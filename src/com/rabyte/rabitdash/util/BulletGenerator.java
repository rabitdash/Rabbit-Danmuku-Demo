package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Bullet;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;
import com.rabyte.rabitdash.Prefabs.GameObject;

import java.util.Vector;

//在某位置生成特定类型，特定轨迹的子弹
public class BulletGenerator {
    static BulletPool bulletPool;


    //生成圆形散射弹幕
    //directions 方向确定
    public static Vector<GameObject> RoundDanmuku(Vector<FixTraceBullet> bullets, Vec2 pos, int life) {
        int i = 0;
        for (FixTraceBullet bullet : bullets) {
            bullet.setPos(pos);
            bullet.traceFunc = new Trace.Linear(Math.toRadians(360.0 / bullets.size() * i));
            bullet.life = life;
            bullet.v=3;
            i++;
        }
        return new Vector<>(bullets);
    }


}
