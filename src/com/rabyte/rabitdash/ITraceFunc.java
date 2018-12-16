package com.rabyte.rabitdash;

import com.rabyte.rabitdash.Math.Vec2;

public interface ITraceFunc {
    public Vec2 getTrace(int frame);
    public Vec2 getReverseTrace(int frame);
}

