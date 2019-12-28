package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Pencil extends Shape {
    /**
     * 定义一个Pencil类，继承Shape类，用于铅笔绘制
     */
    private static final long serialVersionUID = 1L;

    public Pencil() {

    }

    public void draw(Graphics2D g) {
        g.setPaint(color);
        //为 Graphics2D 上下文设置 Stroke
        g.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        //为呈现算法设置单个首选项的值。
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawLine(x1, y1, x2, y2);
    }
}
