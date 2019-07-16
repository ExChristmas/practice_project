import java.util.*;

import SortCSV.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<ReaderCSVSort> readers = new ArrayList<>();
        readers.add(new DefaultReader("newfile.csv", ',', '"'));
        readers.add(new DefaultReader("newfile.csv", ',', '"'));
        WriterCSVSort writer = new DefaultWriter("workfile1.csv",
                ',', '"', '|', "\n");
        Comparator<Row> comparator = new DefaultRowComparator("Id");
        SortCSV sortCSV = new NaturalMergeSort("workfile1.csv", "workfile2.csv");
        sortCSV.sort("newfile.csv", comparator, readers, writer);
    }
}