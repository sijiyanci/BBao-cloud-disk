package client.gui.tools;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MyJTree extends JPanel implements MyJtreeListener{
    private MyJList ml;
    private JTree tree;
    private JScrollPane jsp;
    private String path;
    private DefaultTreeModel dtm;
    public MyJTree(String path,MyJList ml){
        MyClientFile root=toTree(new MyFile(path));
        tree=new JTree(addNodes(root));

        this.path=path;
        this.ml=ml;
        dtm=(DefaultTreeModel) tree.getModel();
        jsp=new JScrollPane(tree);
        setLayout(new BorderLayout());
        add(jsp);

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setCellRenderer(new MyTreeCellRenderer());

        TreePath tpath=new TreePath(tree.getModel().getRoot());


        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
                        .getLastPathComponent();

//                for(int i=0;i<node.getChildCount();i++)
//                {
//                    System.out.println(node.getChildAt(i));
//                }
                if(node==null)
                    return ;
                if(node.getUserObject() instanceof MyClientFile){
                    MyClientFile file=(MyClientFile)node.getUserObject();
                    if(file.isFile())
                        return;
                    ml.updateList(file.getChildren());

                }else if(node.getUserObject() instanceof MyFile){
                    MyFile file=(MyFile)node.getUserObject();
                    if(file.isFile())
                    ml.updateList(file.getPath());
                }


            }
        });
        tree.setSelectionPath(tpath);
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
    public static DefaultMutableTreeNode addNodes(MyClientFile parent) {

        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(parent);

        ArrayList<MyClientFile> children=parent.getChildren();
        children.sort(MyComparator.clientFileComparator);
        for(MyClientFile i: children){
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

    public static MyClientFile toTree(MyFile root){
        String name=root.getName();

        MyClientFile parent=new MyClientFile(root.getPath(),name,name.substring(name.lastIndexOf('.')+1)
                            ,root.isDirectory(),root.length(),new Date(root.lastModified()));
        if(root.isDirectory()){
            ArrayList<MyClientFile> children=new ArrayList<>();
            parent.setChildren(children);
            MyFile[] files=root.listFiles();

            for(MyFile i:files){
                children.add(toTree(i));
            }
        }
        return parent;

    }


    @Override
    public void nodeChange(String type,String name) {
        if(type.equals("新建文件夹"))
            newDirectory(name);

    }
    public boolean newDirectory(String name){
        DefaultMutableTreeNode select=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        DefaultTreeModel model=(DefaultTreeModel)tree.getModel();
        MyClientFile pfile=(MyClientFile) select.getUserObject();
        ArrayList<MyClientFile> sibling=pfile.getChildren();
        //判断是否存在相同的名字的文件夹
        for(MyClientFile i:sibling){
            if(name.equals(i.getName())&&i.isDirectory())
                return false;
        }

        MyClientFile newfile=new MyClientFile(pfile.getPath()+ File.pathSeparator+name,name,"",MyClientFile.DIRTYPE
                ,-1,new Date());
        newfile.setChildren(new ArrayList<MyClientFile>());
        //对数据逻辑树节点添加节点
        sibling.add(newfile);
        //对控件逻辑树节点模型添加节点
        int index=0;
        if(select.getChildCount()>0){
            DefaultMutableTreeNode node=(DefaultMutableTreeNode)select.getFirstChild();
            MyClientFile file=(MyClientFile)node.getUserObject();

            while(file.isDirectory()){
                //System.out.println(file.getName());
                if(file.getName().compareTo(newfile.getName())>0){
                    break;
                }
                index++;
                node=node.getNextSibling();
                if(node==null){
                    break;

                }
                file=(MyClientFile)node.getUserObject();
            }
        }
        model.insertNodeInto(new DefaultMutableTreeNode(newfile),(DefaultMutableTreeNode)select,
                index);


        //更新jlist
        ml.updateList(sibling);
        return true;
    }
}
class MyTreeCellRenderer extends DefaultTreeCellRenderer {
    FileSystemView fsv = FileSystemView.getFileSystemView();
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        //System.out.println(value);
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row,
                hasFocus);

        MyClientFile item=(MyClientFile) ((DefaultMutableTreeNode)value).getUserObject();
        ImageIcon icon=new ImageIcon();
        if(item.isDirectory())
            icon=new ImageIcon("./public/result/dir.jpg");
        else if(item.isFile())
            icon=new ImageIcon("./public/result/file.jpg");
        setText(value.toString());
        setIcon(icon);

        return this;
    }
}