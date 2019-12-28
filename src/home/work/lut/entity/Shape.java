package home.work.lut.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Shape extends Polygon implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x1, y1, x2, y2;
    public Color color;
    public int width;
    public int height;
    public int currentChoice;
    public int length;
    public BufferedImage image;
    public JPanel board;
    public int fontSize;
    public String fontName;
    public String s;
    public int blodtype;
    public int italic;

    public abstract void draw(Graphics2D g);

    public Polygon p;

    public static double mianji;

}