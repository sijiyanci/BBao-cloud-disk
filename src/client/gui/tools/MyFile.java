package client.gui.tools;

import java.io.File;

public class MyFile extends File {
    public MyFile(String path){
        super(path);
    }
    public MyFile(File file){
        super(file.getPath());
    }
    public String toString(){
        return getName();
    }
    public MyFile[] listFiles(){
        File[] files=super.listFiles();
        MyFile[] nfiles=new MyFile[files.length];
        for(int i=0;i<files.length;i++)
            nfiles[i]=new MyFile(files[i]);
        return nfiles;
    }
}
