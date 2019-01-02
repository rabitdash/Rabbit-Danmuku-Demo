package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Math.Vec2;

public class Constants {
    //TODO 将某些变量放在文件里
    public static final int FPS = 60;
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 800;
    public static final boolean DEBUG = false;
    public static final Vec2 WINDOW_CENTER = new Vec2(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);

    public enum BulletTypeEnum {
        CIRCLE,
        ECLIPSE,
        RECTANGLE,
    }

    public enum PlayerTypeEnum {
        RABBIT,
        HUMAN,
        WITCHER,
    }

}
