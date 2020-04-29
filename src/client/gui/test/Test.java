package client.gui.test;

import client.gui.tools.MyJList;
import client.gui.tools.MyJPopupMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] arg){
          JFrame jf=new JFrame();
          String[] strlist ={"a","b","c"};
          MyJPopupMenu jmenu=new MyJPopupMenu(strlist);
          MyJList ml=new MyJList(jmenu);

          ml.updateList(".");
          JScrollPane jsp=new JScrollPane(ml);
          jf.getContentPane().setLayout(new FlowLayout());
          JButton jbtn=new JButton("按键");
          jf.add(jbtn);
          jf.add(new JPanel().add(jsp));
          jf.setBounds(0,0,500,500);
          jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          jf.setVisible(true);
          jbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                      ml.updateList("./src");

                }
          });

//        JScrollPane jsp=new JScrollPane();
//        MyList ml=new MyList();
//        String[] strlist={"asdf","sdf","fsd"};
//        ml.setPumenu(strlist);
//        ml.update(".");
//        jsp.add(ml);
//        jf.setContentPane(jsp);
//        jf.setVisible(true);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setBounds(0,0,300,300);

    }
}
