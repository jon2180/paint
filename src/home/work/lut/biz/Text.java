package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Text extends Shape// 文字
{
    private static final long serialVersionUID = 1L;

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setFont(new Font(fontName, italic + blodtype, fontSize));
        if (s != null) {
            g.drawString(s, x1, y1);
        }
    }
}
