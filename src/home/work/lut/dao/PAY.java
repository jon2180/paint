package home.work.lut.dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PAY extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel jPanel;
    public static JButton cancel;
    public static JButton ok;
    public static JLabel pr;

    public PAY() {
        jPanel = new JPanel();
        this.setSize(600, 500);
        this.setTitle("支付界面");
        this.setVisible(true);
        cancel = new JButton("取消");
        cancel.setSize(new Dimension(20, 20));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ok = new JButton("确定");
        ok.setSize(new Dimension(20, 20));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                setVisible(false);
            }
        });
        ImageIcon imageIcon = new ImageIcon("circle.png");
        pr = new JLabel(imageIcon);
        pr.setSize(new Dimension(100, 100));
        jPanel.add(pr);
        jPanel.add(ok);
        jPanel.add(cancel);


        this.getContentPane().add(jPanel);
        this.setModal(true);
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "谢谢", "", JOptionPane.ERROR_MESSAGE);
    }

}
