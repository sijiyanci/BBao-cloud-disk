package client.gui.tools;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class MyClientFile {
    public static boolean DIRTYPE=true;
    public static boolean FILETYPE=false;
    private String path;
    private String name;
    private String extension;
    private boolean type;   //0--file;1--dir
    private long length;
    private Date modifytime;
    private ArrayList<MyClientFile> children;
    private MyClientFile parent;

    public MyClientFile(){}
    public MyClientFile(String path,String name,String extension,boolean type,long length,Date modifytime){
        this.path=path;
        this.name=name;
        this.extension=extension;
        this.type=type;
        this.length=length;
        this.modifytime=modifytime;
    }
    public String toString(){
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isType() {
        return type;
    }

    public long getLength() {
        return length;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public ArrayList<MyClientFile> getChildren() {
        return children;
    }

    public MyClientFile getParent() {
        return parent;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public void setChildren(ArrayList<MyClientFile> children) {
        this.children = children;
    }

    public void setParent(MyClientFile parent) {
        this.parent = parent;
    }
    public boolean isDirectory(){
        return type;
    }
    public boolean isFile(){
        return !type;
    }
}
