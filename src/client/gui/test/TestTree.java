package client.gui.test;

import client.gui.tools.MyJList;
import client.gui.tools.MyJPopupMenu;
import client.gui.tools.MyJTree;

import javax.swing.*;
import java.awt.*;

public class TestTree {
    public static void main(String[] arg){
        JFrame jf=new JFrame();
        String[] strlist ={"a","b","c"};
        MyJPopupMenu jmenu=new MyJPopupMenu(strlist);
        MyJList ml=new MyJList(jmenu);

        JScrollPane jsp=new JScrollPane(ml);

        MyJTree mt=new MyJTree(".",ml);
        JPanel panel=new JPanel();
        panel.add(mt);

        jf.getContentPane().setLayout(new FlowLayout());
        jf.add(panel);
        jf.add(jsp);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(0,0,500,500);
        jf.setVisible(true);
    }
}
