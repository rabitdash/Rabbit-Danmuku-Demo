package com.rabyte.rabitdash.components;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.rabyte.rabitdash.Math.Vec2;

import java.awt.*;

@Component
public class ViewComponent implements IComponent {
    public Image image=null;
    public Vec2 scale;
    public int existFrame=0;
    public int lifeFrame;
}
