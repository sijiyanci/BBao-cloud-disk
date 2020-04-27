package test;

import java.awt.CardLayout;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;


import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JPanel;


public class Change implements MouseListener {

    static JButton bt1;

    static JButton bt2;

    static JButton bt3;

    static JPanel card;

    static CardLayout cl;

    public Change() {

        bt1 = new JButton("按钮1");

        bt2 = new JButton("按钮2");

        bt3 = new JButton("按钮3");

        bt1.addMouseListener(this);

        bt2.addMouseListener(this);

        bt3.addMouseListener(this);

        card = new JPanel();

        cl = new CardLayout();

    }

    public static void main(String[] args) {

        JFrame jf = new JFrame("界面切换");


        Change t = new Change();


        card.setLayout(cl);

        card.add(bt1);

        card.add(bt2);

        card.add(bt3);


        jf.add(card);

        jf.setBounds(400, 100, 200, 100);

        jf.setDefaultCloseOperation(3);

        jf.setVisible(true);

    }


    @Override

    public void mouseClicked(MouseEvent arg0) {

        // TODO Auto-generated method stub

        if (arg0.getSource() == bt1) {

            cl.next(card);

        } else if (arg0.getSource() == bt2) {

            cl.next(card);

        } else if (arg0.getSource() == bt3) {

            cl.first(card);

        } else {


        }

    }


    @Override

    public void mouseEntered(MouseEvent arg0) {

        // TODO Auto-generated method stub


    }


    @Override

    public void mouseExited(MouseEvent arg0) {

        // TODO Auto-generated method stub


    }


    @Override

    public void mousePressed(MouseEvent arg0) {

        // TODO Auto-generated method stub


    }


    @Override

    public void mouseReleased(MouseEvent arg0) {

        // TODO Auto-generated method stub


    }

}
