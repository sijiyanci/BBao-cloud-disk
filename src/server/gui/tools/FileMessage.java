package server.gui.tools;

import java.util.Date;

//上传或者文件时候，需要的信息
public class FileMessage {
    private String fileName;
    private String room;
    private String filePath;
    private String userName;
    private Date time;


    public FileMessage() {
    }

    public FileMessage(String fileName, String room, String filePath, String userName, Date time) {
        this.fileName = fileName;
        this.room = room;
        this.filePath = filePath;
        this.userName = userName;
        this.time = time;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
