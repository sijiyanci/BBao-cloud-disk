package client.gui.tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyRename extends JFrame {
    private JTextField textField;
    private JButton yes,no;
    private MyJListListener mll;
    private ActionListener action=new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton temp=(JButton) e.getSource();
            if(temp.getText().equals("确认")){
                MyRename.this.setVisible(false);

                mll.dealMenuClick("新建文件夹",textField.getText());

            }else if(temp.getText().equals("取消")){

            }
            MyRename.this.dispose();
        }
    };

    public MyRename(MyJListListener mll){
        this.mll=mll;

        setBounds(100,100,300,150);
        getContentPane().setLayout(null);
        textField=new JTextField();
        textField.setBounds(50,20,200,25);
        yes=new JButton("确认");
        no=new JButton("取消");
        yes.setBounds(40,70,60,25);
        no.setBounds(200,70,60,25);
        yes.addActionListener(action);
        no.addActionListener(action);
        add(textField);
        add(yes);
        add(no);
    }

    public void setMll(MyJListListener mll) {
        this.mll = mll;
    }
}
