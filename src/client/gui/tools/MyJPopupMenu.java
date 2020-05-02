package client.gui.tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJPopupMenu extends JPopupMenu {
    private MyJListListener mll;
    private ActionListener action=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem source=(JMenuItem)e.getSource();

            new MyRename(mll).setVisible(true);
        }
    };

    public MyJPopupMenu(String[] strlist){
        updateMenu(strlist);
    }
    public MyJPopupMenu(){}

    public void setMll(MyJListListener mll){
        this.mll=mll;
    }
    public void updateMenu(String[] strlist){
        for(String i:strlist){
            JMenuItem temp=new JMenuItem(i);
            temp.addActionListener(action);
            add(temp);

        }

    }


}
