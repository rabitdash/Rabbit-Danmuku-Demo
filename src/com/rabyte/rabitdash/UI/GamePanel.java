package com.rabyte.rabitdash.UI;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class GamePanel extends Applet {
    static ImageIcon image;
    public GamePanel() throws HeadlessException {
        super();
    }

    @Override
    public void paint(Graphics g)
    {
        image = new ImageIcon("C:\\Users\\dswxl\\OneDrive\\Í¼Æ¬\\¼øÉÍÍ¼\\"+"china3_20.jpg");

        image = new ImageIcon(getClass().getResource("/resources/images/test.png"));
        g.drawImage(image.getImage(), 20,20,null);
//        System.out.println(image);
//        g.setColor(Color.RED);
//        g.draw3DRect(10,10,20,20,true);
//        g.drawString("asdfasdfasdfasdf",100,100);
//        g.setColor(Color.BLACK);
//        g.drawOval(0,0,100,100);

    }
}
