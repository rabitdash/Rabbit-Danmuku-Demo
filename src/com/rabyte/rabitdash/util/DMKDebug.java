package com.rabyte.rabitdash.util;

import com.rabyte.rabitdash.Prefabs.GameObject;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

public class DMKDebug {
    private static HashMap<GameObject, String> hashMap = new HashMap<>();

    //o为调用debugWatch的对象自己
    public static void debugWatch(Graphics g, String s, GameObject o) {
        int messageNum = 1;
        if(o.isActive())
            hashMap.put(o, s);
        Color c = g.getColor();
        //TODO
        for(Iterator<HashMap.Entry<GameObject,String>> entryIterator = hashMap.entrySet().iterator();
        entryIterator.hasNext();)
        {
            HashMap.Entry<GameObject,String> entry = entryIterator.next();
            g.setColor(Color.BLACK);
            if(entry.getKey().isActive()) {
                g.drawString(entry.getValue(), Math.floorDiv(messageNum, 10), 20 * messageNum);
                messageNum++;
            }
            else {
                entryIterator.remove();
            }
        }

        g.setColor(c);
    }
}
