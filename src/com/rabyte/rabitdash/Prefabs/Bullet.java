package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.ITraceFunc;
import com.rabyte.rabitdash.util.Collidable;
import com.rabyte.rabitdash.util.GameObject;
import com.rabyte.rabitdash.Math.Vec2;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject implements Drawable, Collidable {
    public static final int IMAGE_WIDTH = 200;
    public static final int IMAGE_HEIGHT = 200;
    public Vec2 pos;
    public double collideSize;//碰撞体积
    public int life = 300;//存活寿命，多少帧，寿命=0则active=false，存入子弹对象池
    public int frame = 0;//已存活的帧数
    public ITraceFunc traceFunc;//运动轨迹
    public Vec2 direction;//朝向方向 TODO
    Image image;

    public Bullet() {
        super();
        pos = new Vec2();
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu.jpg").getImage();
    }

    public Bullet(@NotNull Graphics g) {
        super(g);
        pos = new Vec2();
        //TODO 将图片资源获取包装成类
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu.jpg").getImage();
        active = false;
    }

//    public Bullet(Graphics g, Vec2 position) {
//        super(g);
//        active = false;
//        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu.jpg").getImage();
//        //转化Anchor，从左上角到中央
//        this.pos = position.add(new Vec2(-IMAGE_WIDTH / 2.0, -IMAGE_HEIGHT / 2.0));
//    }

    public Vec2 getPos() {
        return pos.add(new Vec2(IMAGE_WIDTH / 2.0, IMAGE_HEIGHT / 2.0));
    }

    public void setPos(Vec2 pos) {
        this.pos = pos.add(new Vec2(-IMAGE_WIDTH / 2.0, -IMAGE_HEIGHT / 2.0));
    }

    public void draw() {
        //保存先前颜色
        Color c = graphics.getColor();
        if (life <= 0) {
            this.active = false;
        } else {
//            graphics.drawImage(image, (int) pos.x, (int) pos.y, 200, 200, null);
            graphics.setColor(Color.RED);
            graphics.drawOval((int) pos.x, (int) pos.y, 20, 20);
            pos = pos.add(traceFunc.getTrace(frame));
            life--;
            frame++;
//            System.out.println(pos.x + " " + pos.y);
        }
        //颜色恢复
        graphics.setColor(c);
    }

    @Override
    public boolean isCollide(Collidable object) {
        return this.getPos().minus(object.getPos()).len()
                < Math.abs(this.getCollideSize() - object.getCollideSize());
    }

    @Override
    public void collideEvent() {
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
