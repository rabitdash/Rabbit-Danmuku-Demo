package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.util.GameObject;
import com.rabyte.rabitdash.Math.Vec2;

import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject{
    public Vec2 pos;
    public int life = 3000;//存活寿命，多少帧，寿命=0则active=false，存入子弹对象池
    Image image;

    public Bullet() {
        super();
    }

    public Bullet(Graphics g) {
        super(g);
        pos = new Vec2();
        active = false;
    }

    public Bullet(Graphics g, Vec2 position) {
        super(g);
        this.pos = position;
        active = false;
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu.jpg").getImage();
    }

    public void draw()
    {
        this.graphics.drawOval((int)pos.x,(int)pos.y,20,20);
    }

    public void draw(Graphics g)
    {
//        g.drawOval((int)pos.x,(int)pos.y,20,20);
        g.drawImage(image,(int)pos.x,(int)pos.y,200,200,null);
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
