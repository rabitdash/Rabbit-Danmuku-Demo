package com.rabyte.rabitdash.Tests;

import com.ilargia.games.entitas.factories.CollectionsFactory;

import java.util.*;

public class JavaCollectionsFactory implements CollectionsFactory {
    @Override
    public <T> List createList(Class<T> aClass) {
        return new ArrayList<T>();
    }

    @Override
    public <T> Set createSet(Class<T> aClass) {
        return new HashSet<T>();
    }

    @Override
    public <K, V> Map createMap(Class<K> aClass, Class<V> aClass1) {
        return new HashMap<K,V>();
    }
}
