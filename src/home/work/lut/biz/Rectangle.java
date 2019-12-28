package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Rectangle extends Shape {

    /**
     * 定义一个Rectangle类，继承Shape类，用于绘制一个矩形
     */
    private static final long serialVersionUID = 1L;

    public Rectangle() {

    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
