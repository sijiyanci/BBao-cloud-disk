package test;

import server.gui.tools.ServerJProgressBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//一个用户上传或下载面板
public class OneOperate extends JPanel {
    public JLabel fileNameLabel;
    private JLabel roomLabel;
    private JLabel filePathLabel;
    private JLabel userNameLabel;
    private JLabel timeLabel;
    private JTextField fileName;
    private JTextField room;
    private JTextField filePath;
    private JTextField userName;
    private JTextField time;
    private Font font;
    private ServerJProgressBar serverJProgressBar;

    public OneOperate(){


        fileNameLabel = new JLabel("文件名");
        roomLabel = new JLabel("占空间");
        filePathLabel = new JLabel("文件目录");
        userNameLabel = new JLabel("用户名");
        timeLabel = new JLabel("上传时间");
        fileName = new JTextField("xixi.txt");
        room = new JTextField("30MB");
        filePath = new JTextField("F:\\Java");
        userName = new JTextField("亦散");
        time = new JTextField("2020-04-30 21:56:03");
        font = new Font("Microsoft JhengHei Light", Font.PLAIN, 18);
        serverJProgressBar = new ServerJProgressBar();

        //文件名
        fileNameLabel.setBounds(40, 10, 90, 30);
        fileNameLabel.setFont(font);
        this.add(fileNameLabel);

        fileName.setBounds(130, 10, 200, 30);
        fileName.setFont(font);
        fileName.setEditable(false);
        fileName.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(fileName);

        //占空间大小
        roomLabel.setBounds(40, 45, 90, 30);
        roomLabel.setFont(font);
        this.add(roomLabel);

        room.setBounds(130, 45, 200, 30);
        room.setFont(font);
        room.setEditable(false);
        room.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(room);

        //文件目录
        filePathLabel.setBounds(40, 80, 90, 30);
        filePathLabel.setFont(font);
        this.add(filePathLabel);

        filePath.setBounds(130, 80, 200, 30);
        filePath.setFont(font);
        filePath.setEditable(false);
        filePath.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(filePath);

        //进度条
        serverJProgressBar.setBounds(370, 10, 290, 30);
        this.add(serverJProgressBar);
        serverJProgressBar.setVisible(true);
        serverJProgressBar.setRun();

        //用户名
        userNameLabel.setBounds(370, 45, 90, 30);
        userNameLabel.setFont(font);
        this.add(userNameLabel);

        userName.setBounds(460, 45, 200, 30);
        userName.setFont(font);
        userName.setEditable(false);
        userName.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(userName);

        //时间
        timeLabel.setBounds(370, 80, 90, 30);
        timeLabel.setFont(font);
        this.add(timeLabel);

        time.setBounds(460, 80, 200, 30);
        time.setFont(font);
        time.setEditable(false);
        time.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(time);
    }


}
