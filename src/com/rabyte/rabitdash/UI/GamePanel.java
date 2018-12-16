package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.Math.Trace;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Bullet;
import com.rabyte.rabitdash.Prefabs.Player;
import com.rabyte.rabitdash.util.BulletPool;
import com.rabyte.rabitdash.util.Constants;
import com.rabyte.rabitdash.util.GetKeys;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.management.PlatformLoggingMXBean;

public class GamePanel extends Applet implements KeyListener, Runnable {
    public static ImageIcon image;
    private static BulletPool bulletPool;
    private Image iBuffer;
    private Graphics gBuffer;
    private static GetKeys getKeys;
    //线程化绘制，thread的调用在start里
    private Thread selfPaintThread;

    public GamePanel() throws HeadlessException {
        super();
        selfPaintThread = new Thread(this);
        getKeys = new GetKeys();
//        setVisible(false);
    }

    //region start 各种事件
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
    //region end

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    public void addAllEvents() {
        addKeyListener(this);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    public void start() {
        //不加getGraphics(), paint(Graphics g)会被调用两次
        //垃圾Java的副作用函数
        getGraphics();
        addAllEvents();
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        selfPaintThread.start();
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        image = new ImageIcon(getClass().getResource("/resources/images/test.png"));
//        Bullet b1, b2;
//        if (iBuffer == null) {
//            iBuffer = createImage(this.getSize().width, this.getSize().height);
//            gBuffer = iBuffer.getGraphics();
//        }
//        bulletPool = BulletPool.getInstance(gBuffer);
//        if (Constants.DEBUG) {
//            b1 = bulletPool.getObject();
//            b2 = bulletPool.getObject();
//            System.out.println();
//
//            //不要用pos，改用setPos
//            b1.setPos(new Vec2(400, 400));
//            b2.setPos(new Vec2(300,300));
//            b1.traceFunc = new Trace.Cos();
//            b2.traceFunc = new Trace.Linear(0);
//            //直接new一个Bullet对象绘制会有重绘问题
//            //鬼知道发生了什么
////        b1 = new Bullet(gBuffer, new Vec2(300, 300));
////        b2 = new Bullet(gBuffer, new Vec2(300, 300));
//
//        }
//        //TODO 这里应是无限循环
//        for (int i = 0; i < 400; i++) {
//            gBuffer.setColor(getBackground());
//            gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
//            if (Constants.DEBUG) {
//                b1.draw();
////                b2.traceFunc = new Trace.Linear(i/180.0*Math.PI);
//                b2.draw();
//            }
//            //在两个中间加渲染
//            g.drawImage(iBuffer, 0, 0, this);
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
////            System.out.println(a++);
//        }
//
//    }

    @Override
    public void run() {
        Graphics g = getGraphics();
//        image = new ImageIcon(getClass().getResource("/resources/images/test.png"));
        Bullet b1, b2;
        if (iBuffer == null) {
            iBuffer = createImage(this.getSize().width, this.getSize().height);
            gBuffer = iBuffer.getGraphics();
        }
        Player p = new Player(gBuffer);
        p.setPos(new Vec2(300,300));
        bulletPool = BulletPool.getInstance(gBuffer);
        if (Constants.DEBUG) {
            b1 = bulletPool.getObject();
            b2 = bulletPool.getObject();
            System.out.println();
            //不要用pos，改用setPos
            b1.setPos(new Vec2(400, 400));
            b2.setPos(new Vec2(300, 300));
            b1.traceFunc = new Trace.Cos();
            b2.traceFunc = new Trace.Linear(0);

            //直接new一个Bullet对象绘制会有重绘问题
            //鬼知道发生了什么
//        b1 = new Bullet(gBuffer, new Vec2(300, 300));
//        b2 = new Bullet(gBuffer, new Vec2(300, 300));

        }
        //TODO 这里应是无限循环
        for (int i = 0; i < 400; i++) {
            gBuffer.setColor(getBackground());
            gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);

            //
            if (Constants.DEBUG) {
                b1.draw();
//                b2.traceFunc = new Trace.Linear(i/180.0*Math.PI);
                b2.draw();
                if(getKeys.down)
                {
                    p.draw();
                    p.setPos(p.getPos().minus(new Vec2(1,0)));
                }
            }
            //在两个中间加渲染

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
