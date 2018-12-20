package com.rabyte.rabitdash.util;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class DMKDebug {
    private static HashMap<Integer, String> hashMap = new HashMap<>();

    //o为调用debugWatch的对象自己
    public static void debugWatch(Graphics g, String s, Object o) {
        int messageNum = 1;
        hashMap.put(o.hashCode(), s);
        Color c = g.getColor();
        for (String str : hashMap.values()) {
            g.setColor(Color.BLACK);
            g.drawString(str+"@", Math.floorDiv(messageNum,10), 20 * messageNum);
            messageNum++;
//            System.out.println(messageNum);
        }
        g.setColor(c);
    }
}
