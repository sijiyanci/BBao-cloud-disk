package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guitest {
    private JButton button1;
    private JButton button2;

    public guitest() {
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("这正在测试合并");
            }
        });
    }
}
