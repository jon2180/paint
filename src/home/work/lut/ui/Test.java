package home.work.lut.ui;

import home.work.lut.dao.MyFrame;
import home.work.lut.entity.MyFrameONE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static MyFrame frame;
    public static JTextField jTextField;
    public static JLabel labelA, labelPwd;
    public static JPasswordField inputPwd;
    public static JButton ok, cancal;
    public static int isok = 0;
    public static String name, pwd;
    private static MyFrameONE frameOne;
    private static String[] names = {"gm", "zj"};
    private static String[] pwds = {"123", "456"};
    private JPanel jPanel;

    public Test() {
        jPanel = new JPanel();
        this.setTitle("画图板登录界面");
        labelA = new JLabel("用户名");
        jTextField = new JTextField(10);
        name = "";

        labelPwd = new JLabel("密码");
        inputPwd = new JPasswordField(10);

        cancal = new JButton("取消");
        ok = new JButton("确定");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isok == 1) {
                    frame = new MyFrame("DRAW COM");
                    isok = 0;
                } else if (isok == 2) {
                    frameOne = new MyFrameONE("DRAW VIP");
                    isok = 0;
                }

            }
        });

        ok.addActionListener(this);
        cancal.addActionListener(this);

        jPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        jPanel.add(labelA);
        jPanel.add(labelPwd);
        jPanel.add(jTextField);
        jPanel.add(inputPwd);
        jPanel.add(ok);
        jPanel.add(cancal);

        this.getContentPane().add(jPanel);
        this.setModal(true);
        this.pack();


        this.setLocationRelativeTo(null);
        this.setSize(500, 120);
        isok = 0;
        this.setVisible(true);


    }

    public static void main(String[] args) {
        new Test();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object instanceof JButton) {
            if (object.equals(ok)) {
                if (vailda() && vaildped()) {

                    for (int i = 0; i < 2; i++) {
                        if (name.equals(names[0]) && pwd.equals(pwds[0])) {
                            isok = 1;
                            this.setVisible(false);
                            break;
                        } else if (name.equals(names[1]) && pwd.equals(pwds[1])) {
                            isok = 2;
                            this.setVisible(false);
                            break;
                        }
                    }

                }
            }
        }
    }

    private boolean vailda() {
        boolean f = false;
        String a = jTextField.getText().trim();
        try {
            if (a.length() > 0) {
                f = true;
                name = a;
            } else {
                f = false;
                JOptionPane.showMessageDialog(this, "用户名不能为空");

            }
        } catch (Exception e) {
            f = false;
            JOptionPane.showMessageDialog(this, "请输入正确的用户名");
        }
        return f;
    }

    private boolean vaildped() {
        boolean f = false;
        String a = new String((inputPwd.getPassword()));
        try {
            if (a.length() > 0) {
                f = true;
                pwd = a;
            } else {
                f = false;
                JOptionPane.showMessageDialog(this, "密码不能为空");

            }
        } catch (Exception e) {
            f = false;
            JOptionPane.showMessageDialog(this, "请输入正确的密码");
        }
        return f;
    }
}
