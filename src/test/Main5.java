package test;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main5{

    public static void fileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
//设置文件类型
        chooser.setFileFilter(filter);
//打开e799bee5baa6e79fa5e98193e4b893e5b19e31333264663139选择器面板
        int returnVal = chooser.showOpenDialog(new JPanel());
//保存文件从这里入手，输出的是文件名
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("你打开的文件是: " +
                    chooser.getSelectedFile().getName());
        }
    }
    public static void main(String[] args) {
        fileChooser();
    }
}