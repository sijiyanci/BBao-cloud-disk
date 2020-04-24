package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



@SuppressWarnings("unused")
public class start extends JFrame {
    private static final long serialVersionUID = -6256528270698337060L;
    private JTextField userName; // 用户名
    private JPasswordField password; // 密码
    private JLabel lableUser;
    private JLabel lablePwd;
    private JButton btnLogin; // 按钮
    private JButton btnRegister;
    private int wx, wy;
    private boolean isDraging = false;
    private JPanel contentPane;

    public static Socket socket;

    public static void main(String[] args) {
        start frame = new start();
        frame.setVisible(true);
    }

    public start() {
        // 设置无标题栏
        setUndecorated(true);
        // 监听鼠标 确保窗体能够拖拽
        addMouseListener((MouseListener) new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                isDraging = true;
                wx = e.getX();
                wy = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                isDraging = false;
            }
        });
        addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDraging) {
                    int left = getLocation().x;
                    int top = getLocation().y;
                    setLocation(left + e.getX() - wx, top + e.getY() - wy);
                }
            }
        });

        setBounds(0, 0, 439, 369);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        //contentPane.setBackground(Color.PINK);
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);
        //setContentPane(contentPane);
        add(contentPane);
        contentPane.setLayout(null);

        // 上面标题
        JLabel lblv = new JLabel("CHAT ROOM");
        lblv.setForeground(Color.WHITE);
        lblv.setBackground(Color.WHITE);
        lblv.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 58));
        lblv.setBounds(37, 60, 357, 80);
        contentPane.add(lblv);
        // 用户名输入框
        userName = new JTextField("");
        userName.setBounds(170, 167, 219, 35);
        userName.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 25));
        userName.setBorder(null);
        contentPane.add(userName);
        // 登录输入框旁边的文字
        lableUser = new JLabel("用户名");
        lableUser.setBounds(37, 170, 126, 27);
        lableUser.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 25));
        lableUser.setForeground(Color.WHITE);
        contentPane.add(lableUser);
        // 密码输入框
        password = new JPasswordField("");
        password.setBounds(170, 212, 219, 35);
        password.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 30));
        password.setBorder(null);
        contentPane.add(password);
        //  密码输入框旁边的文字
        lablePwd = new JLabel("密码");
        lablePwd.setBounds(37, 215, 126, 27);
        lablePwd.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 25));
        lablePwd.setForeground(Color.WHITE);
        contentPane.add(lablePwd);

        //登录按钮
        btnLogin = new JButton("登录");
        btnLogin.setBounds(225, 285, 170, 40);
        //btnLogin.setBackground(new Color(0xFFC0CB));
        btnLogin.setBackground(Color.LIGHT_GRAY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBorder(null);
        btnLogin.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 25));
        btnLogin.setPreferredSize(new Dimension(170, 40));
        btnLogin.setFocusPainted(false);
        contentPane.add(btnLogin);

        //btnLogin.addActionListener( new Signin());

        //注册
        btnRegister = new JButton("注册");
        btnRegister.setBounds(37, 285, 170, 40);
        btnRegister.setFocusPainted(false);
        //btnRegister.setBackground(new Color(0xFFC0CB));
        btnRegister.setBackground(Color.LIGHT_GRAY);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBorder(null);
        btnRegister.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 25));
        btnRegister.setPreferredSize(new Dimension(170, 40));
        contentPane.add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //new SignUp();		//显示注册界面
            }
        });
    }
}

    //----------------------------------------------------

