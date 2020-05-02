package client.gui.tools;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MyJList extends JPanel implements MyJListListener{
    private JList list;
    private JScrollPane jsp;
    private DefaultListModel dlm;
    private MyJPopupMenu itempop,nopop;
    //private DefaultMutableTreeNode parent;
    private MyJtreeListener mjl;
    public MyJList(){
        super();
        dlm=new DefaultListModel();
        list=new JList(dlm);
        //设置渲染cell
        list.setCellRenderer(new MyListCellRenderer());
        jsp=new JScrollPane(list);
        setLayout(new BorderLayout());
        add(jsp);
        //jlist的弹窗点击事件
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

                Rectangle r=list.getCellBounds(0,list.getModel().getSize()-1);
                Point p=e.getPoint();
                if(r!=null){
                    if(r.getHeight()>=p.getY())
                        list.setSelectedIndex(list.locationToIndex(e.getPoint()));
                    else{
                        list.clearSelection();
                    }
                }
                //System.out.println(list.getSelectedValue());
                //System.out.println();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()&&list.getSelectedIndex()!=-1) {
                    //获取选择项的值
                    Object selected = list.getModel().getElementAt(list.getSelectedIndex());
                    if(itempop!=null)
                        itempop.show(e.getComponent(),e.getX(), e.getY());
                }else if(e.isPopupTrigger()&&list.getSelectedIndex()==-1){
                    if(nopop!=null)
                        nopop.show(e.getComponent(),e.getX(), e.getY());
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
    public void newItemPop(MyJPopupMenu itempop){
        this.itempop=itempop;
    }
    public void newNoPop(MyJPopupMenu nopop){
        this.nopop=nopop;
    }

    public void setMjl(MyJtreeListener mjl) {
        this.mjl = mjl;
    }

    public void updateList(String path){
        //更新jlist
        MyFile dir=new MyFile(path);
        MyFile[] temp=dir.listFiles();
        Arrays.sort(temp,MyComparator.dircomparator);
        dlm.clear();
        for(MyFile i:temp)
            dlm.addElement(i);
        //System.out.println(getHeight());
    }
    public void updateList(ArrayList<MyClientFile> items){

        items.sort(MyComparator.clientFileComparator);
        dlm.clear();
        for(MyClientFile i : items){
            dlm.addElement(i);
        }
    }

    public boolean newDirectory(String name){
        mjl.nodeChange("新建文件夹",name);
        return false;
    }

    @Override
    public void dealMenuClick(String type,String name) {
        //Object select=list.getModel().getElementAt(list.getSelectedIndex());

        if(type.equals("新建文件夹")){
            newDirectory(name);
        }
    }
}
