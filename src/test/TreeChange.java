package test;



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * tree node 添加 , 删除, 修改
 *
 * @author Administrator
 *
 */
public class TreeChange implements ActionListener, TreeModelListener {
    private JLabel statusLabel = null;
    private JTree tree = null;
    private DefaultTreeModel treeModel = null;
    private String oldNodeName = null;

    public TreeChange() {
        JFrame frame = new JFrame("JTreeTest 窗体");
        Container contentPane = frame.getContentPane();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("根节点");
        tree = new JTree(root);
        tree.addMouseListener(new MyTreeMouseListener());
        treeModel = (DefaultTreeModel) tree.getModel();
        treeModel.addTreeModelListener(this);
        tree.setEditable(true);
        tree.getCellEditor().addCellEditorListener(
                new MyTreeCellEditorListener());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(tree);

        JPanel toolBarPanel = new JPanel();
        JButton b = new JButton("新增节点");
        b.addActionListener(this);
        toolBarPanel.add(b);
        b = new JButton("删除节点");
        b.addActionListener(this);
        toolBarPanel.add(b);
        b = new JButton("清除所有节点");
        b.addActionListener(this);
        toolBarPanel.add(b);

        statusLabel = new JLabel("Action");
        contentPane.add(toolBarPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 本方法运行新增、删除、清除所有节点的程序代码.
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("新增节点")) {
            DefaultMutableTreeNode parentNode = null;
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("新节点");
            newNode.setAllowsChildren(true);
            TreePath parentPath = tree.getSelectionPath();
            if (parentPath == null) {
                return;
            }
            // 取得新节点的父节点
            parentNode = (DefaultMutableTreeNode) (parentPath
                    .getLastPathComponent());

            // 由DefaultTreeModel的insertNodeInto（）方法增加新节点
            treeModel.insertNodeInto(newNode, parentNode,
                    parentNode.getChildCount());

            // tree的scrollPathToVisible()方法在使Tree会自动展开文件夹以便显示所加入的新节点。若没加这行则加入的新节点
            // 会被 包在文件夹中，你必须自行展开文件夹才看得到。
            tree.scrollPathToVisible(new TreePath(newNode.getPath()));
            tree.setSelectionPath(new TreePath(newNode.getPath()));
            statusLabel.setText("新增节点成功");
        }
        if (ae.getActionCommand().equals("删除节点")) {
            TreePath treepath = tree.getSelectionPath();
            if (treepath != null) {
                // 下面两行取得选取节点的父节点.
                DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) treepath
                        .getLastPathComponent();
                TreeNode parent = (TreeNode) selectionNode.getParent();
                if (parent != null) {
                    // 由DefaultTreeModel的removeNodeFromParent()方法删除节点，包含它的子节点。
                    treeModel.removeNodeFromParent(selectionNode);
                    statusLabel.setText("删除节点成功");
                }
            }
        }
        if (ae.getActionCommand().equals("清除所有节点")) {

            // 下面一行，由DefaultTreeModel的getRoot()方法取得根节点.
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) treeModel
                    .getRoot();

            // 下面一行删除所有子节点.
            rootNode.removeAllChildren();

            // 删除完后务必运行DefaultTreeModel的reload()操作，整个Tree的节点才会真正被删除.
            treeModel.reload();
            statusLabel.setText("清除所有节点成功");
        }
    }

    public void treeNodesChanged(TreeModelEvent e) {
        TreePath treePath = e.getTreePath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath
                .getLastPathComponent();
        try {
            int[] index = e.getChildIndices();
            node = (DefaultMutableTreeNode) node.getChildAt(index[0]);
        } catch (NullPointerException exc) {
        }
        statusLabel.setText(oldNodeName + "更改数据为:"
                + (String) node.getUserObject());
    }

    public static void main(String[] args) {
        new TreeChange();
    }

    class MyTreeMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            try {
                JTree tree = (JTree) e.getSource();
                int rowLocation = tree.getRowForLocation(e.getX(), e.getY());
                TreePath treepath = tree.getPathForRow(rowLocation);
                TreeNode treenode = (TreeNode) treepath.getLastPathComponent();
                oldNodeName = treenode.toString();
            } catch (NullPointerException ne) {
            }
        }
    }

    class MyTreeCellEditorListener implements CellEditorListener {
        public void editingStopped(ChangeEvent e) {
            Object selectnode = tree.getLastSelectedPathComponent();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectnode;
            CellEditor cellEditor = (CellEditor) e.getSource();
            String newName = (String) cellEditor.getCellEditorValue();
            node.setUserObject(newName);

            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            model.nodeStructureChanged(node);
        }

        public void editingCanceled(ChangeEvent e) {
            editingStopped(e);
        }
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
    }
}
