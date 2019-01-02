package com.rabyte.rabitdash.util;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.rabyte.rabitdash.Math.Vec2;

@Component
public interface Collidable extends IComponent {
    //�ж��Ƿ�����
    boolean isCollide(Collidable object);
    //�����¼�
    void collideEvent(Collidable object);
    //��ȡ��ײ���
    double getCollideSize();
    //��ȡ��ײ��λ��
    Vec2 getPos();
}
