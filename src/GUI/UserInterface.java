package GUI;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class UserInterface extends JFrame {

    private JPanel jpanel1,jpanel2,jpanel3,jpanel4;
    private JLabel jlabel1;
    private JButton jbutton1,jbutton2,jbutton3;
    private JTextField jtf1;
    private JTree jtree1;
    private JList<String> jlist1;
    private JScrollPane jscroll1;
    private File projectFile = new File(".");
    public static void main(String[] arg){
        UserInterface jframe=new UserInterface();
        jframe.setVisible(true);
    }

    public UserInterface(){
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 700, 500);
        getContentPane().setLayout(null);

        //初始化上下部分
        jpanel1=new JPanel();
        jpanel1.setLayout(null);
        jpanel1.setBounds(0, 0, 700, 100);
        jpanel1.setBackground(new Color(100,100,100));
        this.getContentPane().add(jpanel1);

        jpanel2=new JPanel();
        jpanel2.setLayout(null);
        jpanel2.setBounds(0, 100, 700, 400);
        jpanel2.setBackground(new Color(200,200,200));
        this.getContentPane().add(jpanel2);

        jpanel3=new JPanel();
        jpanel3.setLayout(null);
        jpanel3.setBounds(20, 10, 325, 365);
        jpanel3.setBackground(new Color(0,200,200));
        jpanel2.add(jpanel3);

        jpanel4=new JPanel();
        jpanel4.setLayout(null);
        jpanel4.setBounds(345, 10, 325, 365);
        jpanel4.setBackground(new Color(200,200,0));
        jpanel2.add(jpanel4);

        jscroll1=new JScrollPane();
        jscroll1.setBounds(0,0,325,365);
        jpanel4.add(jscroll1);

        //上部分建立
        jlabel1=new JLabel("用户名");
        jlabel1.setBounds(20,20,80,25);
        jlabel1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        jpanel1.add(jlabel1);
        jbutton1=new JButton("上传");
        jbutton1.setBounds(20,65,60,25);
        jpanel1.add(jbutton1);
        jbutton2=new JButton("✘");
        jbutton2.setBounds(645,0,45,25);
        jpanel1.add(jbutton2);
        jbutton3=new JButton("跳转");
        jbutton3.setBounds(575,65,60,25);
        jpanel1.add(jbutton3);
        jtf1=new JTextField();
        jtf1.setBounds(340,65,220,25);
        jpanel1.add(jtf1);

        jpanel3.setLayout(new BorderLayout());
        jtree1=new JTree(addNodes( projectFile));
        jpanel3.add(jtree1);
        jtree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
                        .getLastPathComponent();
                if(node==null||node.isLeaf())
                    return ;
                Object[] nodes=node.getPath();

                String path=new String();
                if(!node.isLeaf()){
                    for(Object i:nodes){
                        path+=i.toString()+'/';
                    }
                }
                displayContent(path);

            }
        });
    }
    void displayContent(String path){
//          String[]  items = new String[]{"Spring", "Summer",  "Fall",  "Winter"};
//          jlist1 = new JList<>(items);
//          jscroll1.setViewportView(jlist1);

        File dir=new File(path);
        File[] temp=dir.listFiles();
        Vector<String> strlist=new Vector<>();
        for(File i:temp){
            strlist.add(i.getName());
        }

        jlist1=new JList<>(strlist);
        jscroll1.setViewportView(jlist1);


    }


    DefaultMutableTreeNode addNodes(File dir) {

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

//class MyTreeNode extends DefaultMutableTreeNode{
//    private String path;
//    public MyTreeNode(File f){
//        super(f.getName());
//        path=f.getPath();
//    }
//    public String getPath(){
//        return path;
//    }
//
//}

