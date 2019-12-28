package home.work.lut.biz;

import home.work.lut.entity.Shape;

import java.awt.*;

public class Line extends Shape {
    /**
     * 定义一个Line类，继承Shape类，用于绘制一个填充椭圆
     */
    private static final long serialVersionUID = 1L;

    public Line() {

    }

    //重写父类的Draw方法，实现直线的绘制
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(width));
        g.drawLine(x1, y1, x2, y2);
    }

}
