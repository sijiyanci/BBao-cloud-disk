package client.gui.clientinterface;

import client.gui.tools.MyJList;
import client.gui.tools.MyJPopupMenu;
import client.gui.tools.MyJProgressBar;
import client.gui.tools.MyJTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ClientInterface extends JFrame {

    private JPanel jpanel1,jpanel2,jpanel3,jpanel4,jpanel5;
    private JLabel jlabel1;
    private JButton jbutton1,jbutton2,jbutton3;
    private JTextField jtf1;
    private MyJTree jtree1;
    private MyJList jList;
    private JScrollPane jscroll1;
    private MyJPopupMenu jpmenu1;
    private String[] jitems1_str={"下载","分享","删除"};
    private File projectFile = new File(".");
    private JFileChooser jfc1;
    private Point origin;
    private ProgressBar progressBar;
    private MyJProgressBar jpg;


    public static void main(String[] arg){
        ClientInterface jframe=new ClientInterface();
        jframe.setVisible(true);
    }

    public ClientInterface(){
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 700, 500);
        getContentPane().setLayout(null);

        addMouseListener((MouseListener) new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                origin=new Point();
                origin.x=e.getX();
                origin.y=e.getY();
            }

            public void mouseReleased(MouseEvent e) {

            }
        });
        addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                    Point p=getLocation();
                    setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);

            }
        });

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
        jpg=new MyJProgressBar();
        jpg.setBounds(110,10,160,25);
        jpanel1.add(jpg);
        jpg.setVisible(false);

        //下部分建立
        jpmenu1=new MyJPopupMenu(jitems1_str);
        jList=new MyJList(jpmenu1);
        jtree1=new MyJTree(".",jList);

        jscroll1=new JScrollPane(jList);
        jscroll1.setBounds(0,0,325,365);
        jpanel4.add(jscroll1);

        jpanel3.setLayout(new BorderLayout());
        jpanel3.add(jtree1);




        jbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfc1 = new JFileChooser();

                int returnVal = jfc1.showOpenDialog(new JPanel());
//保存文件从这里入手，输出的是文件名
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("你打开的文件是: " +
                            jfc1.getSelectedFile().getName());
//                    progressBar=new ProgressBar();
//                    progressBar.setVisible(true);
                    jpg.setVisible(true);
                    jpg.setRun();
                }
            }
        });
        jbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbutton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    class ProgressBar extends JFrame{
        private JProgressBar jpb;
        public ProgressBar(){
            setBounds(100,100,300,150);
            setLayout(new BorderLayout());
            JPanel jpanel1=new JPanel();
            add(jpanel1,BorderLayout.CENTER);

            jpanel1.setLayout(new GridBagLayout());
            jpanel1.setBackground(Color.CYAN);
            GridBagConstraints gbc=new GridBagConstraints();

            JProgressBar jpb=new JProgressBar();
            jpb.setStringPainted(true);
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.gridwidth=1;
            gbc.gridwidth=1;
            gbc.weightx=1;
            gbc.weighty=0.7;
            gbc.fill=GridBagConstraints.NONE;
            gbc.anchor=GridBagConstraints.CENTER;
            //gbc.insets=new Insets(10,10,0,0);
            jpanel1.add(jpb,gbc);


            gbc=new GridBagConstraints();
            JButton btn2=new JButton("完成");
            gbc.gridx=0;
            gbc.gridy=1;
            gbc.gridwidth=1;
            gbc.gridwidth=1;
            gbc.weightx=1;
            gbc.weighty=0.3;
            gbc.fill=GridBagConstraints.NONE;
            gbc.anchor=GridBagConstraints.CENTER;
            jpanel1.add(btn2,gbc);
            btn2.setVisible(false);

            new Thread(){
                public void run(){
                    for(int i=0;i<=100;i++){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        jpb.setValue(i);
                    }
                    jpb.setString("上传成功！");
                    btn2.setVisible(true);
                }
            }.start();
        }
    }

}










