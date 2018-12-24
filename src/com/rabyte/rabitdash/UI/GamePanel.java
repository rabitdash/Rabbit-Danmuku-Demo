package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Enemy;
import com.rabyte.rabitdash.Prefabs.FixTraceBullet;
import com.rabyte.rabitdash.Prefabs.GameObject;
import com.rabyte.rabitdash.Prefabs.Player;
import com.rabyte.rabitdash.Stages.Stage;
import com.rabyte.rabitdash.util.*;

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
        Vector<Enemy> enemyAircraftObjects = new Vector<>();
        Vector<GameObject> enemyBulletObjects = new Vector<>();
        Vector<FixTraceBullet> friendlyObjects = new Vector<>();
        Player p = new Player(gBuffer);
        p.setPos(new Vec2(300, 500));
        p.active = true;
//        if (Constants.DEBUG) {
        //bullet generation
        GameObject gameO1 = new GameObject();
        GameObject gameO2 = new GameObject();
        GameObject gameO3 = new GameObject();
        gameO1.setActive(true);
        gameO2.setActive(true);
        gameO3.setActive(true);
//        }
        //ֱ��newһ��Bullet������ƻ����ػ�����
        while (!getKeys.esc && frame < 2000 && p.hitPoint > 0) {
            t1 = System.nanoTime();
            gBuffer.setColor(getBackground());
            gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
            //
            if (!getKeys.f12) {
                enemyAircraftObjects.removeIf(gameObject -> !gameObject.isActive());
                enemyBulletObjects.removeIf(gameObject -> !gameObject.isActive());
                friendlyObjects.removeIf(gameObject -> !gameObject.isActive());
                //TODO д����Ⱦ������
                //�ǻ���ӵ������Ⱦ�����б��Ƴ�
                //TODO
                enemyAircraftObjects.addAll(Stage.level1(gBuffer, frame, p.getPos()));
                //��ֹ�л���Ļ����
                if (frame % 20 == 1) {
                    for (Enemy enemy : enemyAircraftObjects) {

                        Vector<GameObject> enemyBullets = enemy.shoot(p.getPos());
                        enemyBulletObjects.addAll(enemyBullets);
                    }
                }

                //����
                if (getKeys.z && frame % 20 == 1) {
                    //frame % 10 ��ֹ��ҵ�Ļ����
                    Vector<FixTraceBullet> playerBullets = p.shoot();
                    friendlyObjects.addAll(playerBullets);


                }
                if (getKeys.shift)
                    if (!enemyAircraftObjects.isEmpty()) {
                        int i = 0;
                        for (FixTraceBullet fixTraceBullet : friendlyObjects) {
                            if (fixTraceBullet.frame < 16)
                                continue;
                            fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).
                                    angle(enemyAircraftObjects.get(0).getPos().minus(fixTraceBullet.getPos()))
                                    + Math.toRadians(10 * (i - 1)));
                            i++;

                        }
                    }
                //��b TODO
                if (getKeys.x && p.bombNum>0 && frame% 10 == 1) {
                    for (GameObject bullet : enemyBulletObjects) {
                        bullet.active = false;
                    }
                    p.score += enemyBulletObjects.size();
                    p.bombNum--;
                }

                p.draw();
                //collide start
                for (GameObject enemy : enemyAircraftObjects) {
                    enemy.draw();
                    if (enemy.isCollide(p)) {
                        enemy.collideEvent(null);
                        p.collideEvent(enemy);
                    }
                }
                for (GameObject bullet : friendlyObjects) {
                    bullet.draw();
                }
                for (GameObject bullet : enemyBulletObjects) {
                    bullet.draw();
                    if (bullet.isCollide(p)) {
                        bullet.collideEvent(null);
                        p.collideEvent(bullet);
                    }
                }
                for (GameObject enemy : enemyAircraftObjects) {
                    for (GameObject bullet : friendlyObjects) {
                        if (enemy.isCollide(bullet)) {
                            enemy.collideEvent(null);
                            bullet.collideEvent(null);
                            p.score++;
                        }
                    }
                }
                //collide end
                p.move(getKeys);
                //�������м����Ⱦ


                frame++;
                t2 = System.nanoTime(); //��Ϸѭ��ִ�к��ϵͳʱ�䣬��λ������
                dt = (t2 - t1) / 1000000L;  //���㱾��ѭ��ʵ�ʻ��ѵ�ʱ�䣬��λ������
                sleepTime = period - dt;//���㱾��ѭ��ʵʣ���ʱ�䣬��λ������
                if (sleepTime <= 0) {        //��ֹsleepTimeΪ����
                    sleepTime = 1;
                }
                try {
                    System.out.println(frame);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (Constants.DEBUG) {
                    DMKDebug.debugWatch(gBuffer, "HP:" + p.hitPoint, p);
                    DMKDebug.debugWatch(gBuffer, "Score:s" + p.score, gameO1);
                    DMKDebug.debugWatch(gBuffer,"Time:" + (2000-frame),gameO2);
                    DMKDebug.debugWatch(gBuffer, "Bomb:" + p.bombNum, gameO3);
                }
                //update
                g.drawImage(iBuffer, 0, 0, null);
                //˫���� end
            }

        }
        int retry = 0;
        while (retry < 3) {
            if (RankList.insert(JOptionPane.showInputDialog("�����������"), p.score))
                retry = 3;
            else
                JOptionPane.showMessageDialog(null, "Something happend, please retry");
            retry++;
        }
        JFrame rankFrame = new JFrame("RankList");
        rankFrame.setContentPane(new RankPanel().panel1);
        rankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //���ھ���
        rankFrame.setBounds(
                (int) (Toolkit.getDefaultToolkit()
                        .getScreenSize()
                        .getWidth()
                        - Constants.WINDOW_WIDTH) / 2,
                (int) (Toolkit.getDefaultToolkit()
                        .getScreenSize()
                        .getHeight()
                        - Constants.WINDOW_HEIGHT) / 2,
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT);
//        frame.add(new BakPanel());
        rankFrame.setVisible(true);
    }

}
