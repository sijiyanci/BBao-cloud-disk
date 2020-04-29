package server.gui.serverinterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class ServerInterface {
    private static final long serialVersionUID = -6256528270698337163L;
    JFrame jFrame = new JFrame();
    Container contentPane; //容器，从jFrame获得
    JTabbedPane jTabbedPane; //选项卡容器
    JButton buttonClose;//关闭按钮

    File projectFile = new File(".");


    public static void main(String[] args) {
        ServerInterface serverGUI = new ServerInterface();

    }

    //创建窗体的内部
    public ServerInterface(){
        jFrame.setUndecorated(true);
        jFrame.setResizable(false);
        jFrame.setBounds(500,50,700,550);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(3);
        contentPane = jFrame.getContentPane();
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(jTabbedPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.gray);

        //关闭按钮
        ImageIcon closeIcon = new ImageIcon("src/images/close_536px_1.png");
        buttonClose = new JButton(closeIcon);
        buttonClose.setBounds(655,5,40,30);
        buttonClose.setBackground(Color.LIGHT_GRAY);
        buttonClose.setForeground(Color.WHITE);
        buttonClose.setBorder(null);
        buttonClose.setPreferredSize(new Dimension(40,30));
        buttonClose.setFocusPainted(false);
        contentPane.add(buttonClose);
        jFrame.setVisible(true);
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
            }
        });

        //
        //上面标题
        JLabel labelTitle = new JLabel("服务器");
        labelTitle.setForeground(Color.WHITE);
        labelTitle.setBackground(Color.WHITE);
        labelTitle.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,35));
        labelTitle.setBounds(10,20,357,80);
        contentPane.add(labelTitle);

        designPanel();
    }

    //设计选项卡
    public void designPanel(){

        //将面板加入jTabbedPanel中
        jTabbedPane.add("首页",FirstPage());
        jTabbedPane.add("用户文件",new JScrollPane(new JTree(addNodes(projectFile))));
        jTabbedPane.add("用户操作记录",operate());


        Font font = new Font("Microsoft JhengHei Light",Font.PLAIN,25);

        jTabbedPane.setFont(font);

        //设置标签页的背景色
        jTabbedPane.setBackground(Color.LIGHT_GRAY);
        //设置标签页的前景色
        jTabbedPane.setForeground(Color.WHITE);
        //设置边框
        jTabbedPane.setBorder(null);
        //设置UI
        jTabbedPane.setUI(new SpacedTabbedPaneUI());
        jTabbedPane.setBounds(10,100,680,400);


    }

    public JPanel FirstPage(){
        JPanel jPanel = new JPanel();
        JLabel userNumLabel = new JLabel("用户总数:");
        JLabel roomLabel = new JLabel("磁盘总空间:");
        JLabel usedRoomLabel = new JLabel("已用空间:");
        JLabel portLabel = new JLabel("端口号:");
        JLabel pathLabel = new JLabel("服务端文件目录:");
        JTextField userNum = new JTextField("4");
        JTextField room = new JTextField();
        JTextField usedRoom = new JTextField("2480");
        JTextField port = new JTextField();
        JTextField path = new JTextField("F:/BBao-cloud-disk");
        JButton EditButton = new JButton("编辑");
        JButton YesButton = new JButton("确定");
        Font font = new Font("Microsoft JhengHei Light",Font.PLAIN,20);


        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.setBounds(10,150,680,500);
        jPanel.setLayout(null);
        jPanel.setBackground(Color.WHITE);

        //用户数量
        userNumLabel.setBounds(20,30,200,32);
        userNumLabel.setFont(font);
        jPanel.add(userNumLabel);

        userNum.setBounds(180,30,219,32);
        userNum.setFont(font);
        userNum.setEditable(false);
        userNum.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.add(userNum);

        //空间总数
        roomLabel.setBounds(20,80,200,32);
        roomLabel.setFont(font);
        jPanel.add(roomLabel);

        room.setBounds(180,80,219,32);
        room.setFont(font);
        room.setBorder(new EmptyBorder(5,5,5,5));
        room.setBackground(Color.LIGHT_GRAY);
        jPanel.add(room);

        //已用空间
        usedRoomLabel.setBounds(20,130,200,30);
        usedRoomLabel.setFont(font);
        jPanel.add(usedRoomLabel);

        usedRoom.setBounds(180,130,219,32);
        usedRoom.setFont(font);
        usedRoom.setBorder(new EmptyBorder(5,5,5,5));
        usedRoom.setEditable(false);
        jPanel.add(usedRoom);

        //端口号
        portLabel.setBounds(20,180,200,30);
        portLabel.setFont(font);
        jPanel.add(portLabel);

        port.setBounds(180,180,219,32);
        port.setFont(font);
        port.setBorder(new EmptyBorder(5,5,5,5));
        port.setBackground(Color.LIGHT_GRAY);
        jPanel.add(port);

        //服务端文件目录
        pathLabel.setBounds(20,230,200,30);
        pathLabel.setFont(font);
        jPanel.add(pathLabel);

        path.setBounds(180,230,219,32);
        path.setFont(font);
        path.setEditable(false);
        path.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.add(path);

        //编辑按钮
        EditButton.setBounds(470,300,80,40);
        EditButton.setBackground(Color.LIGHT_GRAY);
        EditButton.setForeground(Color.WHITE);
        EditButton.setBorder(null);
        EditButton.setFont(font);
        EditButton.setPreferredSize(new Dimension(100,40));
        EditButton.setFocusPainted(false);
        jPanel.add(EditButton);

        EditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("编辑设置信息");
            }
        });

        //确定按钮
        YesButton.setBounds(560,300,80,40);
        YesButton.setBackground(Color.LIGHT_GRAY);
        YesButton.setForeground(Color.WHITE);
        YesButton.setBorder(null);
        YesButton.setFont(font);
        YesButton.setPreferredSize(new Dimension(100,40));
        YesButton.setFocusPainted(false);
        jPanel.add(YesButton);

        YesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("确定更改");
            }
        });


        return jPanel;
    }


    DefaultMutableTreeNode addNodes(File dir) {

        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(dir.getName());

        File[] tmp = dir.listFiles();
        Vector<File> ol = new Vector<File>();
        ol.addAll(Arrays.asList(tmp));
        Collections.sort(ol, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int result = o1.getName().compareTo(o2.getName());
                if (o1.isDirectory() && o2.isFile()) {
                    result = -1;
                } else if (o2.isDirectory() && o1.isFile()) {
                    result = 1;
                }
                return result;
            }
        });
        for (int i = 0; i < ol.size(); i++) {
            File file = ol.elementAt(i);
            DefaultMutableTreeNode node=new DefaultMutableTreeNode(file.getName());
            if (file.isDirectory()) {
                node=addNodes(file);
            }
            curDir.add(node);
        }
        return curDir;
    }

    public JScrollPane operate(){
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(makeList());

        return jScrollPane;
    }

    public JList<JPanel> makeList(){
        Vector<JPanel> vector = new Vector<>();
        JPanel jPanel;
        int i = 0;
        int y = -100;
        int height = 100;
        while (i++ <= 5){
            jPanel = new JPanel();
            jPanel.setBorder(new EmptyBorder(5,5,5,5));
            jPanel.setBounds(0,y + (height+3)*i,680,height);
            jPanel.setLayout(null);
            jPanel.setBackground(Color.pink);

            vector.add(jPanel);

        }
        JList<JPanel> jList = new JList<>(vector);
        return jList;
    }


    class SpacedTabbedPaneUI extends BasicTabbedPaneUI {
        @Override
        protected LayoutManager createLayoutManager() {
            return new BasicTabbedPaneUI.TabbedPaneLayout() {
                @Override
                protected void calculateTabRects(int tabPlacement, int tabCount) {
                    final int spacer = 1; // should be non-negative
                    final int indent = 3;

                    super.calculateTabRects(tabPlacement, tabCount);

                    for (int i = 0; i < rects.length; i++) {
                        rects[i].x += i * spacer + indent;
                    }
                }
            };
        }
    }
}
