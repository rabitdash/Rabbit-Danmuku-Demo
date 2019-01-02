package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.Graphics.DMKEffects;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Aircraft.Enemy;
import com.rabyte.rabitdash.Prefabs.Aircraft.Player;
import com.rabyte.rabitdash.Prefabs.Bullet.FixTraceBullet;
import com.rabyte.rabitdash.Prefabs.GameObject;
import com.rabyte.rabitdash.Service.GetKeys;
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
    public static Image backImage;
    private static @Deprecated
    BulletPool bulletPool;
    private static GetKeys getKeys;
    private static Graphics g;
    private static Image iBuffer;
    private static Graphics gBuffer;
    private static int frame;


    public GamePanel() throws HeadlessException {
        super();

    }

    //region start �����¼�
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        getKeys.keyPressed(e.getKeyCode());
    }
    //region end24.��


    @Override
    public void keyReleased(KeyEvent e) {
        getKeys.keyReleased(e.getKeyCode());
    }

    public void addAllEvents() {
        addKeyListener(this);
    }

    private void drawBackgoround(Graphics g, int backLoop) {
        backLoop = (backLoop + 8) % Constants.WINDOW_HEIGHT;            //ÿ֡����8���أ��ۻ��ƶ�����640����ȡ��Ϊ��ʼ�������ۼ�
        for (int i = -1; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                g.drawImage(backImage, j * Constants.WINDOW_WIDTH / 2,
                        i * Constants.WINDOW_HEIGHT + backLoop,
                        (j + 1) * Constants.WINDOW_WIDTH / 2,
                        (i + 1) * Constants.WINDOW_HEIGHT + backLoop,
                        0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
        }
    }

    @Override
    public void start() {
        //����getGraphics(), paint(Graphics g)�ᱻ��������
        //����Java�ĸ����ú���
        //�̻߳����ƣ�thread�ĵ�����start��
        Thread selfPaintThread = new Thread(this);
        getGraphics();
        addAllEvents();
        getKeys = GetKeys.getInstance();
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        selfPaintThread.start();
    }

    //Panel������һ���̻߳��ƣ���ֹ����
    //��������gBuffer�ϣ����iBuffer����g��
    @Override
    public void run() {
        //��Ҫȥg
        //init
        Graphics g = getGraphics();
        frame = 0;
        int backLoop = 0;
        long t1, t2, dt, sleepTime;
        long period = 1000 / Constants.FPS;//����ÿһ��ѭ����Ҫ��ִ��ʱ�䣬��λ������
        backImage = new ImageIcon(getClass().getResource("/resources/images/background.png")).getImage();

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

        GameObject gameO1 = new GameObject();
        GameObject gameO2 = new GameObject();
        GameObject gameO3 = new GameObject();
        gameO1.setActive(true);
        gameO2.setActive(true);
        gameO3.setActive(true);
        //init end
        //TODO
        while (!getKeys.esc && frame < 3000 && p.hitPoint > 0) {
            t1 = System.nanoTime();
            gBuffer.setColor(getBackground());
            gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
            //
            drawBackgoround(gBuffer, backLoop++);
            if (!getKeys.f12) {

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
                if (getKeys.z && frame % 10 == 1) {
                    //existFrame % 10 ��ֹ��ҵ�Ļ����
                    Vector<FixTraceBullet> playerBullets = p.shoot();
                    friendlyObjects.addAll(playerBullets);


                }

                //����
                if (p.focusFire)
                    if (!enemyAircraftObjects.isEmpty()) {
                        int i = 0;
                        for (FixTraceBullet fixTraceBullet : friendlyObjects) {
                            if (fixTraceBullet.frame < 5)
                                continue;
                            fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).
                                    angle(enemyAircraftObjects.get(0).getPos().minus(fixTraceBullet.getPos()))
                                    //���Լ���Ŀ�������Ϊ��ԭ��~Ŀ����������ȥ��ԭ��~�Լ�������
                            );
                            i++;

                        }
                    }

                //��b TODO
                if (getKeys.x && p.bombNum > 0 && frame % 10 == 1) {
                    DMKEffects.Bomb(gBuffer);
                    for (GameObject bullet : enemyBulletObjects) {
                        bullet.active = false;
                    }
                    Player.score += enemyBulletObjects.size();
                    if (!Constants.DEBUG)
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
                            Player.score++;
                        }
                    }
                }
                //collide end
                p.control(getKeys);
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

                DMKDebug.debugWatch(gBuffer, "HP:" + p.hitPoint, p);
                DMKDebug.debugWatch(gBuffer, "Score:" + Player.score, gameO1);
                DMKDebug.debugWatch(gBuffer, "��Time:" + (3000 - frame), gameO2);
                DMKDebug.debugWatch(gBuffer, "Bomb:" + p.bombNum, gameO3);

                if (Constants.DEBUG) {
                }
                //update
                g.drawImage(iBuffer, 0, 0, null);
                //˫���� end
                enemyAircraftObjects.removeIf(gameObject -> !gameObject.isActive());
                enemyBulletObjects.removeIf(gameObject -> !gameObject.isActive());
                friendlyObjects.removeIf(gameObject -> !gameObject.isActive());
                //TODO д����Ⱦ������
            }

        }
        {
            String nameString = JOptionPane.showInputDialog(null,
                    "�����а��������������",
                    "��Ϸ����",
                    JOptionPane.INFORMATION_MESSAGE);
            if (nameString != null)
                RankList.insert(nameString, Player.score);
            JFrame rankFrame = new JFrame("���а�");
            rankFrame.setContentPane(new RankPanel().panel1);
            rankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //���ھ���
            rankFrame.setBounds(
                    (int) (Toolkit.getDefaultToolkit()
                            .getScreenSize()
                            .getWidth()
                            - Constants.WINDOW_WIDTH) >> 1,
                    (int) (Toolkit.getDefaultToolkit()
                            .getScreenSize()
                            .getHeight()
                            - Constants.WINDOW_HEIGHT) / 2,
                    Constants.WINDOW_WIDTH,
                    Constants.WINDOW_HEIGHT);
//        existFrame.add(new BakPanel());
            rankFrame.setVisible(true);
        }
    }

}
