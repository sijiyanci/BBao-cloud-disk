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
          jf.getContentPane().setLayout(new GridBagLayout());
          JTextField field=new JTextField("value.toString()");
          JButton button=new JButton();
          ImageIcon icon=new ImageIcon();
          icon=new ImageIcon("./public/result/dir.jpg");
          //            button.setIcon(icon);
//              setSize(300,20);
//              add(button,BorderLayout.NORTH);
//              add(field,BorderLayout.SOUTH);

          GridBagConstraints gbc=new GridBagConstraints();
          gbc.gridx=0;
          gbc.gridy=0;
          gbc.gridwidth=1;
          gbc.gridwidth=1;
          gbc.weightx=0.5;
          gbc.weighty=0.7;
          gbc.fill=GridBagConstraints.BOTH;
          gbc.anchor=GridBagConstraints.CENTER;
          jf.add(button,gbc);

          gbc=new GridBagConstraints();
          gbc.gridx=1;
          gbc.gridy=1;
          gbc.gridwidth=1;
          gbc.gridwidth=1;
          gbc.weightx=0.5;
          gbc.weighty=0.3;
          gbc.fill=GridBagConstraints.BOTH;
          gbc.anchor=GridBagConstraints.CENTER;
          jf.add(field,gbc);
          jf.setVisible(true);
          jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
