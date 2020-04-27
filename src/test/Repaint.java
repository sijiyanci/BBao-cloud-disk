package test;


import java.awt.Container;

import java.awt.FlowLayout;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

public class Repaint extends JFrame {

    Container con = this.getContentPane();

    JButton jb1 = new JButton("jb1");

    JButton jb2 = new JButton("jb2");

    JLabel jl1 = new JLabel("jl1");

    FlowLayout gly = new FlowLayout();

    JPanel jp = new JPanel(gly);

    public Repaint() {

        con.add(jp);

        jp.add(jb1);

        jp.add(jb2);

        MyListener ml = new MyListener();

        jb1.addMouseListener(ml);

        this.setSize(300, 200);

        this.setVisible(true);

    }

    private class MyListener extends MouseAdapter {

        @Override

        public void mouseClicked(MouseEvent e) {

            // TODO Auto-generated method stub

            try {

                JButton jb3 = new JButton("jb3");

                jp.add(jb3);

//                               jp.updateUI();//可以正常显示

                jp.repaint();//API中对repaint()方法是这样描述的，调度完当前所有未完成的事件后重新绘制该组件，repaint方法不总是马上执行，所以只有调整大小才可以显示。

                //主要就是下面的invalidate和validate

                //当然，用jp来invalidate和validatae也是可以的

//                                   jp.invalidate();

            } catch (Exception ex) {

                ex.printStackTrace();
            }

        }
    }
        public static void main(String s[]) {

            Repaint sss = new Repaint();

            sss.setVisible(true);}

}
