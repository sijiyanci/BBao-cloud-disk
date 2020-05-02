package test;

import javax.swing.*;
import java.awt.*;

public class Progress extends JFrame{
        private JProgressBar jpb;
        public Progress(){
            setBounds(100,100,300,150);
            setLayout(new BorderLayout());
            JPanel jpanel1=new JPanel();
            add(jpanel1,BorderLayout.CENTER);

            jpanel1.setLayout(new GridBagLayout());
            jpanel1.setBackground(Color.PINK);
            GridBagConstraints gbc=new GridBagConstraints();

            JProgressBar jpb=new JProgressBar();
            jpb.setStringPainted(true);
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.gridwidth=1;
       //     gbc.gridwidth=1;
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
         //   gbc.gridwidth=1;
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

    public static void main(String[] args) {
        Progress progress = new Progress();
        progress.setVisible(true);
    }
}
