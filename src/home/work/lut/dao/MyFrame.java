package home.work.lut.dao;

import home.work.lut.biz.Component;
import home.work.lut.biz.Rectangle;
import home.work.lut.biz.*;
import home.work.lut.entity.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {

    public static final long serialVersionUID = 1L;
    public static int saved = 0;
    public static Color c = Color.black;
    public Graphics2D g;
    public int lengthCount;
    public static String fontName = new String(" 宋体 ");
    public static int fSize = 16;
    public static int blodtype = Font.PLAIN;
    public static int italic = Font.PLAIN;

    public static int index = 0;
    public static home.work.lut.entity.Shape[] itemList = new home.work.lut.entity.Shape[5000];

    Polygon polygon = new Polygon();

    public static int stroke = 1;
    public static Color color = Color.black;
    public static int currentChoice = 3;
    public static MyMenu menu;
    MyToolbar myToolbar;
    ColorPanel colorPanel;
    int length;

    public static DrawPanel drawingArea;
    public JLabel statusBar;
    public JLabel Area;

    public Polygon p;
    ImageIcon icon = new ImageIcon("");
    public static home.work.lut.entity.Shape shape1;

    public MyFrame() {
    }

    public MyFrame(String s) {
        init(s);
        setVisible(true);
    }

    public void init(String s) {
        this.setTitle(s);
        this.setSize(1500, 600);
        this.setLocationRelativeTo(null);
        menu = new MyMenu();
        myToolbar = new MyToolbar();
        colorPanel = new ColorPanel();
        add(colorPanel, BorderLayout.WEST);
        try {
            ImageIcon imageIcon = new ImageIcon("themeicon.png");
            Image image = imageIcon.getImage();
            this.setIconImage(image);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "图标异常");
        }
        drawingArea = new DrawPanel();
        this.add(drawingArea, BorderLayout.CENTER);
        statusBar = new JLabel();
        Area = new JLabel();
        this.add(statusBar, BorderLayout.SOUTH);
        this.add(Area, BorderLayout.EAST);

        statusBar.setText("坐标");
        Area.setText("面积");
        Area.setSize(20, 20);
        statusBar.setBackground(new Color(195, 195, 195));
        Area.setBackground(new Color(195, 195, 195));
        drawingArea.createNewGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (saved == 0) {
                    int n = JOptionPane.showConfirmDialog(null, "您还没保存！就准备跑了？", "保存文件提示", JOptionPane.OK_CANCEL_OPTION);
                    if (n == 0) {
                        System.exit(0);
                    }
                }
                if (saved == 1) {
                    System.exit(0);
                }
            }
        });
    }


