package test;

import java.awt.event.MouseAdapter;import java.awt.event.MouseEvent;import java.awt.event.WindowAdapter;import java.awt.event.WindowEvent;
import javax.swing.JFrame;import javax.swing.JMenuItem;import javax.swing.JPopupMenu;
public class Menu {
    private static JFrame frm; private static JPopupMenu pm; private static JMenuItem mi1; private static JMenuItem mi2;
    public static void main(String args[]) {
        frm = new JFrame("Table");
        frm.setBounds(100, 100, 500, 400);
        pm = new JPopupMenu();
        mi1 = new JMenuItem("Copy");
        mi2 = new JMenuItem("Past");
        frm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {    if (e.getButton() == MouseEvent.BUTTON3) {     pm.add(mi1);
                pm.add(mi2);
                pm.show(frm, e.getX(), e.getY());    }
            }  });
        frm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);   }
        });
        frm.setVisible(true); }}

