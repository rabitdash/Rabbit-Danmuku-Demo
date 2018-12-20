package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Math.Vec2;

public interface Collidable {
    //�ж��Ƿ�����
    boolean isCollide(Collidable object);
    //�����¼�
    void collideEvent(Collidable object);
    //��ȡ��ײ���
    double getCollideSize();
    //��ȡ��ײ��λ��
    Vec2 getPos();
}
