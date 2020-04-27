package gui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class MyJList extends JList {
    private DefaultListModel dlm;
    private MyJPopupMenu pumenu;
    public MyJList(MyJPopupMenu pumenu){
        super();

        dlm=new DefaultListModel();
        this.pumenu=pumenu;
        setModel(dlm);
        //设置渲染cell
        setCellRenderer(new MyListCellRenderer());
        //jlist的弹窗点击事件
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setSelectedIndex(locationToIndex(e.getPoint()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()&&getSelectedIndex()!=-1) {
                    //获取选择项的值
                    Object selected = getModel().getElementAt(getSelectedIndex());
                    pumenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    public void updateMenu(String[] strlist){
        //设置弹窗内容
        pumenu.updateMenu(strlist);
    }
    public void updateList(String path){
        //更新jlist
        MyFile dir=new MyFile(path);
        MyFile[] temp=dir.listFiles();
        Arrays.sort(temp,MyComparator.dircomparator);
        dlm.clear();
        for(MyFile i:temp)
            dlm.addElement(i);
    }

}
