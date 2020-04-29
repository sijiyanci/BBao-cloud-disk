package client.gui.tools;

import javax.swing.*;

public class MyJPopupMenu extends JPopupMenu {
    public MyJPopupMenu(String[] strlist){
        updateMenu(strlist);
    }
    public MyJPopupMenu(){}
    public void updateMenu(String[] strlist){
        for(String i:strlist){
            add(new JMenuItem(i));
//            JMenuItem temp=new JMenuItem(i);
//            switch (i){
//                case "下载":
//                    ;
//            }
        }

    }

}
