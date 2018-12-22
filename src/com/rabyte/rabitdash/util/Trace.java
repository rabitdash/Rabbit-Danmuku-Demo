package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.util.ITraceFunc;
import com.rabyte.rabitdash.Math.Vec2;

public class Trace {
    public static final class Linear implements ITraceFunc {
        double direction = 0;

        public Linear() {
        }

        public Linear(double direction) {
            this.direction = direction;
        }
        //        public Linear(Vec2 directionVec2){this.direction = }
        @Override
        public Vec2 getTrace(int frame) {
            return new Vec2(1, 0)
                    .rotate(direction)
                    .normalize();
        }

        @Override
        public Vec2 getReverseTrace(int frame) {
            return new Vec2(-1, 0)
                    .rotate(direction)
                    .normalize();
        }
    }

    public static final class Cos implements ITraceFunc {
        double direction = 0;

        public Cos() {
        }

        public Cos(double direction) {
            this.direction = direction;
        }

        @Override
        public Vec2 getTrace(int frame) {
//            return new Vec2(0, -1).dot(new Vec2(frame, Math.cos(frame)).len()).rotate(direction).normalize();
            return new Vec2(frame / 180.0 * Math.PI,
                    Math.cos(frame / 180.0 * Math.PI))
                    .rotate(direction)
                    .normalize();

        }

        @Override
        public Vec2 getReverseTrace(int frame) {
            return new Vec2(-frame / 180.0 * Math.PI,
                    -Math.cos(frame / 180.0 * Math.PI))
                    .rotate(direction)
                    .normalize();

        }
    }

    public static final class Round implements ITraceFunc {
        double direction = 0;

        public Round() {
        }

        public Round(double direction) {
            this.direction = direction;
        }

        @Override
        public Vec2 getTrace(int frame) {
            return new Vec2(Math.cos(frame/180.0 * Math.PI),
                    Math.sin(frame/180.0 * Math.PI))
                    .rotate(direction)
                    .normalize();
        }

        @Override
        public Vec2 getReverseTrace(int frame) {
            return null;
        }
    }

    public static final class Halt implements ITraceFunc {
        @Override
        public Vec2 getTrace(int frame) {
            return new Vec2(0, 0);
        }

        @Override
        public Vec2 getReverseTrace(int frame) {
            return getTrace(frame);
        }
    }
}
