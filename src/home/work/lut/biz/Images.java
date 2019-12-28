package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Images extends Shape {
    private static final long serialVersionUID = 1L;

    public void draw(Graphics2D g) {
        g.drawImage(image, 0, 0, board);
    }
}
