package com.rabyte.rabitdash.Math;
import java.lang.Math;

public class Vec2 {
    public double x, y;

    public Vec2()
    {
        this.x = 0;
        this.y = 0;
    }

    public Vec2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 b)
    {
        return new Vec2(this.x + b.x,this.y + b.y);
    }
    public Vec2 dot(double num)
    {
        return new Vec2(this.x * num, this.y * num);
    }

    public double matmul(Vec2 b)
    {
        return this.x*b.y - this.y*b.x;
    }

    public Vec2 normalize()
    {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        if(length == 0)
        {
            return new Vec2(0,0);
        }
        return new Vec2(this.x/length, this.y / length);
    }
}
