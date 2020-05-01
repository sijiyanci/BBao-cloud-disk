package server.gui.serverinterface;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

//用户文件卡片
public class TreePage extends JTree {

    public TreePage(File dir){
        super(addNodes(dir));
    }

    static DefaultMutableTreeNode addNodes(File dir) {

        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(dir.getName());

        File[] tmp = dir.listFiles();
        Vector<File> ol = new Vector<File>();
        ol.addAll(Arrays.asList(tmp));
        Collections.sort(ol, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int result = o1.getName().compareTo(o2.getName());
                if (o1.isDirectory() && o2.isFile()) {
                    result = -1;
                } else if (o2.isDirectory() && o1.isFile()) {
                    result = 1;
                }
                return result;
            }
        });
        for (int i = 0; i < ol.size(); i++) {
            File file = ol.elementAt(i);
            DefaultMutableTreeNode node=new DefaultMutableTreeNode(file.getName());
            if (file.isDirectory()) {
                node=addNodes(file);
            }
            curDir.add(node);
        }
        return curDir;
    }

}
