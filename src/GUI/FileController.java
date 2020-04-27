package GUI;

import java.io.*;

public class FileController implements Runnable{
    public File sfile,dfile;
    public long finishpart;
    public boolean state;
    public long size;
    //public static String server="D:\\四季言辞\\new\\p1.mp4";
    public FileController(String sourse,String des){
        sfile=new File(sourse);
        dfile=new File(des+File.separator+sfile.getName());
        state=false;
        size=sfile.length();
    }
    @Override
    public void run() {
        try{
            dfile.createNewFile();
            long len=0;

            FileOutputStream fos=new FileOutputStream(dfile);
            InputStream fis=new FileInputStream(sfile);
            byte[] buffer=new byte[2048];
            while((len=fis.read(buffer))>0){

                fos.write(buffer,0,(int)len);
                finishpart+=len;
            }
            fos.close();
            fis.close();
            state=true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setRun(){
        new Thread(this).start();
    }
}
