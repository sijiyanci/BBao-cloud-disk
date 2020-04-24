package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.Socket;

public class loginGUI extends JFrame{
    private static final long serialVersionUID = -6256528270698337160L;
    private JTextField userName; //用户名输入框
    private JPasswordField password; //密码输入框
    private JLabel labelUser;//用户名标签
    private JLabel labelPassword;//密码标签
    private JButton buttonLogin;//登录按钮
    private JButton buttonRegister;//注册按钮
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
        setUndecorated(false);
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

        setBounds(100,100,439,369);
        setLocationRelativeTo(null);//设置之后能够让窗体再正中间？
        contentPane = new JPanel();
        contentPane.setBackground(Color.pink);
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setResizable(false);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //上面标题
        JLabel labelTitle = new JLabel("登  录");
        labelTitle.setForeground(Color.WHITE);
        labelTitle.setBackground(Color.WHITE);
        labelTitle.setFont(new Font("Microsoft JhengHei Light",Font.PLAIN,40));
        labelTitle.setBounds(200,60,357,80);
        contentPane.add(labelTitle);

    }
}
