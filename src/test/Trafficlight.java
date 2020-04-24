package test;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Trafficlight extends JFrame{
    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();

        panel1.setBackground(new Color(255,255,255));
        panel1.setBounds(205, 70, 90, 210);
        panel1.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        //panel1.setPreferredSize(new Dimension(100,100));
        //panel1.setBorder(BorderFactory.createLineBorder(Color.red));
        //add(panel1);
        this.getContentPane().add(panel1);
        panel1.setLayout(null);
        JavaJF circle1=new JavaJF(50);
        circle1.setBounds(20, 15, 50, 50);
        circle1.setBackground(Color.white);
        JavaJF circle2=new JavaJF(50);
        circle2.setBounds(20, 80, 50, 50);
        JavaJF circle3=new JavaJF(50);
        circle3.setBounds(20, 145, 50, 50);
        JavaJF []circle= {circle1,circle2,circle3};
        panel1.add(circle1);
        panel1.add(circle2);
        panel1.add(circle3);
        JPanel panel2=new JPanel();
        //panel2.setBackground(new Color(255,255,255));
        panel2.setBounds(150, 300, 200, 40);
        //panel2.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        this.getContentPane().add(panel2);
		/*JRadioButton jr1 = new JRadioButton();
		JRadioButton jr2 = new JRadioButton();
		JRadioButton jr3 = new JRadioButton();*/
        //jr1.setPreferredSize(new Dimension(30,30));
        String []color= {"Red","Yellow","Green"};
        Color []color1= {Color.red,Color.yellow,Color.green};
        JRadioButton []jr=new JRadioButton[3];
        ButtonGroup btnGroup = new ButtonGroup();
        for(int i=0;i<jr.length;i++) {
            JRadioButton temp=new JRadioButton(color[i]);
            JavaJF tempjf=circle[i];
            Color tempc=color1[i];
            temp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (temp.isSelected()) {
                        System.out.println(temp.getText());
                        for(int j=0;j<3;j++) {
                            if(!tempjf.equals(circle[j])) {
                                circle[j].c=Color.white;
                            }
                        }
                        tempjf.c=tempc;
                        panel1.repaint();
                        //circle1.setBackground(Color.red);

                    }

                }
            });
            jr[i]=temp;
            btnGroup.add(jr[i]);
        }
        jr[0].setSelected(true);
        circle[0].c=color1[0];

        //jr1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        panel2.add(jr[0]);
        //panel2.add(l1);
        panel2.add(jr[1]);
        //panel2.add(l2);
        panel2.add(jr[2]);
        //panel2.add(l3);
    }

    public static void main(String[] arg) {
        Trafficlight jframe=new Trafficlight();
        jframe.setBounds(100, 100, 500, 500);
        //jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.getContentPane().setLayout(null);
        jframe.init();
        jframe.setVisible(true);
    }
}
class JavaJF extends JPanel{

    private int r;
    public Color c;
    public JavaJF(int r) {
        c=Color.white;
        this.r=r;
    }
    public void paint(Graphics g) {
	/*	 if (this.getModel().isArmed()) {
		      g.setColor(java.awt.SystemColor.controlHighlight);
		    } else {*/
        g.setColor(c);
        g.fillOval(0, 0, r, r);
        g.setColor(Color.black);
        g.drawOval(0, 0, r, r);

    }
}

