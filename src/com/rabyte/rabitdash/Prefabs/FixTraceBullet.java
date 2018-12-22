package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.util.*;
import com.rabyte.rabitdash.Math.Vec2;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class FixTraceBullet extends Bullet implements Drawable, Collidable {
    public static final int IMAGE_WIDTH = 10;
    public static final int IMAGE_HEIGHT = 10;
    public Vec2 pos;
    public double collideSize = 5/1.414;//碰撞体积
    public int life = 1000;//存活寿命，多少帧，寿命=0则active=false，存入子弹对象池
    public int frame = 0;//已存活的帧数
    public ITraceFunc traceFunc;//运动轨迹
    public @Deprecated
    Vec2 direction;//朝向方向 TODO
    Image image;

    public FixTraceBullet() {
        super();
        pos = new Vec2();
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu.jpg").getImage();
        active=false;
    }

    public FixTraceBullet(@NotNull Graphics g) {
        pos = new Vec2();
        //"C:\\Users\\dswxl\\OneDrive\\图片\\本机照片\\TIM图片20181217232334.jpg"
        //TODO 将图片资源获取包装成类
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu2.jpg").getImage();
        active = false;
        graphics = g;
    }

    public Vec2 getPos() {
        return pos.add(new Vec2(IMAGE_WIDTH / 2.0, IMAGE_HEIGHT / 2.0));
    }

    public void setPos(Vec2 pos) {
        this.pos = pos.add(new Vec2(-IMAGE_WIDTH / 2.0, -IMAGE_HEIGHT / 2.0));
    }

    public void draw() {
        //保存先前颜色
        if (frame > life) {
            this.active = false;
        }
        //将frame设为负数可以制作延时弹幕
        //TODO 然而方法并不完美
        else if (frame > 0) {
            //颜色入栈
            Color c = graphics.getColor();
//            graphics.drawImage(image, (int) pos.x, (int) pos.y, imageWidth, imageHeight, null);
            graphics.setColor(Color.RED);
            graphics.fillOval((int) pos.x, (int) pos.y, IMAGE_WIDTH, IMAGE_HEIGHT);
            pos = pos.add(traceFunc.getTrace(frame).dot(v));

            frame++;
            //颜色恢复
            graphics.setColor(c);
//            System.out.println(pos.x + " " + pos.y);
        } else {
            frame++;
        }

    }

    @Override
    public boolean isCollide(Collidable object) {
        if (!this.active)
            return false;
//        System.out.println(this.getPos().minus(object.getPos()).len());
        return this.getPos().minus(object.getPos()).len()
                < Math.abs(this.getCollideSize() + object.getCollideSize());
    }

    @Override
    public void collideEvent(Collidable object) {
//        if (Constants.DEBUG)
//            DMKDebug.debugWatch(graphics, "collide", this);
        Color c = graphics.getColor();
        graphics.setColor(Color.BLUE);
        graphics.drawOval((int) object.getPos().x - 50, (int) object.getPos().y - 50, 100, 100);
        graphics.setColor(c);
    }

    @Override
    public double getCollideSize() {
        return collideSize;
    }
////    @Override
////    public void run() {
//    if(iBuffer==null)
//    {
//        iBuffer=createImage(this.getSize().width,this.getSize().height);
//        gBuffer=iBuffer.getGraphics();
//    }
//            gBuffer.setColor(getBackground());
//            gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
//            gBuffer.setColor(Color.RED);
//            gBuffer.fillOval(90+i,90+i,80,80);
////        long t1, t2, dt, sleepTime;
////        long peroid = 1000 / 10;
////        //计算每一次循环需要的执行时间，单位：毫秒
////        t1 = System.nanoTime();
//////        System.out.println(graphics);
//////        this.graphics.drawOval((int) pos.x, (int) pos.y, 20, 20);
////        while (true) {
////            this.graphics.drawOval((int) pos.x++, (int) pos.y++, 20, 20);
////            t2 = System.nanoTime();
////            dt = (t2 - t1) / 1000000L;
////            sleepTime = peroid - dt;
////            if (sleepTime <= 0) {        //防止sleepTime为复数
////                sleepTime = 2;
////            }
////            try {
////                Thread.sleep(sleepTime);
////            } catch (InterruptedException ex) {
////                t1 = System.nanoTime();
////                ex.printStackTrace();
////            }
////
////
////        }
////    }
}
