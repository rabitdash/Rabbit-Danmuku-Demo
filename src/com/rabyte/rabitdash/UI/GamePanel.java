package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.Math.Mat2;
import com.rabyte.rabitdash.util.Trace;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;
import com.rabyte.rabitdash.Prefabs.Player;
import com.rabyte.rabitdash.util.BulletPool;
import com.rabyte.rabitdash.util.Constants;
import com.rabyte.rabitdash.util.DMKDebug;
import com.rabyte.rabitdash.util.GetKeys;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class GamePanel extends Applet implements KeyListener, Runnable {
    public static ImageIcon image;
    private static BulletPool bulletPool;
    private static GetKeys getKeys;
    private Image iBuffer;
    private Graphics gBuffer;
    //线程化绘制，thread的调用在start里
    private Thread selfPaintThread;

    public GamePanel() throws HeadlessException {
        super();
        selfPaintThread = new Thread(this);
    }

    //region start 各种事件
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        getKeys.keyPressed(e.getKeyCode());
//        System.out.println(e.getKeyChar());
    }
    //region end

    @Override
    public void keyReleased(KeyEvent e) {
        getKeys.keyReleased(e.getKeyCode());
//        System.out.println(e.getKeyChar());
    }

    public void addAllEvents() {
        addKeyListener(this);
    }

    @Override
    public void start() {
        //不加getGraphics(), paint(Graphics g)会被调用两次
        //垃圾Java的副作用函数
        getGraphics();
        addAllEvents();
        getKeys = new GetKeys();
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        selfPaintThread.start();
    }


    //Panel单独分一个线程绘制，防止阻塞
    @Override
    public void run() {
        Graphics g = getGraphics();
//        image = new ImageIcon(getClass().getResource("/resources/images/test.png"));
        FixTraceBullet b1, b2;
        if (iBuffer == null) {
            iBuffer = createImage(this.getSize().width, this.getSize().height);
            gBuffer = iBuffer.getGraphics();
        }


        Player p = new Player(gBuffer);
        p.setPos(new Vec2(400, 400));
        bulletPool = BulletPool.getInstance(gBuffer);
        Vector<FixTraceBullet> fixTraceBullets = bulletPool.getObject(20);
        System.out.println(fixTraceBullets.size());
        double i = 0;
        //bullet generation
        for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
            fixTraceBullet.setPos(new Vec2(100, 100));
//            System.out.println("floorDiv" + Math.floorDiv((int)i,30));
//            fixTraceBullet.traceFunc = new Trace.Linear(Math.floorDiv((int)i,30)*30.0/180.0* Math.PI);
            fixTraceBullet.traceFunc = new Trace.Cos(0);
            fixTraceBullet.life = 5000;
            fixTraceBullet.frame -= i;
            fixTraceBullet.active = true;
            i++;
        }
        if (Constants.DEBUG) {

            b1 = bulletPool.getObject();
            b2 = bulletPool.getObject();
            System.out.println();
            //不要用pos，改用setPos
            b1.setPos(new Vec2(0, 400));
            b2.setPos(new Vec2(100, 100));
            System.out.println(p.getCollideSize());
            System.out.println(b2.getCollideSize());

            b1.traceFunc = new Trace.Halt();
            b2.traceFunc = new Trace.Halt();

            //直接new一个Bullet对象绘制会有重绘问题
            //鬼知道发生了什么
//        b1 = new FixTraceBullet(gBuffer, new Vec2(300, 300));
//        b2 = new FixTraceBullet(gBuffer, new Vec2(300, 300));

        }
        //TODO 这里应是无限循环
        while (true) {
            //双缓冲 start
            gBuffer.setColor(getBackground());
            gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
            //双缓冲 end
            //
            if (Constants.DEBUG) {
                //TODO 写在渲染类里面
                //非活动的子弹会从渲染队列中被移除
                fixTraceBullets.removeIf(fixTraceBullet -> !fixTraceBullet.isActive());
                //TODO
                //draw start
                for (FixTraceBullet fixTraceBullet : fixTraceBullets) {
                    if (fixTraceBullet.isActive()) {
                        fixTraceBullet.draw();
                        //collide start TODO
                        if(fixTraceBullet.isCollide(p))
                        {
                            fixTraceBullet.collideEvent(p);
                        }
                        //collide end
//                        System.out.println(fixTraceBullet.getPos());
                    }
                }
                p.draw();
                //draw end
                //collide start

                //collide end

//                b1.draw();
//                b2.traceFunc = new Trace.Linear(i/180.0*Math.PI);
//                b2.draw();
                p.control(getKeys);

//                if (b1.isCollide(p)) {
////                    gBuffer.drawOval((int) p.getPos().x, (int) p.getPos().y, 300, 300);
//                    DMKDebug.debugWatch(gBuffer, "b1 collide with Player");
//                }


                //在两个中间加渲染
            }
            g.drawImage(iBuffer, 0, 0, this);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
//            System.out.println(a++);
        }
    }
}
