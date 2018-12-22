package com.rabyte.rabitdash.Math;

//TODO java自带的Math过于精确
//自定义可以发挥更多性能
public class DMKMath {
    //degree to rad
    public static double toRadian(double degree) {
        // 1 degree = 0.0174rad
        //其实这种精度就已经够了
        //因为有效数字多一位，精度提升10倍
        return degree * 0.0174;
    }

    public static double sign(double num) {
        if (num > 0) {
            return 1;
        } else if (num < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
