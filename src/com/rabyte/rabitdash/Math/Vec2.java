package com.rabyte.rabitdash.Math;

import com.rabyte.rabitdash.util.Constants;

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

    public double dot(Vec2 b) {
        return this.x * b.x + this.y * b.y;
    }

    public Vec2 minus(Vec2 b) {
        return new Vec2(this.x - b.x, this.y - b.y);
    }

    public double matmul(Vec2 b) {
        return this.x * b.y - this.y * b.x;
    }

    //TODO 这是左乘
    public Vec2 matmul(Mat2 mat) {
        return new Vec2(mat.a * this.x + mat.b * this.y, mat.c * this.x + mat.d * this.y);
    }

    public Vec2 normalize() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        if (length == 0) {
            return new Vec2(0, 0);
        }
        return new Vec2(this.x / length, this.y / length);
    }

    public double len() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    //单位为角度制
//    public Vec2 rotate(int degree) {
//        double rotateDegree = Math.toRadians(degree);
//        Mat2 mat = new Mat2(Math.cos(rotateDegree), -Math.sin(rotateDegree),
//                Math.sin(rotateDegree), Math.cos(rotateDegree));
//        return this.matmul(mat);
//    }

    //单位为弧度制
    public Vec2 rotate(double rad) {
        Mat2 mat = new Mat2(Math.cos(rad), -Math.sin(rad),
                Math.sin(rad), Math.cos(rad));
        return this.matmul(mat);
    }

    //弧度制
    public double angle(Vec2 b) {
        //夹角小于180
        if (this.matmul(b) >= 0) {
            return Math.acos(this.dot(b) / (this.len() * b.len()));
        } else {
            return Math.PI*2 - Math.acos(this.dot(b) / (this.len() * b.len()));
        }
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}
