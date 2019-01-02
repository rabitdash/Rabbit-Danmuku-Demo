package com.rabyte.rabitdash.components;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.rabyte.rabitdash.Math.Vec2;

@Component
public class MoveComponent implements IComponent {
    public Vec2 v;
}
