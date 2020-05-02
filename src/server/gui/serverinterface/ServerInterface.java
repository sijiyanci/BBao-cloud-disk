package server.gui.serverinterface;


import server.gui.tools.SpacedTabbedPaneUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//服务端界面
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
                System.exit(0);
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
        jTabbedPane.add("首页",new FirstPage());
        jTabbedPane.add("用户文件",new JScrollPane(new TreePage(projectFile)));
        jTabbedPane.add("用户操作记录",new OperatePage());


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


}
