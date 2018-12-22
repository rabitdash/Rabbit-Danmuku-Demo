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
    //�̻߳����ƣ�thread�ĵ�����start��
    private Thread selfPaintThread;

    public GamePanel() throws HeadlessException {
        super();
        selfPaintThread = new Thread(this);
    }

    //region start �����¼�
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
        //����getGraphics(), paint(Graphics g)�ᱻ��������
        //����Java�ĸ����ú���
        getGraphics();
        addAllEvents();
        getKeys = new GetKeys();
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        selfPaintThread.start();
    }


    //Panel������һ���̻߳��ƣ���ֹ����
    //��������gBuffer�ϣ����iBuffer����g��
    @Override
    public void run() {
        //��Ҫȥg
        Graphics g = getGraphics();
        frame = 0;
        long t1, t2, dt, sleepTime;
        long period = 1000 / Constants.FPS;//����ÿһ��ѭ����Ҫ��ִ��ʱ�䣬��λ������


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
            //ֱ��newһ��Bullet������ƻ����ػ�����
            //��֪��������ʲô
            //TODO ����Ӧ������ѭ��
            while (true) {

                t1 = System.nanoTime();
                t2 = System.nanoTime(); //��Ϸѭ��ִ�к��ϵͳʱ�䣬��λ������
                dt = (t2 - t1) / 1000000L;  //���㱾��ѭ��ʵ�ʻ��ѵ�ʱ�䣬��λ������
                sleepTime = period - dt;//���㱾��ѭ��ʵʣ���ʱ�䣬��λ������
                //˫���� start
                gBuffer.setColor(getBackground());
                gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
                //
                if (!getKeys.f12) {
                    if (Constants.DEBUG) {
                        //TODO д����Ⱦ������
                        //�ǻ���ӵ������Ⱦ�����б��Ƴ�
                        //TODO
                        //draw start
                        p.draw();

                        hostileObjects.addAll(Stage.level1(gBuffer, frame, p.getPos()));
                        //����
                        if (getKeys.z && frame % 20 == 1) {
                            //frame % 10 ��ֹ��ҵ�Ļ����
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
//                        DMKDebug.debugWatch(gBuffer, "����ӵ�����" + BulletPool.getActiveBullet(), p);
                        DMKDebug.debugWatch(gBuffer, "HP:" + p.hitPoint, p);
                        DMKDebug.debugWatch(gBuffer, "����������" + hostileObjects.size(), gameO1);


                        //draw end
                        //collide start

                        //collide end
                        p.control(getKeys);
//                    System.out.println("frame:"+frame);

                        //�������м����Ⱦ

                    }


                    frame++;

                    if (sleepTime <= 0) {        //��ֹsleepTimeΪ����
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
                    //˫���� end
                }

            }
        }
    }
}
