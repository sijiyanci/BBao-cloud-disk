package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MyListCellRenderer extends JLabel implements ListCellRenderer {

    public MyListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        MyFile item=(MyFile)value;
        ImageIcon icon=new ImageIcon();
        if(item.isDirectory())
            icon=new ImageIcon("./public/result/dir.jpg");
        else if(item.isFile())
            icon=new ImageIcon("./public/result/file.jpg");
        setText(value.toString());
        setIcon(icon);
        if (isSelected) {
            setBackground(Color.BLUE);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }

        return this;
    }
}
