package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class CutTool extends Shape {
    private static final long serialVersionUID = 1L;

    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.white);
        g2d.setStroke(new BasicStroke(width));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2), 0, 0);

    }
}
