package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Mat2;
import com.rabyte.rabitdash.Stages.Stage;
import com.rabyte.rabitdash.util.*;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;
import com.rabyte.rabitdash.Prefabs.Player;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class GamePanel extends Applet implements KeyListener, Runnable {
    public static ImageIcon image;

    private static @Deprecated
    BulletPool bulletPool;
    private static GetKeys getKeys;
    private Image iBuffer;
    private Graphics gBuffer;
    private int frame;
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
    //东西画在gBuffer上，最后iBuffer画在g上
    @Override
    public void run() {
        //不要去g
        Graphics g = getGraphics();
        frame = 0;
        long t1, t2, dt, sleepTime;
        long period = 1000 / Constants.FPS;//计算每一次循环需要的执行时间，单位：毫秒


        if (iBuffer == null) {
            iBuffer = createImage(this.getSize().width, this.getSize().height);
            gBuffer = iBuffer.getGraphics();
        }
        if (Constants.DEBUG) {

            Vector<GameObject> hostileObjects = new Vector<>();
            Vector<GameObject> friendlyObjects = new Vector<>();
            Player p = new Player(gBuffer);
            p.setPos(new Vec2(400, 400));
            p.active = true;
            //bullet generation
            GameObject gameO1 = new GameObject();
            gameO1.setActive(true);
            //直接new一个Bullet对象绘制会有重绘问题
            //鬼知道发生了什么
            //TODO 这里应是无限循环
            while (true) {

                t1 = System.nanoTime();
                t2 = System.nanoTime(); //游戏循环执行后的系统时间，单位：毫秒
                dt = (t2 - t1) / 1000000L;  //计算本次循环实际花费的时间，单位：毫秒
                sleepTime = period - dt;//计算本次循环实剩余的时间，单位：毫秒
                //双缓冲 start
                gBuffer.setColor(getBackground());
                gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
                //
                if (!getKeys.f12) {
                    if (Constants.DEBUG) {
                        //TODO 写在渲染类里面
                        //非活动的子弹会从渲染队列中被移除
                        //TODO
                        //draw start
                        p.draw();

                        hostileObjects.addAll(Stage.level1(gBuffer, frame, p.getPos()));
                        //冲了
                        if (getKeys.z && frame % 20 == 1) {
                            //frame % 10 防止玩家弹幕过密
                            friendlyObjects.addAll(p.shoot(gBuffer));
                        }
                        if (getKeys.x) {
                            hostileObjects.clear();
                        }
                        if (!hostileObjects.isEmpty()) {
                            for (GameObject bullet : hostileObjects) {
                                bullet.draw();
                                if (bullet.isCollide(p)) {
                                    bullet.active = false;
                                    p.collideEvent(bullet);
                                }
                            }

                        }
                        for (GameObject bullet : friendlyObjects) {
                            bullet.draw();
                        }
                        hostileObjects.removeIf(gameObject -> !gameObject.isActive());
                        friendlyObjects.removeIf(gameObject -> !gameObject.isActive());
//                        DMKDebug.debugWatch(gBuffer, "存活子弹数：" + BulletPool.getActiveBullet(), p);
                        DMKDebug.debugWatch(gBuffer, "HP:" + p.hitPoint, p);
                        DMKDebug.debugWatch(gBuffer, "存活对象数：" + hostileObjects.size(), gameO1);


                        //draw end
                        //collide start

                        //collide end
                        p.control(getKeys);
//                    System.out.println("frame:"+frame);

                        //在两个中间加渲染

                    }


                    frame++;

                    if (sleepTime <= 0) {        //防止sleepTime为负数
                        sleepTime = 1;
                    }
                    try {
                        System.out.println(frame);
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    //update
                    g.drawImage(iBuffer, 0, 0, this);
                    //双缓冲 end
                }

            }
        }
    }
}
