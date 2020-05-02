package server.gui.serverinterface;

import server.gui.tools.OperateList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//用户操作卡片
public class OperatePage extends JScrollPane {

    public OperatePage(){
        setBorder(new EmptyBorder(5,5,5,5));
        setBounds(0,0,680,500);
        this.getVerticalScrollBar().setUnitIncrement(20);
        setBackground(Color.white);
        setViewportView(new OperateList());

    }

}
