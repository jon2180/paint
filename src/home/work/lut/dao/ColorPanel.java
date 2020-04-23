package home.work.lut.dao;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;

public class ColorPanel extends JPanel {

    public static final long serialVersionUID = 1L;
    public static JButton bt;
    public static JButton bt1;
    private JPanel paneldownchild;
    private BevelBorder bb;
    private BevelBorder bb1;
    private JPanel left;
    private JPanel right;
    private JButton color, copy;

    private ImageIcon icon;
    private Color[] colores = {
            new Color(225, 225, 225),
            new Color(0, 0, 0),
            new Color(127, 127, 127),
            new Color(195, 195, 195),
            new Color(136, 0, 21),
            new Color(185, 122, 87),
            new Color(237, 28, 36),
            new Color(225, 174, 201),
            new Color(225, 127, 39),
            new Color(225, 242, 0),
            new Color(239, 228, 176),
            new Color(34, 117, 76),
            new Color(181, 230, 29),
            new Color(0, 162, 232),
            new Color(153, 217, 234),
            new Color(63, 72, 204),
            new Color(112, 146, 190),
            new Color(163, 73, 164),
            new Color(200, 191, 231),
            new Color(89, 173, 154),
            new Color(8, 193, 194),
            new Color(9, 232, 76),
            new Color(153, 217, 234),
            new Color(199, 73, 4)};

    public ColorPanel() {
        addColorPanel();
    }

    public static void chooseColor() {
        MyFrame.color = JColorChooser.showDialog(null, "请选择颜色", MyFrame.color);
        ColorPanel.bt.setBackground(MyFrame.color);
        MyFrame.itemList[MyFrame.index].color = MyFrame.color;
    }

    public void addColorPanel() {
        this.setPreferredSize(new Dimension(60, 60));
        this.setLayout(null);
        this.setBackground(new Color(195, 195, 195));
        paneldownchild = new JPanel();
        paneldownchild.setBackground(Color.cyan);
        paneldownchild.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        paneldownchild.setBounds(10, 10, 40, 280);
        paneldownchild.setToolTipText("颜色");
        this.add(paneldownchild);
        bb = new BevelBorder(0, Color.gray, Color.white);
        bb1 = new BevelBorder(1, Color.gray, Color.white);

        left = new JPanel();
        left.setBackground(Color.white);
        left.setLayout(null);
        left.setBorder(bb);
        left.setPreferredSize(new Dimension(40, 40));

        bt = new JButton();
        bt.setBounds(5, 5, 20, 20);
        bt.setBorder(bb);
        bt.setBackground(Color.black);
        bt.setSize(20, 20);
        bt1 = new JButton();
        bt1.setBorder(bb1);
        bt1.setBackground(Color.white);
        bt1.setBounds(15, 15, 20, 20);
        left.add(bt);
        left.add(bt1);

        right = new JPanel();
        right.setBackground(new Color(195, 195, 195));
        right.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        right.setPreferredSize(new Dimension(60, 240));
        paneldownchild.add(left);
        paneldownchild.add(right);
        for (int i = 0; i < 24; i++) {
            JButton bt3 = new JButton();
            bt3.setOpaque(true);
            bt3.setBackground(colores[i]);
            bt3.setPreferredSize(new Dimension(20, 20));
            bt3.setBorder(bb);
            bt3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton t = (JButton) e.getSource();
                    Color c = t.getBackground();
                    MyFrame.color = c;
                    bt.setBackground(c);
                    MyFrame.itemList[MyFrame.index].color = c;
                }
            });
            right.add(bt3);
        }
        color = new JButton();
        icon = new ImageIcon("color_48.png");
        color.setIcon(icon);

        copy = new JButton("打印");
        copy.setPreferredSize(new Dimension(40, 20));
        copy.setToolTipText("打印");
        right.add(copy);
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop d = Desktop.getDesktop();
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                pras.add(MediaSizeName.ISO_A4);
                pras.add(OrientationRequested.LANDSCAPE);

            }
        });
        color.setPreferredSize(new Dimension(40, 40));
        color.setToolTipText("更多颜色");
        right.add(color);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor();
            }
        });
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton = (JButton) e.getSource();
                Color c = jButton.getBackground();
                MyFrame.color = c;
                MyFrame.itemList[MyFrame.index].color = c;
            }
        });
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton = (JButton) e.getSource();
                Color c = jButton.getBackground();
                MyFrame.color = c;
                MyFrame.itemList[MyFrame.index].color = c;
            }
        });
    }
}
