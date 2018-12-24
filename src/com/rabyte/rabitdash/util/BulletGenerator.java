package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Bullet;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;
import com.rabyte.rabitdash.Prefabs.GameObject;

import java.util.Vector;

//��ĳλ�������ض����ͣ��ض��켣���ӵ�
public class BulletGenerator {
    static BulletPool bulletPool;


    //����Բ��ɢ�䵯Ļ
    //directions ����ȷ��
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
