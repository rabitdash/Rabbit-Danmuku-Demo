package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;

import java.util.Vector;

//管理并生成子弹
//在某位置生成特定类型，特定轨迹的子弹
public class BulletManager {
    static BulletPool bulletPool;
    BulletManager()
    {
//        bulletPool = BulletPool.getInstance();
    }

    //生成圆形散射弹幕
    //directions 方向确定
    public void RoundDanmuku(int directions, Vec2 pos, int bulletNum)
    {
        Vector<FixTraceBullet> fixTraceBullets = bulletPool.getBullets(bulletNum);
        for(FixTraceBullet fixTraceBullet : fixTraceBullets)
        {
            fixTraceBullet.pos = pos;
        }
    }


}
