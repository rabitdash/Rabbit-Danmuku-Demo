package com.rabyte.rabitdash.Tests;

import com.ilargia.games.entitas.api.system.IExecuteSystem;
import com.ilargia.games.entitas.api.system.IInitializeSystem;

public class DebugSystem implements IExecuteSystem, IInitializeSystem {
    @Override
    public void execute(float v) {
        System.out.println("rua" + v);
    }

    @Override
    public void initialize() {
        System.out.println("rua initialize");
    }
}
