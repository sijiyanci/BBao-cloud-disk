package client.gui.register;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class Register extends JFrame {
    private static final long serialVersionUID = -6256528270698337162L;
    private JTextField userName; // 设置用户名输入框
    private JPasswordField password1; //设置密码输入框
    private JPasswordField password2; //再次验证密码输入框
    private JLabel labelUser; //用户名标签
    private JLabel labelPassword1; //密码标签
    private JLabel labelPassword2; //验证密码标签
    private JButton buttonRegister; //注册按钮
    private JButton buttonClose; //关闭按钮
    private int windowX,windowY; //窗口位置
    private boolean isDraging = false;
    private JPanel contentPane; //窗体

    public static Socket socket;

    /*//用于测试创建效果
    public static void main(String[] args) {
        registerGUI frame = new registerGUI();
        frame.setVisible(true);
    }*/

    public Register(){
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

        setBounds(100,100,439,520);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.gray);
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setResizable(false);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //上面标题
        JLabel labelTitle = new JLabel("注  册");
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
        password1 = new JPasswordField("");
        password1.setBounds(170,230,219,35);
        password1.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        password1.setBorder(null);
        contentPane.add(password1);

        //密码输入框旁边的文字
        labelPassword1 = new JLabel("设置密码");
        labelPassword1.setBounds(50,233,126,27);
        labelPassword1.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        labelPassword1.setForeground(Color.WHITE);
        contentPane.add(labelPassword1);

        //再次输入密码输入框
        password2 = new JPasswordField("");
        password2.setBounds(170,293,219,35);
        password2.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        password2.setBorder(null);
        contentPane.add(password2);

        //再次输入密码旁边的文字
        labelPassword2 = new JLabel("重输密码");
        labelPassword2.setBounds(50,296,126,27);
        labelPassword2.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        labelPassword2.setForeground(Color.WHITE);
        contentPane.add(labelPassword2);

        //注册按钮
        buttonRegister = new JButton("注册");
        buttonRegister.setBounds(145,359,170,40);
        buttonRegister.setBackground(Color.LIGHT_GRAY);
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setBorder(null);
        buttonRegister.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
        buttonRegister.setPreferredSize(new Dimension(170,40));
        buttonRegister.setFocusPainted(false);
        contentPane.add(buttonRegister);

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  final JDialog dialog = new JDialog(,"",true);
                setVisible(false);
                System.out.println("回到登录页面");
                if (true){
                    new PopUpWindow().setVisible(true);
                }
            }
        });

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

    class PopUpWindow extends JFrame{
        private JLabel label;
        private JButton button;
        private JButton closeButton;
        private JPanel panel;

        public PopUpWindow(){
            setUndecorated(true);
            setBounds(100,100,300,200);
            setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setBackground(Color.gray);
            panel.setBorder(new EmptyBorder(5,5,5,5));
            setResizable(false);
            setContentPane(panel);
            panel.setLayout(null);

            label = new JLabel("注册成功!",JLabel.CENTER);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.WHITE);
            label.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
            label.setBounds(90,40,120,50);
            panel.add(label);

            button = new JButton("确定");
            button.setBounds(90,100,120,40);
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.WHITE);
            button.setBorder(null);
            button.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,25));
            button.setPreferredSize(new Dimension(120,40));
            button.setFocusPainted(false);
            panel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });

            ImageIcon closeIcon = new ImageIcon("src/images/close_536px_1.png");
            closeButton = new JButton(closeIcon);
            closeButton.setBounds(255,5,40,30);
            closeButton.setBackground(Color.LIGHT_GRAY);
            closeButton.setForeground(Color.WHITE);
            closeButton.setBorder(null);
            closeButton.setPreferredSize(new Dimension(40,30));
            closeButton.setFocusPainted(false);
            panel.add(closeButton);

            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
        }
    }

}
