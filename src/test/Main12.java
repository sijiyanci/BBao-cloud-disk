
package test;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Main12 extends JPanel {
    private static JPopupMenu pm;
    private static JMenuItem mi1;
    private static JMenuItem mi2;
    private BookEntry books[] = {
            new BookEntry("Ant: The Definitive Guide", "./public/result/p1.jpg"),
            new BookEntry("Database Programming with JDBC and Java",
                    "2.gif"),
            new BookEntry("Developing Java Beans", "3.gif"),
            new BookEntry("Developing JSP Custom Tag Libraries",
                    "4.gif"),
            new BookEntry("Java 2D Graphics", "4.gif"),
            new BookEntry("Java and XML", "5.gif"),
            new BookEntry("Java and XSLT", "1.gif"),
            new BookEntry("Java and SOAP", "2.gif"),
            new BookEntry("Learning Java", "3.gif") };

    private JList booklist = new JList(books);

    public Main12() {
        pm = new JPopupMenu();
        mi1 = new JMenuItem("Copy");
        mi2 = new JMenuItem("Past");
        pm.add(mi1);
        pm.add(mi2);
        setLayout(new BorderLayout());
        JButton button = new JButton("Print");
  //      button.addActionListener(new PrintListener());

        booklist = new JList(books);
        booklist.setCellRenderer(new BookCellRenderer());
        booklist.setVisibleRowCount(10);
        booklist.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                booklist.setSelectedIndex(booklist.locationToIndex(e.getPoint()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()&&booklist.getSelectedIndex()!=-1) {

                    //获取选择项的值
                    Object selected = booklist.getModel().getElementAt(booklist.getSelectedIndex());
                    System.out.println(selected);
                    pm.show(e.getComponent(),e.getX(), e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane pane = new JScrollPane(booklist);

        add(pane, BorderLayout.NORTH);
        add(button, BorderLayout.SOUTH);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Main12());
        frame.pack();
        frame.setVisible(true);
    }

    // An inner class to respond to clicks on the Print button
    /*class PrintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selected[] = booklist.getSelectedIndices();
            System.out.println("Selected Elements:  ");

            for (int i = 0; i < selected.length; i++) {
                BookEntry element = (BookEntry) booklist.getModel()
                        .getElementAt(selected[i]);
                System.out.println("  " + element.getTitle());
            }
        }
    }*/
}

class BookEntry {
    private final String title;

    private final String imagePath;

    private ImageIcon image;

    public BookEntry(String title, String imagePath) {
        this.title = title;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public ImageIcon getImage() {
        if (image == null) {
            image = new ImageIcon(imagePath);
        }
        return image;
    }

    // Override standard toString method to give a useful result
    public String toString() {
        return title;
    }
}

class BookCellRenderer extends JLabel implements ListCellRenderer {
    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);


    public BookCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);


    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        ListModel temp=list.getModel();
        System.out.println(temp.getElementAt(1) instanceof BookEntry);
        BookEntry entry = (BookEntry) value;
        setText(entry.getTitle());

        if (isSelected) {
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);

           
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }
}

