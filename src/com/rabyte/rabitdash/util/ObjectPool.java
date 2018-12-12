package com.rabyte.rabitdash.util;

public class ObjectPool {
    private ObjectPool _instance = null;
    public GameObject[] gameObjects;
    private static final int MAX_DRAW_NUM=1000;//最大同屏绘制数
    //singleton
    
    public ObjectPool getInstance()
    {
        if (_instance == null)
        {
            _instance = new ObjectPool();
        }
        return _instance;
    }
    private ObjectPool()
    {
        gameObjects = new GameObject[MAX_DRAW_NUM];
    }
}
