package home.work.lut.entity;


import home.work.lut.biz.Rectangle;
import home.work.lut.biz.*;
import home.work.lut.dao.ColorPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFrameONE extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int saved = 0;
    public static Color c = Color.black;
    public static String fontName = new String(" 宋体 ");
    public static int index = 0;
    public static Shape[] itemList = new Shape[5000];
    public static int stroke = 1;
    public static Color color = Color.black;
    public static int currentChoice = 3;
    private static int fSize = 16;
    private static int blodtype = Font.PLAIN;
    private static int italic = Font.PLAIN;
    public Graphics2D g;
    public int lengthCount;
    MyMenu menu;
    MyToolbar myToolbar;
    ColorPanel colorPanel;
    int length;
    private DrawPanel drawingArea;
    private JLabel statusBar;

    public MyFrameONE(String s) {
        init(s);
        setVisible(true);
    }

    public MyFrameONE() {
    }

    public void init(String s) {
        this.setTitle(s);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        menu = new MyMenu();
        myToolbar = new MyToolbar();
        colorPanel = new ColorPanel();
        add(colorPanel, BorderLayout.EAST);
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
        this.add(statusBar, BorderLayout.SOUTH);
        statusBar.setText("坐标");
        statusBar.setOpaque(true);// 设置该组件为透明
        statusBar.setBackground(new Color(195, 195, 195));
        drawingArea.createNewGraphics();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (saved == 0) {
                    int n = JOptionPane.showConfirmDialog(null, "您还没保存，确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION);
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
            Graphics2D g2d = (Graphics2D) g; // 定义画板
            int j = 0;

            while (j <= index) {
                draw(g2d, itemList[j]);
                j++;

            }
        }

        void draw(Graphics2D g2d, Shape shape) {
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
                    itemList[index] = new FillRoundRect();
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
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]像素");// 设置状态提示
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
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]像素");

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
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]像素");
            }

            public void mouseExited(MouseEvent e) {
                statusBar.setText("坐标：");
            }
        }

        class MouseMOtion extends MouseMotionAdapter {
            public void mouseDragged(MouseEvent e) {
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]像素");

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
                statusBar.setText("坐标:[" + e.getX() + "," + e.getY() + "]像素");
            }
        }
    }

    class MyMenu {
        private JMenuBar jMenuBar;
        private JMenuItem file_item_new, file_item_open, file_item_save, file_item_exit;
        private JMenuItem set_item_color, set_item_undo;
        private JMenuItem[] stroke_item;
        private JMenuItem help_item_info;
        private JMenuItem help_item_use;

        private JMenu file_menu, set_menu, help_menu, stroke_menu;
        private String strokes[] = {"stroke1.png", "stroke2.png", "stroke3.png",
                "stroke4.png"};

        public MyMenu() {
            addMenu();
        }

        public void addMenu() {
            jMenuBar = new JMenuBar();
            stroke_item = new JMenuItem[strokes.length];
            // 实例化菜单对象
            file_menu = new JMenu("文件");
            set_menu = new JMenu("设置");
            help_menu = new JMenu("帮助");
            stroke_menu = new JMenu("粗细");
            // 实例化菜单项,并通过ImageIcon对象添加图片
            file_item_new = new JMenuItem("新建", new ImageIcon("new.png"));
            file_item_open = new JMenuItem("打开", new ImageIcon("open.png"));
            file_item_save = new JMenuItem("保存", new ImageIcon("save.png"));
            file_item_exit = new JMenuItem("退出", new ImageIcon("exit.png"));
            set_item_color = new JMenuItem("颜色", new ImageIcon("color.png"));
            set_item_undo = new JMenuItem("撤销", new ImageIcon("undo.png"));
            help_item_use = new JMenuItem("使用手册");
            help_item_info = new JMenuItem("关于画图");
            for (int i = 0; i < 4; i++) {
                stroke_item[i] = new JMenuItem("", new ImageIcon(strokes[i]));
                stroke_menu.add(stroke_item[i]);
            }
            help_item_info.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,
                            "" + "关于画图\n" + "****该软件由苟慜开发完成****\n" + "****班级：软件工程二班     *****\n"
                                    + "****学号：1816270248  *****\n" + "****邮箱：1924480487@qq.com\n",
                            "关于画图", JOptionPane.PLAIN_MESSAGE);

                }
            });
            help_item_use.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "" + "##################\r\n" + "#画图软件使用说明书#\r\n"
                            + "####################\r\n" + "1.本软件可以实现以下功能：\r\n" + "（1）在画布上绘制直线、矩形、椭圆等图形\r\n"
                            + "（2）设置画笔的颜色和粗细\r\n" + "（3）绘制填充图形\r\n" + "（4）依据鼠标轨迹绘制曲线\r\n" + "（5）橡皮擦、保存图片\r\n"
                            + "2.本软件主要分为四个模块：菜单、工具栏、调色板、和画布\r\n" + "（1）菜单栏的文件子菜单包括打开、新建、保存图片以及退出程序，设置有快捷键，方便操作，\r\n"
                            + "	菜单栏的设置子菜单包括设置画笔的粗细和颜色；\r\n" + "（2）工具栏主要包括保存文件、清空画板、撤回操作、图形绘制和文字的绘制；\r\n"
                            + "（3）调色板位于界面的左侧，用于设置画笔的颜色，可以使用已设定的颜色，也可以自己选择系统提供的颜色；\r\n"
                            + "（4）画布用于图形绘制，使用鼠标选中要绘制的图形即可进行绘制。", "使用说明", JOptionPane.PLAIN_MESSAGE);
                }
            });
            help_menu.add(help_item_use);
            help_menu.add(help_item_info);
            // 设置快捷键
            file_item_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
            file_item_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
            file_item_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
            file_item_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
            set_item_undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
            // 添加菜单项到菜单
            file_menu.add(file_item_new);
            file_menu.add(file_item_open);
            file_menu.add(file_item_save);
            file_menu.add(file_item_exit);
            set_menu.add(set_item_color);
            set_menu.add(set_item_undo);
            set_menu.add(stroke_menu);
            // 添加菜单到菜单条
            jMenuBar.add(file_menu);
            jMenuBar.add(set_menu);
            jMenuBar.add(help_menu);
            // 添加菜单条
            setJMenuBar(jMenuBar);
            // 给文件菜单设置监听
            file_item_new.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.newFile();
                }
            });
            file_item_save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 保存文件，并将标志符saved设置为1
                    menu.saveFile();
                    saved = 1;
                }
            });
            file_item_open.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 打开文件，并将标志符saved设置为0
                    menu.openFile();
                    saved = 0;
                }
            });
            file_item_exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 如果文件已经保存就直接退出，若果文件没有保存，提示用户选择是否退出

                    if (saved == 1) {
                        System.exit(0);
                    } else {
                        int n = JOptionPane.showConfirmDialog(null, "您还没保存，确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION);
                        if (n == 0) {
                            System.exit(0);
                        }
                    }
                }
            });
            // 给设置菜单添加监听
            set_item_color.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 设置粗细
                    ColorPanel.chooseColor();

                }
            });
            set_item_undo.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 撤销
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

        // 保存图形文件
        public void saveFile() {
            // 文件选择器
            JFileChooser fileChooser = new JFileChooser();
            // 设置文件显示类型为仅显示文件
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // 文件过滤器
            JpgFilter jpg = new JpgFilter();
            BmpFilter bmp = new BmpFilter();
            PngFilter png = new PngFilter();
            GifFilter gif = new GifFilter();
            // 向用户可选择的文件过滤器列表添加一个过滤器。
            fileChooser.addChoosableFileFilter(jpg);
            fileChooser.addChoosableFileFilter(bmp);
            fileChooser.addChoosableFileFilter(png);
            fileChooser.addChoosableFileFilter(gif);
            // 返回当前的文本过滤器，并设置成当前的选择
            fileChooser.setFileFilter(fileChooser.getFileFilter());
            // 弹出一个 "Save File" 文件选择器对话框
            int result = fileChooser.showSaveDialog(MyFrameONE.this);
            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }
            File fileName = fileChooser.getSelectedFile();

            if (!fileName.getName().endsWith(fileChooser.getFileFilter().getDescription())) {
                String t = fileName.getPath() + fileChooser.getFileFilter().getDescription();
                fileName = new File(t);
            }
            fileName.canWrite();
            if (fileName == null || fileName.getName().equals("")) {
                JOptionPane.showMessageDialog(fileChooser, "无效的文件名", "无效的文件名", JOptionPane.ERROR_MESSAGE);
            }

            BufferedImage image = createImage(drawingArea);
            try {
                ImageIO.write(image, "png", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 打开文件
        public void openFile() {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            PngFilter png = new PngFilter();
            JpgFilter jpg = new JpgFilter();
            BmpFilter bmp = new BmpFilter();
            GifFilter gif = new GifFilter();
            fileChooser.addChoosableFileFilter(jpg);
            fileChooser.addChoosableFileFilter(bmp);
            fileChooser.addChoosableFileFilter(png);
            fileChooser.addChoosableFileFilter(gif);
            fileChooser.setFileFilter(fileChooser.getFileFilter());
            // 弹出一个 "Open File" 文件选择器对话框
            int result = fileChooser.showOpenDialog(MyFrameONE.this);
            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }
            // 得到选择文件的名字
            File fileName = fileChooser.getSelectedFile();
            if (!fileName.getName().endsWith(fileChooser.getFileFilter().getDescription())) {
                JOptionPane.showMessageDialog(MyFrameONE.this, "文件格式错误！");
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

            int width = MyFrameONE.this.getWidth();
            int height = MyFrameONE.this.getHeight();
            BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2D = (Graphics2D) panelImage.createGraphics();

            g2D.setColor(Color.WHITE);
            g2D.fillRect(0, 0, width, height);
            g2D.translate(0, 0);
            panel.paint(g2D);
            g2D.dispose();
            return panelImage;
        }

        // 文件过滤
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

    class MyToolbar {
        private ImageIcon[] icon; // 存放按钮的图片
        private JButton[] btn_paint;// 定义各种绘图的按钮
        private JComboBox<String> jfont;
        private JComboBox<String> jfont_size;
        private JToolBar toolbar; // 定义按钮面板
        private Checkbox btn_blod;// 粗体
        private Checkbox btn_italic;// 斜体
        // 将图片资源的相对路径存放于数组中，方便使用
        private String images[] = {"save.png", "refresh.png", "undo.png", "pencil.png",
                "line.png", "rectangle.png", "rectangle3.png", "oval.png",
                "oval2.png", "circle.png", "fillcircle.png", "rectangle2.png",
                "rectangle4.png", "triangle.png", "pentagon.png", "hexagon.png",
                "eraser.png", "brush.png", "font.png",};
        private String tipText[] = {"保存", "清空", "撤销", "铅笔", "直线", "空心矩形", "填充矩形", "空心椭圆", "填充椭圆", "空心圆形", "填充圆形",
                "空心圆角矩形", "填充圆角矩形", "三角形", "五边形", "六边形", "橡皮擦", "刷子", "文本", "粗细"};
        private String font[] = {"宋体", "隶书", "华文彩云", "仿宋_GB2312", "华文行楷", "方正舒体"};
        private String fontSize[] = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36",
                "48", "72"};

        public MyToolbar() {
            addToorbar();
        }

        public void addToorbar() {
            btn_paint = new JButton[images.length];// 定义指定个数的按钮
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
            icon = new ImageIcon[images.length];
            // 设置按钮图标以及图片
            for (int i = 0; i < images.length; i++) {
                btn_paint[i] = new JButton();
                icon[i] = new ImageIcon(images[i]);
                btn_paint[i].setIcon(icon[i]);
                btn_paint[i].setToolTipText(tipText[i]);
                btn_paint[i].setPreferredSize(new Dimension(28, 28));// 设置图标大小
                btn_paint[i].setBackground(Color.WHITE);
                toolbar.add(btn_paint[i]);
            }
            toolbar.setFloatable(true);// 可以拖动
            toolbar.add(btn_italic);
            toolbar.add(btn_blod);
            for (int i = 2; i < images.length; i++) {
                btn_paint[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < images.length; j++) {
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

            // 添加监听
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
            MyFrameONE.this.add(toolbar, BorderLayout.NORTH);

        }
    }

}

