package server.gui.serverinterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//首页卡片
public class FirstPage extends JPanel{
    private JLabel userNumLabel;
    private JLabel roomLabel;
    private JLabel usedRoomLabel;
    private JLabel portLabel;
    private JLabel pathLabel;
    private JTextField userNum;
    private JTextField room;
    private JTextField usedRoom;
    private JTextField port;
    private JTextField path;
    private JButton editButton;
    private JButton yesButton;
    private Font font;

    public FirstPage(){
        userNumLabel = new JLabel("用户总数:");
        roomLabel = new JLabel("磁盘总空间:");
        usedRoomLabel = new JLabel("已用空间:");
        portLabel = new JLabel("端口号:");
        pathLabel = new JLabel("服务端文件目录:");
        userNum = new JTextField("4");
        room = new JTextField();
        usedRoom = new JTextField("2480");
        port = new JTextField();
        path = new JTextField("F:/BBao-cloud-disk");
        editButton = new JButton("编辑");
        yesButton = new JButton("确定");
        font = new Font("Microsoft JhengHei Light",Font.PLAIN,20);

        setBorder(new EmptyBorder(5,5,5,5));
        setBounds(10,150,680,500);
        setLayout(null);
        setBackground(Color.WHITE);

        //用户数量
        userNumLabel.setBounds(20,30,200,32);
        userNumLabel.setFont(font);
        this.add(userNumLabel);

        userNum.setBounds(180,30,219,32);
        userNum.setFont(font);
        userNum.setEditable(false);
        userNum.setBorder(new EmptyBorder(5,5,5,5));
        this.add(userNum);

        //空间总数
        roomLabel.setBounds(20,80,200,32);
        roomLabel.setFont(font);
        this.add(roomLabel);

        room.setBounds(180,80,219,32);
        room.setFont(font);
        room.setBorder(new EmptyBorder(5,5,5,5));
        room.setBackground(Color.LIGHT_GRAY);
        this.add(room);

        //已用空间
        usedRoomLabel.setBounds(20,130,200,30);
        usedRoomLabel.setFont(font);
        this.add(usedRoomLabel);

        usedRoom.setBounds(180,130,219,32);
        usedRoom.setFont(font);
        usedRoom.setBorder(new EmptyBorder(5,5,5,5));
        usedRoom.setEditable(false);
        this.add(usedRoom);

        //端口号
        portLabel.setBounds(20,180,200,30);
        portLabel.setFont(font);
        this.add(portLabel);

        port.setBounds(180,180,219,32);
        port.setFont(font);
        port.setBorder(new EmptyBorder(5,5,5,5));
        port.setBackground(Color.LIGHT_GRAY);
        this.add(port);

        //服务端文件目录
        pathLabel.setBounds(20,230,200,30);
        pathLabel.setFont(font);
        this.add(pathLabel);

        path.setBounds(180,230,219,32);
        path.setFont(font);
        path.setEditable(false);
        path.setBorder(new EmptyBorder(5,5,5,5));
        this.add(path);

        //编辑按钮
        editButton.setBounds(470,300,80,40);
        editButton.setBackground(Color.LIGHT_GRAY);
        editButton.setForeground(Color.WHITE);
        editButton.setBorder(null);
        editButton.setFont(font);
        editButton.setPreferredSize(new Dimension(100,40));
        editButton.setFocusPainted(false);
        this.add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("编辑设置信息");
            }
        });

        //确定按钮
        yesButton.setBounds(560,300,80,40);
        yesButton.setBackground(Color.LIGHT_GRAY);
        yesButton.setForeground(Color.WHITE);
        yesButton.setBorder(null);
        yesButton.setFont(font);
        yesButton.setPreferredSize(new Dimension(100,40));
        yesButton.setFocusPainted(false);
        this.add(yesButton);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("确定更改");
            }
        });

    }
}
