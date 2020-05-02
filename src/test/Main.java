package test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Main extends JPanel {
    File projectFile = new File(".");

    public Main(File dir) {
        setLayout(new BorderLayout());
        JTree tree = new JTree(addNodes( projectFile));
        //tree.setCellRenderer(new MyTreeCellRenderer());
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
                        .getLastPathComponent();
                System.out.println("You selected " + node);
            }
        });

        JScrollPane scrollpane = new JScrollPane();
        scrollpane.getViewport().add(tree);
        add(scrollpane, BorderLayout.CENTER);
    }

    DefaultMutableTreeNode addNodes( File dir) {
//        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(dir);
//        if (curTop != null) {
//            curTop.add(curDir);
//        }
//        File[] tmp = dir.listFiles();
//        Vector<File> ol = new Vector<File>();
//        ol.addAll(Arrays.asList(tmp));
//        Collections.sort(ol, new Comparator<File>() {
//            @Override
//            public int compare(File o1, File o2) {
//                int result = o1.getName().compareTo(o2.getName());
//                if (o1.isDirectory() && o2.isFile()) {
//                    result = -1;
//                } else if (o2.isDirectory() && o1.isFile()) {
//                    result = 1;
//                }
//                return result;
//            }
//        });
//        for (int i = 0; i < ol.size(); i++) {
//            File file = ol.elementAt(i);
//            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
//            if (file.isDirectory()) {
//                addNodes(node, file);
//            }
//            curDir.add(node);
//        }
//        return curDir;
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

    public Dimension getMinimumSize() {
        return new Dimension(200, 400);
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 400);
    }

    public static void main(String[] av) {
        JFrame frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(new Main(new File(".")));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MyTreeCellRenderer extends DefaultTreeCellRenderer {
    FileSystemView fsv = FileSystemView.getFileSystemView();
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        System.out.println(value);
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row,
                hasFocus);
        if (value instanceof DefaultMutableTreeNode) {
            value = ((DefaultMutableTreeNode) value).getUserObject();
            if (value instanceof File) {
                File file = (File) value;
//                if (file.isFile()) {
//                    setIcon(fsv.getSystemIcon(file));
//                    setText(file.getPath());
//                } else {
//                    setIcon(fsv.getSystemIcon(file));
//                    setText(file.getName());
//                }
                setIcon(fsv.getSystemIcon(file));
                setText(file.getName());
            }
        }
        return this;
    }
}