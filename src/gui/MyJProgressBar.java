package gui;

import javax.swing.*;

public class MyJProgressBar extends JProgressBar implements Runnable{
    private FileController fcl;
    public MyJProgressBar(){
        String sourse="D:\\四季言辞\\new\\p1.mp4";
        String des="D:\\四季言辞";
        fcl=new FileController(sourse,des);
        setValue(0);
        setStringPainted(true);
    }

    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            setValue(i);
        }
        while(!fcl.state){
            int proportion=(int)(fcl.finishpart/fcl.size*100);
            setValue(proportion);
        }
        setVisible(false);
        //setString("上传成功！");
    }

    public void setRun(){
        fcl.setRun();
        new Thread(this).start();
    }
}
