package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Rubber extends Shape {
    private static final long serialVersionUID = 1L;

    public Rubber() {
    }

    public void draw(Graphics2D g) {
        g.setPaint(Color.white);
        g.setStroke(new BasicStroke(20, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawLine(x1, y1, x2, y2);
    }
}
