package com.rabyte.rabitdash.entity;

import com.ilargia.games.entitas.Entity;
import com.ilargia.games.entitas.api.ContextInfo;
import com.ilargia.games.entitas.api.IComponent;

import java.util.Stack;

public class EnemyEntity extends Entity {
    public EnemyEntity(int totalComponents, Stack<IComponent>[] componentContexts, ContextInfo contextInfo) {
        super(totalComponents, componentContexts, contextInfo);
    }
}
