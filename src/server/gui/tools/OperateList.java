package server.gui.tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OperateList extends JList {
    ArrayList<FileMessage> fileMessages;
    DefaultListModel defaultListModel;

    public OperateList(){
        fileMessages = new ArrayList<>();
        defaultListModel = new DefaultListModel();
        ServerJProgressBar serverJProgressBar;


        for (int i = 0;i < 10;i++){
            FileMessage fileMessage = new FileMessage();
            fileMessage.setFileName("xixi.txt");
            fileMessage.setFilePath("F:\\Java");
            fileMessage.setRoom("30MB");
            fileMessage.setTime(new Date(System.currentTimeMillis()));
            fileMessage.setUserName("亦散");
            //进度条
            serverJProgressBar = new ServerJProgressBar();
            serverJProgressBar.setBounds(370, 10+i*130, 290, 30);
            this.add(serverJProgressBar);
            serverJProgressBar.setVisible(true);
            serverJProgressBar.setRun();

            defaultListModel.addElement(fileMessage);
        }
        this.setModel(defaultListModel);
        this.setFixedCellHeight(130);
        this.setFixedCellWidth(680);

        this.setCellRenderer(new MyListCellRenderer());

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
        private FileMessage fileMessage;
//        private ServerJProgressBar serverJProgressBar;



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

            /*//进度条
            serverJProgressBar = new ServerJProgressBar();
            serverJProgressBar.setBounds(370, 10, 290, 30);
            this.add(serverJProgressBar);
            serverJProgressBar.setVisible(true);
            serverJProgressBar.setRun();*/


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
