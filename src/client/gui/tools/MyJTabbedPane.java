package client.gui.tools;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyJTabbedPane extends JPanel {
    //private ArrayList<JPanel> cards;
    private JTabbedPane tab;
    public MyJTabbedPane(){
        //cards=new ArrayList<JPanel>();
        tab=new JTabbedPane();
        //setLayout(new GridLayout(1, 1));

        setLayout(new BorderLayout());
        add(tab);
    }
    public void addCard(JPanel newone,String name){
        tab.addTab(name,newone);
    }

}
