package GUI;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class MyJTree extends JTree{
    private MyJList ml;
    private String path;
    private DefaultTreeModel dtm;
    public MyJTree(String path,MyJList ml){
        super(addNodes(new MyFile(path)));
        this.path=path;
        this.ml=ml;
        dtm=(DefaultTreeModel) getModel();

        addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
                        .getLastPathComponent();
                if(node==null||node.isLeaf())
                    return ;
                MyFile file=(MyFile)node.getUserObject();
                ml.updateList(file.getPath());

            }
        });
    }
    //设立一个jlist与jtree绑定
    public void setMl(MyJList ml){
        this.ml=ml;

    }
    //初始化树
    public static DefaultMutableTreeNode addNodes(MyFile dir) {

        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(dir);

        MyFile[] tmp = dir.listFiles();
        Arrays.sort(tmp,MyComparator.dircomparator);
        for(MyFile i: tmp){
            //新建节点
            DefaultMutableTreeNode node=new DefaultMutableTreeNode(i);
            //如果该节点要为目录，那么新建节点为递归调用的返回值
            if(i.isDirectory())
                node=addNodes(i);
            //将新建节点连到父亲上
            curDir.add(node);
        }
        return curDir;
    }

}
