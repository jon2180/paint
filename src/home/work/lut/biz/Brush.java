package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;
import java.util.Random;

public class Brush extends Shape {
    private static final long serialVersionUID = 1L;
    private int[] fx = new int[100];
    private int[] fy = new int[100];

    public Brush() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            fx[i] = random.nextInt(16) - 16;
            fy[i] = random.nextInt(16) - 16;
        }
    }

    public void draw(Graphics2D g) {
        g.setPaint(color);
        ;
        g.setStroke(new BasicStroke(0, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        for (int i = 0; i < 100; i++) {
            double d = (double) fx[i];
            double c = (double) fy[i];
            g.drawLine((int) (x1 + d * Math.sin(d)), (int) (y1 + c * Math.sin(c)), (int) (x2 + d * Math.sin(d)),
                    (int) (y2 + c * Math.sin(c)));
        }
    }
}





