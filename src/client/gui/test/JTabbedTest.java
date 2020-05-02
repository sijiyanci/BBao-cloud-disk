package client.gui.test;

import client.gui.tools.MyJTabbedPane;

import javax.swing.*;
import java.awt.*;

public class JTabbedTest {
    public static void main(String[] arg){
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        MyJTabbedPane mjp=new MyJTabbedPane();
        mjp.addCard(panel1,"主页");
        mjp.addCard(panel2,"下载");
        JFrame jf=new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,400);
        mjp.setBounds(0,50,500,450);
        jf.add(mjp);

        jf.getContentPane().setLayout(null);
        jf.setVisible(true);
    }

}
