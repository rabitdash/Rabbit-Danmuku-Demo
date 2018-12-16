package com.rabyte.rabitdash.Math;

import com.rabyte.rabitdash.ITraceFunc;

public class Trace {
    public static final class Linear implements ITraceFunc
    {
        double direction = 0;
        public Linear(){}
        public Linear(double direction)
        {
            this.direction = direction;
        }
        @Override
        public Vec2 getTrace(int frame) {
            return new Vec2(0,-1).rotate(direction).normalize();
        }

        @Override
        public Vec2 getReverseTrace(int frame) {
            return new Vec2(0,1).rotate(direction).normalize();
        }
    }

    public static final class Cos implements ITraceFunc
    {
        double direction = 0;
        public Cos(){}
        public Cos(double direction)
        {
            this.direction = direction;
        }
        @Override
        public Vec2 getTrace(int frame) {
//            return new Vec2(0, -1).dot(new Vec2(frame, Math.cos(frame)).len()).rotate(direction).normalize();
            return new Vec2(frame/180.0*Math.PI, Math.cos(frame/180.0*Math.PI)).rotate(direction).normalize();

        }

        @Override
        public Vec2 getReverseTrace(int frame) {
            return new Vec2(-frame/180.0*Math.PI, -Math.cos(frame/180.0*Math.PI)).rotate(direction).normalize();

        }
    }
    public static final class Halt implements ITraceFunc
    {
        @Override
        public Vec2 getTrace(int frame) {
            return new Vec2(0,0);
        }

        @Override
        public Vec2 getReverseTrace(int frame) {
            return getTrace(frame);
        }
    }
}
