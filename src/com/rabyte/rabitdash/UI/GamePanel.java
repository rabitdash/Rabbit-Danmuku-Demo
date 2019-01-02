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

    //region start 各种事件
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        getKeys.keyPressed(e.getKeyCode());
    }
    //region end24.、


    @Override
    public void keyReleased(KeyEvent e) {
        getKeys.keyReleased(e.getKeyCode());
    }

    public void addAllEvents() {
        addKeyListener(this);
    }

    private void drawBackgoround(Graphics g, int backLoop) {
        backLoop = (backLoop + 8) % Constants.WINDOW_HEIGHT;            //每帧下移8像素，累积移动超过640像素取余为初始数重新累计
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
        //不加getGraphics(), paint(Graphics g)会被调用两次
        //垃圾Java的副作用函数
        //线程化绘制，thread的调用在start里
        Thread selfPaintThread = new Thread(this);
        getGraphics();
        addAllEvents();
        getKeys = GetKeys.getInstance();
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        selfPaintThread.start();
    }

    //Panel单独分一个线程绘制，防止阻塞
    //东西画在gBuffer上，最后iBuffer画在g上
    @Override
    public void run() {
        //不要去g
        //init
        Graphics g = getGraphics();
        frame = 0;
        int backLoop = 0;
        long t1, t2, dt, sleepTime;
        long period = 1000 / Constants.FPS;//计算每一次循环需要的执行时间，单位：毫秒
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

                //非活动的子弹会从渲染队列中被移除
                //TODO
                enemyAircraftObjects.addAll(Stage.level1(gBuffer, frame, p.getPos()));
                //防止敌机弹幕过密
                if (frame % 20 == 1) {
                    for (Enemy enemy : enemyAircraftObjects) {

                        Vector<GameObject> enemyBullets = enemy.shoot(p.getPos());
                        enemyBulletObjects.addAll(enemyBullets);
                    }
                }

                //冲了
                if (getKeys.z && frame % 10 == 1) {
                    //existFrame % 10 防止玩家弹幕过密
                    Vector<FixTraceBullet> playerBullets = p.shoot();
                    friendlyObjects.addAll(playerBullets);


                }

                //缓动
                if (p.focusFire)
                    if (!enemyAircraftObjects.isEmpty()) {
                        int i = 0;
                        for (FixTraceBullet fixTraceBullet : friendlyObjects) {
                            if (fixTraceBullet.frame < 5)
                                continue;
                            fixTraceBullet.traceFunc = new Trace.Linear(new Vec2(1, 0).
                                    angle(enemyAircraftObjects.get(0).getPos().minus(fixTraceBullet.getPos()))
                                    //从自己到目标的向量为（原点~目标向量）减去（原点~自己向量）
                            );
                            i++;

                        }
                    }

                //放b TODO
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
                //在两个中间加渲染


                frame++;
                t2 = System.nanoTime(); //游戏循环执行后的系统时间，单位：毫秒
                dt = (t2 - t1) / 1000000L;  //计算本次循环实际花费的时间，单位：毫秒
                sleepTime = period - dt;//计算本次循环实剩余的时间，单位：毫秒
                if (sleepTime <= 0) {        //防止sleepTime为负数
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
                DMKDebug.debugWatch(gBuffer, "残Time:" + (3000 - frame), gameO2);
                DMKDebug.debugWatch(gBuffer, "Bomb:" + p.bombNum, gameO3);

                if (Constants.DEBUG) {
                }
                //update
                g.drawImage(iBuffer, 0, 0, null);
                //双缓冲 end
                enemyAircraftObjects.removeIf(gameObject -> !gameObject.isActive());
                enemyBulletObjects.removeIf(gameObject -> !gameObject.isActive());
                friendlyObjects.removeIf(gameObject -> !gameObject.isActive());
                //TODO 写在渲染类里面
            }

        }
        {
            String nameString = JOptionPane.showInputDialog(null,
                    "在排行榜上留下你的姓名",
                    "游戏结束",
                    JOptionPane.INFORMATION_MESSAGE);
            if (nameString != null)
                RankList.insert(nameString, Player.score);
            JFrame rankFrame = new JFrame("排行榜");
            rankFrame.setContentPane(new RankPanel().panel1);
            rankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //窗口居中
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
