import java.io.IOException;
import java.util.*;

import sortcsv.*;

public class Main {

    public static void main(String[] args) throws Exception {
//        List<ReaderCSVSort> readers = new ArrayList<>();
//        readers.add(new DefaultReader("newfile.csv", ',', '"'));
//        readers.add(new DefaultReader("newfile.csv", ',', '"'));
//        WriterCSVSort writer = new DefaultWriter("workfile1.csv",
//                ',', '"', '|', "\n");
//        Comparator<Row> comparator = new DefaultRowComparator("Id");
//        SortCSV sortCSV = new NaturalMergeSort("workfile1.csv", "workfile2.csv");
//        sortCSV.sort("newfile.csv", comparator, readers, writer);
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "01234567890";
        List<String> row = new ArrayList<>();
        WriterCSVSort writer = new DefaultWriter("bigFile.csv", ',', '"', '|', "\n");
        row.add("1");
        row.add("2");
        row.add("3");
        row.add("4");
        row.add("5");
        row.add("6");
        row.add("7");
        writer.write(row);
        row.clear();
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            for(int j = 0; j < 6; j++) {
                row.add(alphabet.charAt());
            }
        }
    }
}