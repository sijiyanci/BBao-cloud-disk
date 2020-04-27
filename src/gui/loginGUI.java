package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class loginGUI extends JFrame{
    private static final long serialVersionUID = -6256528270698337160L;
    private JTextField userName; //用户名输入框
    private JPasswordField password; //密码输入框
    private JLabel labelUser;//用户名标签
    private JLabel labelPassword;//密码标签
    private JButton buttonLogin;//登录按钮
    private JButton buttonRegister;//注册按钮
    private JButton buttonClose;//关闭按钮
    private int windowX,windowY;//窗口位置
    private boolean isDraging = false;//可拖拽？
    private JPanel contentPane;//窗体

    public static Socket socket;

    public static void main(String[] args) {
        loginGUI frame = new loginGUI();
        frame.setVisible(true);
    }

    public loginGUI(){
        //设置无标题栏
        setUndecorated(true);
        //监听鼠标确保窗体能够拖拽
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                isDraging = true;
                windowX = e.getX();
                windowY = e.getY();
            }

            public void mouseReleased(MouseEvent e){
                isDraging = false;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDraging){
                    int left = getLocation().x;
                    int top = getLocation().y;
                    //设置位置这里有疑问？？？？？？？？？
                    setLocation(left+e.getX() - windowX,top + e.getY() - windowY);
                }
            }
        });

        setBounds(100,100,439,450);
        setLocationRelativeTo(null);//设置之后能够让窗体再正中间？
        contentPane = new JPanel();
        contentPane.setBackground(Color.gray);
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setResizable(false);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //上面标题
        JLabel labelTitle = new JLabel("登  录");
        labelTitle.setForeground(Color.WHITE);
        labelTitle.setBackground(Color.WHITE);
        labelTitle.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,40));
        labelTitle.setBounds(170,60,357,80);
        contentPane.add(labelTitle);

        //用户名输入框
        userName = new JTextField("");
        userName.setBounds(170,167,219,35);
        userName.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        userName.setBorder(null);
        contentPane.add(userName);

        //用户名输入框旁边的文字
        labelUser = new JLabel("用户名");
        labelUser.setBounds(50,170,126,27);
        labelUser.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        labelUser.setForeground(Color.WHITE);
        contentPane.add(labelUser);

        //密码输入框
        password = new JPasswordField("");
        password.setBounds(170,230,219,35);
        password.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        password.setBorder(null);
        contentPane.add(password);

        //密码输入框旁边的文字
        labelPassword = new JLabel("密码");
        labelPassword.setBounds(50,233,126,27);
        labelPassword.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        labelPassword.setForeground(Color.WHITE);
        contentPane.add(labelPassword);

        //登录按钮
        buttonLogin = new JButton("登录");
        buttonLogin.setBounds(50,293,170,40);
        buttonLogin.setBackground(Color.LIGHT_GRAY);
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setBorder(null);
        buttonLogin.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        buttonLogin.setPreferredSize(new Dimension(170,40));
        buttonLogin.setFocusPainted(false);
        contentPane.add(buttonLogin);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                System.out.println("客户页面");
                //new SignIn()  //显示用户主页面
            }
        });

        //注册按钮
        buttonRegister = new JButton("注册");
        buttonRegister.setBounds(225,293,170,40);
        buttonRegister.setBackground(Color.LIGHT_GRAY);
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setBorder(null);
        buttonRegister.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        buttonRegister.setPreferredSize(new Dimension(170,40));
        buttonRegister.setFocusPainted(false);
        contentPane.add(buttonRegister);

        //监听注册按钮
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                System.out.println("注册页面");
                //new SignUp() //显示注册界面
                registerGUI registerFrame = new registerGUI();
                registerFrame.setVisible(true);
            }
        });

        //关闭按钮
        ImageIcon closeIcon = new ImageIcon("src/images/close_536px_1.png");
        buttonClose = new JButton(closeIcon);
        buttonClose.setBounds(395,5,40,30);
        buttonClose.setBackground(Color.LIGHT_GRAY);
        buttonClose.setForeground(Color.WHITE);
        buttonClose.setBorder(null);
        buttonClose.setPreferredSize(new Dimension(40,30));
        buttonClose.setFocusPainted(false);
        contentPane.add(buttonClose);

        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }
}
