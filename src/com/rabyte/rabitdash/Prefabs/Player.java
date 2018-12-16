package com.rabyte.rabitdash.Prefabs;

import com.rabyte.rabitdash.Drawable;
import com.rabyte.rabitdash.Math.Vec2;
import com.rabyte.rabitdash.util.GameObject;
import com.rabyte.rabitdash.util.GetKeys;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject implements Drawable {
    public static final int IMAGE_WIDTH = 200;
    public static final int IMAGE_HEIGHT = 200;
    public Vec2 pos;
    public double collideSize;//Åö×²Ìå»ý
    Image image;

    public Player(@NotNull Graphics g)
    {
        super();
        this.graphics = g;
        this.pos = new Vec2();
        image = new ImageIcon("C:\\Users\\dswxl\\Desktop\\remu.jpg").getImage();
    }
    public Vec2 getPos() {
        return pos.add(new Vec2(IMAGE_WIDTH / 2.0, IMAGE_HEIGHT / 2.0));
    }

    public void setPos(Vec2 pos) {
        this.pos = pos.add(new Vec2(-IMAGE_WIDTH / 2.0, -IMAGE_HEIGHT / 2.0));
    }

    public void draw()
    {
        graphics.drawImage(image, (int) pos.x, (int) pos.y, IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }

}
