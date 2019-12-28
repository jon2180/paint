package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Component extends Shape {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void draw(Graphics2D g) {
        int w = width;
        int h = height;
        g.drawLine(0, 0, 0, height);
        g.setPaint(color);
        g.drawLine(0, h / 2, w - 10, h / 2);
        g.drawLine(w, h / 2, w - 10, h / 2 + 10);
        g.drawLine(w, h / 2, w - 10, h / 2 + 10);

        g.drawLine(w / 2, 0, w / 2, h);
        g.drawLine(w / 2, 0, w / 2 - 10, 10);
        g.drawLine(w / 2, 0, w / 2 + 10, 10);

        g.drawString("Y", w / 2 - 20, 20);
        g.drawString("X", w - 20, h / 2 + 20);

        for (int x = 0; x < w; x++) {
            int y = (int) Math.cos(x * Math.PI / 180 * h / 3);
            g.drawString(".", x, h / 2 - y);
        }

    }
}
