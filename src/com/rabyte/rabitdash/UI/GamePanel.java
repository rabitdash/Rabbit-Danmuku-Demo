package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.Prefabs.Bullet;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class GamePanel extends Applet {
    static ImageIcon image;
    private Image iBuffer;
    private Graphics gBuffer;
    public GamePanel() throws HeadlessException {
        super();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        image = new ImageIcon("C:\\Users\\dswxl\\OneDrive\\Í¼Æ¬\\¼øÉÍÍ¼\\"+"china3_20.jpg");

        image = new ImageIcon(getClass().getResource("/resources/images/test.png"));
//        g.drawImage(image.getImage(), 20,20,null);
        Bullet b = new Bullet(g,new Vec2(20,20));

//        if(iBuffer==null)
//        {
//            iBuffer=createImage(this.getSize().width,this.getSize().height);
//            gBuffer=iBuffer.getGraphics();
//        }
//        gBuffer.setColor(getBackground());
//        gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
//        gBuffer.setColor(Color.RED);
//        gBuffer.fillOval(90,90,80,80);
//        g.drawImage(iBuffer,0,0,this);
        for(int i = 0;i<200;i++)
        {
            if(iBuffer==null)
            {
                iBuffer=createImage(this.getSize().width,this.getSize().height);
                gBuffer=iBuffer.getGraphics();
            }
            gBuffer.setColor(getBackground());
            gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
            gBuffer.setColor(Color.RED);
            b.draw(gBuffer);
//            gBuffer.fillOval(90+i,90+i,80,80);
            g.drawImage(iBuffer,0,0,this);

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            b.pos = b.pos.add(new Vec2(1,1));
//            System.out.println(i);
//            System.out.println(b.pos);
//            repaint();
        }
//        b.draw();
//        ThreadGroup threadGroup = new ThreadGroup("Bullet");
//        Thread thread = new Thread(threadGroup,b,"1");
//        Thread thread = new Thread(b,"1");
//        thread.start();
//        thread.run();
//        System.out.println(image);
//        g.setColor(Color.RED);
//        g.draw3DRect(10,10,20,20,true);
//        g.drawString("asdfasdfasdfasdf",100,100);
//        g.setColor(Color.BLACK);
//        g.drawOval(0,0,100,100);

    }
}
