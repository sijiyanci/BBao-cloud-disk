package test;

import server.gui.tools.FileMessage;
import server.gui.tools.ServerJProgressBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JListTest2 {
    public static void main(String[] args) {
        ArrayList<FileMessage> fileMessages = new ArrayList<>();
        DefaultListModel defaultListModel = new DefaultListModel();
        for (int i = 0;i < 2;i++){
            FileMessage fileMessage = new FileMessage();
            fileMessage.setFileName("xixi.txt");
            fileMessage.setFilePath("F:\\Java");
            fileMessage.setRoom("30MB");
            fileMessage.setTime(new Date(System.currentTimeMillis()));
            fileMessage.setUserName("亦散");
            defaultListModel.addElement(fileMessage);
        }
        JList jList = new JList(defaultListModel);
        jList.setFixedCellHeight(130);
        jList.setFixedCellWidth(680);
        jList.setCellRenderer(new MyListCellRenderer());
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        JButton addBtn = new JButton("增加");
        JButton deleteBtn = new JButton("删除");
        jFrame.getContentPane().setLayout(new BorderLayout());
        jFrame.add(jList,BorderLayout.NORTH);
        jFrame.add(jPanel,BorderLayout.SOUTH);
        jPanel.add(addBtn);
        jPanel.add(deleteBtn);

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultListModel.remove(0);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileMessage fileMessage = new FileMessage();
                fileMessage.setFileName("haha.txt");
                fileMessage.setFilePath("F:\\Java");
                fileMessage.setRoom("60MB");
                fileMessage.setTime(new Date(System.currentTimeMillis()));
                fileMessage.setUserName("亦散");
                defaultListModel.addElement(fileMessage);
            }
        });
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(680,1000);
    }

    static class MyListCellRenderer extends JPanel implements ListCellRenderer {

        private JLabel fileNameLabel;
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
        private FileMessage fileMessage;



        public MyListCellRenderer() {
            setOpaque(true);
            setLayout(null);
            setBounds(0, 0, 680, 130);


            font = new Font("Microsoft JhengHei Light", Font.PLAIN, 18);

            fileNameLabel = new JLabel("文件名");
            roomLabel = new JLabel("占空间");
            filePathLabel = new JLabel("文件目录");
            userNameLabel = new JLabel("用户名");
            timeLabel = new JLabel("上传时间");
            fileName = new JTextField("");
            room = new JTextField("");
            filePath = new JTextField("");
            userName = new JTextField("");
            time = new JTextField("");
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

        public Component getListCellRendererComponent(JList list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            fileMessage = (FileMessage)value;
            fileName.setText(fileMessage.getFileName());
            room.setText(fileMessage.getRoom());
            filePath.setText(fileMessage.getFilePath());
            userName.setText(fileMessage.getUserName());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = dateFormat.format(fileMessage.getTime());
            time.setText(dateString);

            if (isSelected) {
                setBackground(Color.PINK);
                setForeground(Color.white);
            } else {
                setBackground(Color.white);
                setForeground(Color.black);
            }

            return this;
        }
    }
}
