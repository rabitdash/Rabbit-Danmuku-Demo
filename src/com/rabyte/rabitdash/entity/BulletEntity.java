package com.rabyte.rabitdash.entity;

import com.ilargia.games.entitas.Entity;
import com.ilargia.games.entitas.api.ContextInfo;
import com.ilargia.games.entitas.api.IComponent;

import java.util.Stack;

public class BulletEntity extends Entity {
    public BulletEntity(int totalComponents, Stack<IComponent>[] componentContexts, ContextInfo contextInfo) {
        super(totalComponents, componentContexts, contextInfo);
    }
}
