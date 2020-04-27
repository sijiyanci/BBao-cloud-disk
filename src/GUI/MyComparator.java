package GUI;

import java.io.File;
import java.util.Comparator;

public class MyComparator {
    public static Comparator<File> dircomparator=new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            int result = o1.getName().compareTo(o2.getName());
            if (o1.isDirectory() && o2.isFile()) {
                result = -1;
            } else if (o2.isDirectory() && o1.isFile()) {
                result = 1;
            }
            return result;
        }
    };
}
