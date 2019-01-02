package com.rabyte.rabitdash.Tests;

import com.ilargia.games.entitas.Context;
import com.ilargia.games.entitas.api.system.ISystem;
import com.ilargia.games.entitas.factories.Collections;
import com.ilargia.games.entitas.systems.Systems;

public class SystemTest {
    static Systems systems;
    Context context;

    private static void createCollections() {
        new Collections(new JavaCollectionsFactory());
    }

    public static void main(String[] args) {
        createCollections();
        Collections.createList(ISystem.class);
        systems = new Systems();
        System.out.println(ISystem.class);
        System.out.println(systems);
        systems.add(new DebugSystem());
        systems.execute(10);
        System.out.println(systems);
    }
}
