package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Oval extends Shape {
    /**
     * 定义一个OVal类，继承Shape类，用于绘制一个椭圆
     */
    private static final long serialVersionUID = 1L;

    public void draw(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));

    }
}
