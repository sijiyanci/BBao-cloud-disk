package test;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JListTest {
    public static void main(String[] arg){
        String[] strs={"123","132","123"};
        DefaultListModel dlm=new DefaultListModel();
        for(String i:strs)
            dlm.addElement(i);
        JList list=new JList(dlm);
        list.setFixedCellHeight(40);
        list.setFixedCellWidth(300);
        list.setCellRenderer(new MyListCellRenderer());
        JFrame jf=new JFrame();
        JPanel k=new JPanel();
        JButton btn1=new JButton("增加");
        JButton btn2=new JButton("删除");
        jf.getContentPane().setLayout(new BorderLayout());
        jf.add(list,BorderLayout.NORTH);
        jf.add(k,BorderLayout.SOUTH);
        k.add(btn1);
        k.add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlm.remove(0);

            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlm.addElement("456");
            }
        });
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setSize(500,300);
    }
    static class MyListCellRenderer extends JPanel implements ListCellRenderer {

        private JTextField field;
        public MyListCellRenderer() {
            setOpaque(true);
//            setLayout(new GridBagLayout());
            field=new JTextField("");
            JButton button=new JButton();
//            ImageIcon icon=new ImageIcon();
//            icon=new ImageIcon("./public/result/dir.jpg");
//            GridBagConstraints gbc=new GridBagConstraints();
//            gbc.gridx=0;
//            gbc.gridy=0;
//            gbc.gridwidth=1;
//            gbc.gridwidth=1;
//            gbc.weightx=0.5;
//            gbc.weighty=0.7;
//            gbc.fill=GridBagConstraints.BOTH;
//            gbc.anchor=GridBagConstraints.CENTER;
//            add(button,gbc);
//
//            gbc=new GridBagConstraints();
//            gbc.gridx=1;
//            gbc.gridy=1;
//            gbc.gridwidth=1;
//            gbc.gridwidth=1;
//            gbc.weightx=0.5;
//            gbc.weighty=0.3;
//            gbc.fill=GridBagConstraints.BOTH;
//            gbc.anchor=GridBagConstraints.CENTER;
//            add(field,gbc);
            setLayout(null);
            button.setBounds(0,0,30,15);
            field.setBounds(0,15,100,35);
            add(button);
            add(field);

        }

        public Component getListCellRendererComponent(JList list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {


                field.setText(value.toString());

  //            button.setIcon(icon);
//              setSize(300,20);
//              add(button,BorderLayout.NORTH);
//              add(field,BorderLayout.SOUTH);



            if (isSelected) {
                setBackground(Color.BLUE);
                setForeground(Color.white);
            } else {
                setBackground(Color.white);
                setForeground(Color.black);
            }

            return this;
        }

    }


}
