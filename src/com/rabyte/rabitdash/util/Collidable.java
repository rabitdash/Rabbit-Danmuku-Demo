package com.rabyte.rabitdash.util;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.rabyte.rabitdash.Math.Vec2;

@Component
public interface Collidable extends IComponent {
    //判断是否相碰
    boolean isCollide(Collidable object);
    //相碰事件
    void collideEvent(Collidable object);
    //获取碰撞体积
    double getCollideSize();
    //获取碰撞体位置
    Vec2 getPos();
}