//菜单栏

    /**
     * 思路；主要调用JMenubar主菜单 （框架）
     * JMenuItem 子菜单（完善各个菜单导航）
     * 并且完成各个子菜单栏的 ！！监听（调用 文件存储 新建）
     * 具体方法：
     * 调用JMenuber  JMenuTtem  JMenu功能键 方法
     */


    class MyMenu {
        private JMenuBar jMenuBar;
        private JMenuItem file_item_new, file_item_open, file_item_save, file_item_exit;
        private JMenuItem set_item_color, set_item_undo;
        private JMenuItem[] stroke_item;
        private JMenuItem help_item_info;
        private JMenuItem help_item_use;
        private JMenuItem help_item_pay;


        private JMenu file_menu, set_menu, help_menu, stroke_menu;
        private String strokes[] = {"stroke1.png", "stroke2.png", "stroke3.png",
                "stroke4.png"};

        public MyMenu() {
            addMenu();
        }

        public void addMenu() {
            jMenuBar = new JMenuBar();
            stroke_item = new JMenuItem[strokes.length];
            file_menu = new JMenu("File");
            set_menu = new JMenu("Set");
            help_menu = new JMenu("Help");
            stroke_menu = new JMenu("Storke");
            file_item_new = new JMenuItem("New");
            file_item_open = new JMenuItem("Open");
            file_item_save = new JMenuItem("Sava");
            file_item_exit = new JMenuItem("Exit");
            set_item_color = new JMenuItem("Color");
            set_item_undo = new JMenuItem("Undo");
            help_item_use = new JMenuItem("State");
            help_item_info = new JMenuItem("Auther");
            help_item_pay = new JMenuItem("Pay");
            for (int i = 0; i < 4; i++) {
                stroke_item[i] = new JMenuItem("", new ImageIcon(strokes[i]));
                stroke_menu.add(stroke_item[i]);
            }
            help_item_pay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //   new PAY();
                    JOptionPane.showMessageDialog(null, "", "pay", JOptionPane.PLAIN_MESSAGE);
                }
            });
            help_item_info.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,
                            "" + "关于画图\n" + "该软件由苟慜开发完成\n" + "班级：软件工程二班    \n"
                                    + "学号：1816270248  \n" + "邮箱：1924480487@qq.com\n" + "哈哈哈哈哈哈哈哈哈\n",
                            "作者", JOptionPane.PLAIN_MESSAGE);

                }
            });
            help_item_use.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "" + "画图软件使用说明书\n"
                            + "1.本软件可以实现以下功能：\r\n" + "自己看图标！！！！\r\n", "使用说明", JOptionPane.PLAIN_MESSAGE);

                }
            });
            help_menu.add(help_item_use);
            help_menu.add(help_item_info);
            help_menu.add(help_item_pay);
            file_menu.add(file_item_new);
            file_menu.add(file_item_open);
            file_menu.add(file_item_save);
            file_menu.add(file_item_exit);
            set_menu.add(set_item_color);
            set_menu.add(set_item_undo);
            set_menu.add(stroke_menu);
            jMenuBar.add(file_menu);
            jMenuBar.add(set_menu);
            jMenuBar.add(help_menu);
            setJMenuBar(jMenuBar);
            file_item_new.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.newFile();
                }
            });
            file_item_save.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.saveFile();
                    saved = 1;
                }
            });
            file_item_open.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.openFile();
                    saved = 0;
                }
            });
            file_item_exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (saved == 1) {
                        System.exit(0);
                    } else {
                        int n = JOptionPane.showConfirmDialog(null, "你还没有保存，就准备跑了吗？", "文件保存提示", JOptionPane.OK_CANCEL_OPTION);
                        if (n == 0) {
                            System.exit(0);
                        }
                    }
                }
            });
            set_item_color.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ColorPanel.chooseColor();
                }
            });

            set_item_undo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingArea.undo();
                }
            });
            stroke_item[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stroke = 1;
                    itemList[index].width = stroke;
                }
            });
            stroke_item[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stroke = 5;
                    itemList[index].width = stroke;
                }
            });
            stroke_item[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stroke = 15;
                    itemList[index].width = stroke;
                }
            });
            stroke_item[3].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stroke = 25;
                    itemList[index].width = stroke;
                }
            });

        }

        /**
         * 文件存储：
         * 需求 ：保存，新建， 打开
         * 思路：
         * 1.保存文件
         * JFileChooser   调用该方法
         * JpgFilter jpg = new JpgFilter();
         * BmpFilter bmp = new BmpFilter();
         * PngFilter png = new PngFilter();
         * GifFilter gif = new GifFilter();
         * 创建该类方法 去实现文件过滤  保存文件格式
         * <p>
         * 2.新建文件
         * 调用 JFileChooser 方法
         * 改变文件的状态
         * <p>
         * 3.打开文件
         * 根据文件的状态进行过滤
         * 读取disk
         * 然后判断该文件位置的可行性
         */


        public void saveFile() {
            JFileChooser fileChooser1 = new JFileChooser();
            fileChooser1.setFileSelectionMode(JFileChooser.CANCEL_OPTION);

            JpgFilter jpg1 = new JpgFilter();
            BmpFilter bmp1 = new BmpFilter();
            PngFilter png1 = new PngFilter();
            GifFilter gif1 = new GifFilter();

            fileChooser1.addChoosableFileFilter(jpg1);
            fileChooser1.addChoosableFileFilter(bmp1);
            fileChooser1.addChoosableFileFilter(png1);
            fileChooser1.addChoosableFileFilter(gif1);

            fileChooser1.setFileFilter(fileChooser1.getFileFilter());

            int result = fileChooser1.showSaveDialog(MyFrame.this);
            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }
            File fileName = fileChooser1.getSelectedFile();

            if (!fileName.getName().endsWith(fileChooser1.getFileFilter().getDescription())) {
                String t = fileName.getPath() + fileChooser1.getFileFilter().getDescription();
                fileName = new File(t);
            }
            fileName.canWrite();
            if (fileName == null || fileName.getName().equals("")) {
                JOptionPane.showMessageDialog(fileChooser1, "无效的文件名", "无效的文件名", JOptionPane.ERROR_MESSAGE);
            }

            BufferedImage image = createImage(drawingArea);
            try {
                ImageIO.write(image, "png", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void openFile() {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.CANCEL_OPTION);
            fileChooser.setBackground(Color.black);
            JpgFilter jpg = new JpgFilter();
            BmpFilter bmp = new BmpFilter();
            PngFilter png = new PngFilter();
            GifFilter gif = new GifFilter();

            fileChooser.addChoosableFileFilter(jpg);
            fileChooser.addChoosableFileFilter(bmp);
            fileChooser.addChoosableFileFilter(png);
            fileChooser.addChoosableFileFilter(gif);
            fileChooser.setFileFilter(fileChooser.getFileFilter());
            int result = fileChooser.showOpenDialog(MyFrame.this);

            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File fileName = fileChooser.getSelectedFile();
            if (!fileName.getName().endsWith(fileChooser.getFileFilter().getDescription())) {
                JOptionPane.showMessageDialog(MyFrame.this, "文件格式错误！");
                return;
            }
            fileName.canRead();

            if (fileName == null || fileName.getName().equals("")) {
                JOptionPane.showMessageDialog(fileChooser, "无效的文件名", "无效的文件名", JOptionPane.ERROR_MESSAGE);
            }

            BufferedImage image;

            try {
                index = 0;
                currentChoice = 0;
                image = ImageIO.read(fileName);
                drawingArea.createNewGraphics();
                itemList[index].image = image;
                itemList[index].board = drawingArea;
                drawingArea.repaint();
                index++;
                currentChoice = 3;
                drawingArea.createNewGraphics();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }

        public void newFile() {
            index = 0;
            currentChoice = 3;
            color = Color.black;
            stroke = 1;
            drawingArea.createNewGraphics();
            repaint();
        }


        public BufferedImage createImage(DrawPanel panel) {
            int width = MyFrame.this.getWidth();
            int height = MyFrame.this.getHeight();
            BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g2D = (Graphics2D) panelImage.createGraphics();

            g2D.setColor(Color.WHITE);
            g2D.fillRect(0, 0, width, height);
            g2D.translate(0, 0);
            panel.paint(g2D);
            g2D.dispose();
            return panelImage;
        }


        //创建  文件过滤
        //FileFilter


        class JpgFilter extends FileFilter {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                return f.getName().endsWith(".jpg");
            }

            @Override
            public String getDescription() {
                return ".jpg";
            }

        }

        class BmpFilter extends FileFilter {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                return f.getName().endsWith(".bmp");
            }

            @Override
            public String getDescription() {
                return ".bmp";
            }
        }

        class GifFilter extends FileFilter {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                return f.getName().endsWith(".gif");
            }

            @Override
            public String getDescription() {
                return ".gif";
            }

        }

        class PngFilter extends FileFilter {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                return f.getName().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return ".png";
            }

        }

    }


    /**
     * 工具栏：
     * 思路
     * 调用 JButton  这个方法去实现这个工具栏单元
     * JToolBar   工具栏的创建方法
     * JComboBox   创建一个下拉列表框
     * Checkbox     下拉列表框的内容
     * 各个 工具栏单元的监听
     * <p>
     * 实现：
     * 调用鼠标监听时间
     */


    public class MyToolbar extends MyFrame {
        private JButton[] btn_paint;
        private JComboBox<String> jfont;
        private JComboBox<String> jfont_size;
        private JToolBar toolbar;
        private Checkbox btn_blod;
        private Checkbox btn_italic;
        private String tipText[] = {"保存", "清空", "撤销", "铅笔", "直线", "空心矩形", "填充矩形", "空心椭圆", "填充椭圆", "空心圆形", "填充圆形",
                "圆角矩形", "填充", "三角形", "五边形", "裁剪", "橡皮擦", "刷子", "文本", "字体粗细", "函数"};
        private String font[] = {"宋体", "隶书", "华文彩云", "仿宋", "华文行楷", "方正舒体"};
        private String fontSize[] = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36",
                "48", "72"};

        public MyToolbar() {
            addToorbar();
        }

        public void addToorbar() {
            btn_paint = new JButton[tipText.length];// 定义指定个数的按钮
            toolbar = new JToolBar("工具栏");// 实例化一个水平的工具标签

            toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolbar.setBackground(new Color(195, 195, 195));
            btn_blod = new Checkbox("粗体");
            btn_italic = new Checkbox("斜体");
            btn_blod.setBackground(new Color(195, 195, 195));
            btn_italic.setBackground(new Color(195, 195, 195));
            btn_blod.setPreferredSize(new Dimension(40, 30));
            btn_italic.setPreferredSize(new Dimension(40, 30));

            jfont_size = new JComboBox<String>(fontSize);
            jfont_size.setPreferredSize(new Dimension(50, 30));
            jfont = new JComboBox<String>(font);
            jfont.setPreferredSize(new Dimension(100, 30));

            for (int i = 0; i < tipText.length; i++) {
                btn_paint[i] = new JButton(tipText[i]);
                btn_paint[i].setBackground(Color.WHITE);
                toolbar.add(btn_paint[i]);
            }
            toolbar.setFloatable(true);
            toolbar.add(btn_italic);
            toolbar.add(btn_blod);
            for (int i = 2; i < tipText.length; i++) {
                btn_paint[i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        for (int j = 0; j < tipText.length; j++) {
                            if (e.getSource() == btn_paint[j]) {
                                currentChoice = j;
                                drawingArea.createNewGraphics();
                                repaint();
                            }
                        }

                    }
                });
            }

            btn_paint[0].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.saveFile();
                    saved = 1;

                }
            });
            btn_paint[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.newFile();
                }
            });
            btn_paint[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingArea.undo();
                }
            });
            btn_italic.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    italic = Font.ITALIC;
                }
            });
            btn_blod.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    blodtype = Font.BOLD;
                }
            });
            toolbar.add(jfont_size);
            jfont_size.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    fSize = Integer.parseInt(fontSize[jfont_size.getSelectedIndex()]);
                }
            });
            toolbar.add(jfont);
            jfont.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    fontName = font[jfont.getSelectedIndex()];
                }
            });
            MyFrame.this.add(toolbar, BorderLayout.NORTH);
        }

    }


    /**
     * 画板：
     * 思路：
     * 实现对鼠标的监听
     * 调用各个画图  功能的类
     */


    class DrawPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public DrawPanel() {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            this.setBackground(Color.white);
            this.addMouseListener(new mouseAction());
            this.addMouseMotionListener(new MouseMOtion());
        }


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int j = 0;
            while (j <= index) {
                draw(g2d, itemList[j]);
                j++;
            }
        }

        void draw(Graphics2D g2d, home.work.lut.entity.Shape shape) {
            shape.draw(g2d);
        }

        public void undo() {
            index--;
            if (index >= 0) {
                if (currentChoice == 3 || currentChoice == 16 || currentChoice == 17) {
                    index -= itemList[index].length;
                } else {
                    index--;
                }
                drawingArea.repaint();
            }
            index++;
            drawingArea.createNewGraphics();
        }

        public void createNewGraphics() {
            if (currentChoice == 16) {
                try {
                    String url = "cursor.png";
                    ImageIcon imageIcon = new ImageIcon("cursor.png");
                    Toolkit tk = Toolkit.getDefaultToolkit();
                    Image image = imageIcon.getImage();
                    Cursor cursor = tk.createCustomCursor(image, new Point(10, 10), "norm");
                    drawingArea.setCursor(cursor);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "自定义光标异常");
                }

            } else if (currentChoice == 18) {
                drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            } else {
                drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

            switch (currentChoice) {
                case 0:
                    itemList[index] = new Images();
                    break;
                case 3:
                    itemList[index] = new Pencil();
                    break;
                case 4:
                    itemList[index] = new Line();
                    break;
                case 5:
                    itemList[index] = new Rectangle();
                    break;
                case 6:
                    itemList[index] = new FillRect();
                    break;
                case 7:
                    itemList[index] = new Oval();
                    break;
                case 8:
                    itemList[index] = new FillOval();
                    break;
                case 9:
                    itemList[index] = new Circle();
                    break;
                case 10:
                    itemList[index] = new FillCircle();
                    break;
                case 11:
                    itemList[index] = new RoundRect();
                    break;
                case 12:
                    itemList[index] = new Fill();
                    break;
                case 13:
                    itemList[index] = new Triangle();
                    break;
                case 14:
                    itemList[index] = new Pentagon();
                    break;
                case 15:
                    itemList[index] = new CutTool();
                    break;
                case 16:
                    itemList[index] = new Rubber();
                    break;
                case 17:
                    itemList[index] = new Brush();
                    break;
                case 18:
                    itemList[index] = new Component();
                case 19:
                    itemList[index] = new Text();

                    String input;
                    input = JOptionPane.showInputDialog("请输入文字");
                    itemList[index].s = input;
                    itemList[index].fontSize = fSize;
                    itemList[index].fontName = fontName;
                    itemList[index].italic = italic;
                    itemList[index].blodtype = blodtype;
                    break;
            }
            itemList[index].color = color;
            itemList[index].width = stroke;
        }

        class mouseAction extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]");
                itemList[index].x1 = itemList[index].x2 = e.getX();
                itemList[index].y1 = itemList[index].y2 = e.getY();
                if (currentChoice == 3 || currentChoice == 16 || currentChoice == 17) {
                    lengthCount = 0;
                    itemList[index].x1 = itemList[index].x2 = e.getX();
                    itemList[index].y1 = itemList[index].y2 = e.getY();
                    index++;
                    lengthCount++;
                    createNewGraphics();
                }
            }

            public void mouseReleased(MouseEvent e) {
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]");

                if (currentChoice == 3 || currentChoice == 16 || currentChoice == 17) {
                    itemList[index].x1 = e.getX();
                    itemList[index].y1 = e.getY();
                    lengthCount++;
                    itemList[index].length = lengthCount;
                }
                itemList[index].x2 = e.getX();
                itemList[index].y2 = e.getY();
                repaint();
                index++;
                createNewGraphics();

            }

            public void mouseEntered(MouseEvent e) {
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]");

            }

            public void mouseExited(MouseEvent e) {
                statusBar.setText("坐标：");

            }
        }

        class MouseMOtion extends MouseMotionAdapter {
            public void mouseDragged(MouseEvent e) {
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]");

                if (currentChoice == 3 || currentChoice == 16 || currentChoice == 17) {
                    itemList[index - 1].x1 = itemList[index].x2 = itemList[index].x1 = e.getX();
                    itemList[index - 1].y1 = itemList[index].y2 = itemList[index].y1 = e.getY();
                    index++;
                    lengthCount++;
                    createNewGraphics();
                } else {
                    itemList[index].x2 = e.getX();
                    itemList[index].y2 = e.getY();
                }
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]");
                Area.setText("面积:[" + home.work.lut.entity.Shape.mianji + "]");
            }
        }
    }


    class Fill extends Shape {
        private static final long serialVersionUID = 1L;

        public Fill() {
        }

        public void draw(Graphics2D g) {
            g.setStroke(new BasicStroke(width));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.fillArc(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2), 90, 360);
        }
    }
}

