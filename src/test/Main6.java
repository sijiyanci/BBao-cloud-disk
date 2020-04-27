package test;
/**
 * java swing 之进度条的使用
 * @author gao
 */

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class Main6 extends JFrame {
    public Main6(){
        this.setTitle("进度条的使用");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 250, 100);
        JPanel contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        final JProgressBar progressBar=new JProgressBar();
        progressBar.setStringPainted(true);
        new Thread(){
            public void run(){
                for(int i=0;i<=100;i++){
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    progressBar.setValue(i);
                }
                progressBar.setString("升级完成！");
            }
        }.start();
        contentPane.add(progressBar);
        this.setVisible(true);
    }
    public static void main(String[]args){
        Main6 example=new Main6();
        System.out.println("!");
    }
}
