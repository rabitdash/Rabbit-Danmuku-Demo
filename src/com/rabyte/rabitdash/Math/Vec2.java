package com.rabyte.rabitdash.Math;

import java.lang.Math;

public class Vec2 {
    public double x, y;

    public Vec2() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 b) {
        return new Vec2(this.x + b.x, this.y + b.y);
    }

    public Vec2 dot(double num) {
        return new Vec2(this.x * num, this.y * num);
    }

    public Vec2 minus(Vec2 b) {
        return new Vec2(this.x - b.x, this.y - b.y);
    }

    public double matmul(Vec2 b) {
        return this.x * b.y - this.y * b.x;
    }

    public Vec2 matmul(Mat2 mat) {
        return new Vec2(this.x * mat.a + this.y * mat.c, this.y * mat.b + this.y * mat.d);
    }

    public Vec2 normalize() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        if (length == 0) {
            return new Vec2(0, 0);
        }
        return new Vec2(this.x / length, this.y / length);
    }

    public double len() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        return length;
    }

    //单位为角度制
    public Vec2 rotate(int degree) {
        double rotateDegree = 2.0 * Math.PI / (double) degree;
        Mat2 mat = new Mat2(Math.cos(rotateDegree), -Math.sin(rotateDegree),
                Math.sin(rotateDegree), Math.cos(rotateDegree));
        return this.matmul(mat);
    }

    //单位为弧度制
    public Vec2 rotate(double rad) {
        double rotateDegree = rad;
        Mat2 mat = new Mat2(Math.cos(rotateDegree), -Math.sin(rotateDegree),
                Math.sin(rotateDegree), Math.cos(rotateDegree));
        return this.matmul(mat);
    }

    @Override
    public String toString() {
        return String.valueOf(this.x) +" " + String.valueOf(this.y);
    }
}
