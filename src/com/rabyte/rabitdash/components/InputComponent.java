package com.rabyte.rabitdash.components;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;

@Component(isSingleEntity = true)
public class InputComponent implements IComponent {
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean z;
    public boolean x;
    public boolean shift;
    public boolean f12;
    public boolean esc;
}
