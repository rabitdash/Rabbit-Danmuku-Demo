package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;

import java.util.Vector;

//���������ӵ�
//��ĳλ�������ض����ͣ��ض��켣���ӵ�
public class BulletManager {
    static BulletPool bulletPool;
    BulletManager()
    {
//        bulletPool = BulletPool.getInstance();
    }

    //����Բ��ɢ�䵯Ļ
    //directions ����ȷ��
    public void RoundDanmuku(int directions, Vec2 pos, int bulletNum)
    {
        Vector<FixTraceBullet> fixTraceBullets = bulletPool.getBullets(bulletNum);
        for(FixTraceBullet fixTraceBullet : fixTraceBullets)
        {
            fixTraceBullet.pos = pos;
        }
    }


}
