package server.gui.tools;

import client.gui.tools.FileController;

import javax.swing.*;

//进度条线程
public class ServerJProgressBar extends JProgressBar implements Runnable {

    private FileController fcl;
    public ServerJProgressBar(){
        String sourse="F:\\Java\\BBao\\Close_48px_3.png";
        String des="F:\\Java";
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
//        setVisible(true);
        setString("上传成功！");
    }

    public void setRun(){
        fcl.setRun();
        new Thread(this).start();
    }

}
