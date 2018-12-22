package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.util.Constants;

import javax.swing.*;
import java.awt.*;

//±³¾°Í¼Æ¬panel
public class BakPanel extends JPanel {

    static Image mainBak;

    BakPanel() {
        mainBak = new ImageIcon(getClass().getResource("/resources/images/mainBak.jpg"))
                .getImage()
                .getScaledInstance(Constants.WINDOW_WIDTH,
                        Constants.WINDOW_HEIGHT,
                        Image.SCALE_DEFAULT);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(mainBak,0,0,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT,this);
    }
    public static void main(String[] args)
    {
        JFrame jf = new JFrame();
        jf.add(new BakPanel());
//        jf.setBounds(
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - Constants.WINDOW_WIDTH) / 2,
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Constants.WINDOW_HEIGHT) / 2,
//                Constants.WINDOW_WIDTH,
//                Constants.WINDOW_HEIGHT);
        jf.setVisible(true);
    }

}
