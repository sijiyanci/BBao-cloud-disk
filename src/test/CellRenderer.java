package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
@SuppressWarnings("serial")
public class CellRenderer extends DefaultListCellRenderer{
    JPanel newPanel = new JPanel();
    String[] s = {"美国","日本","大陆","英国","法国","意大利","澳洲","韩国"};
    String valueIndex="1,3,5";
    public Component createComponent() {
        JList list = new JList(s);
        JScrollPane jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension(150, 230));    //设置滚动list的宽度以及高度（宽150，高230）

        //加载样式
        //list.setCellRenderer((ListCellRenderer) getListCellRendererComponent(list, "", 0, true, true));
        list.setCellRenderer(new CellRenderer());
        //加载选择文件框
        newPanel.add(jScrollPane);

        return newPanel;
    }

    //JLst渲染器
    //list - 正在绘制的 JList,value - 由 list.getModel().getElementAt(index) 返回的值,index - 单元格索引。isSelected - 如果选择了指定的单元格，则为 true。cellHasFocus - 如果指定的单元格拥有焦点，则为 true。
    public Component getListCellRendererComponent(javax.swing.JList list,
                                                  Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        System.out.println(value.toString()+"!");
        Color background = null;
        Color foreground = null;
        if(valueIndex!=null){
            String[] _valueIndex = valueIndex.split(",");
            for(int i=0;i<_valueIndex.length;i++){
                if((index+"").equals(_valueIndex[i])){
                    background = Color.RED;
                    foreground = Color.WHITE;
                }
            }
        }

        if(isSelected){
            background = Color.BLUE;
            foreground = new Color(0, 255, 0);
        }
        setBackground(background);
        setForeground(foreground);

        return this;
    }

    public static void main(String[] args) {
        CellRenderer test = new CellRenderer();
        Component contents = test.createComponent();
        JFrame frame = new JFrame("CellRenderer");
        frame.setCursor(new Cursor(12));
        frame.setBounds(0, 0,300, 300);
        frame.setResizable(false); // 禁止最大化,禁止拖拉窗口大小
        // frame.pack();
        // 设置窗体启动位置
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().add(contents, BorderLayout.CENTER);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
