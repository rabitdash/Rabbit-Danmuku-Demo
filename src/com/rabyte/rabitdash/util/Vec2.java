package com.rabyte.rabitdash.util;
import java.lang.Math;

public class Vec2 {
    public double x, y;
    Vec2()
    {
        this.x = 0;
        this.y = 0;
    }

    public Vec2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    Vec2 dot(double num)
    {
        return new Vec2(this.x * num, this.y * num);
    }

    double matmul(Vec2 b)
    {
        return this.x*b.y - this.y*b.x;
    }

    Vec2 normalize()
    {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        return new Vec2(this.x/length, this.y / length);
    }
}
