package test;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Main1 {
    static String labels[] = { "A", "B", "C", "D", "E", "F", "G" };

    public static void main(String args[]) {
        JFrame frame = new JFrame("Modifying Model");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fill model
        final DefaultListModel model = new DefaultListModel();
        for (int i = 0, n = labels.length; i < n; i++) {
            model.addElement(labels[i]);
        }
        JList jlist = new JList(model);
        JScrollPane scrollPane1 = new JScrollPane(jlist);
        frame.add(scrollPane1, BorderLayout.CENTER);


        ListDataListener listDataListener = new ListDataListener() {
            public void contentsChanged(ListDataEvent listDataEvent) {
                appendEvent(listDataEvent);
            }

            public void intervalAdded(ListDataEvent listDataEvent) {
                appendEvent(listDataEvent);
            }

            public void intervalRemoved(ListDataEvent listDataEvent) {
                appendEvent(listDataEvent);
            }

            private void appendEvent(ListDataEvent listDataEvent) {
                switch (listDataEvent.getType()) {
                    case ListDataEvent.CONTENTS_CHANGED:
                        System.out.println("Type: Contents Changed");
                        break;
                    case ListDataEvent.INTERVAL_ADDED:
                        System.out.println("Type: Interval Added");
                        break;
                    case ListDataEvent.INTERVAL_REMOVED:
                        System.out.println("Type: Interval Removed");
                        break;
                }
                DefaultListModel theModel = (DefaultListModel) listDataEvent.getSource();
                System.out.println(theModel);
            }
        };

        model.addListDataListener(listDataListener);

        JButton jb = new JButton("add F");

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                model.add(0, "First");
            }
        });

        frame.add(jb,BorderLayout.SOUTH);

        frame.setSize(640, 300);
        frame.setVisible(true);
    }
} 