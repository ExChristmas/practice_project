import java.io.IOException;
import java.util.*;

import sortcsv.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<ReaderCSVSort> readers = new ArrayList<>();
        readers.add(new DefaultReader("bigFile.csv", ',', '"'));
        readers.add(new DefaultReader("bigFile.csv", ',', '"'));
        WriterCSVSort writer = new DefaultWriter("workfile1.csv",
                ',', '"', '|', "\n");
        Comparator<Row> comparator = new DefaultRowComparator("1");
        SortCSV sortCSV = new NaturalMergeSort("workfile1.csv", "workfile2.csv");
        sortCSV.sort("bigFile.csv", comparator, readers, writer);
//        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
//                "01234567890";
//        List<String> row = new ArrayList<>();
//        WriterCSVSort writer = new DefaultWriter("bigFile.csv", ',', '"', '|', "\n");
//        Random rnd = new Random();
//        row.add("1");
//        row.add("2");
//        row.add("3");
//        row.add("4");
//        row.add("5");
//        row.add("6");
//        row.add("7");
//        writer.write(row);
//        row.clear();
//        StringBuilder val = new StringBuilder();
//        for(int i = 0; i < 50000; i++) {
//            for(int j = 0; j < 7; j++) {
//                for(int k = 0; k < 9; k++) {
//                    val.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
//                }
//                row.add(val.toString());
//                val = new StringBuilder();
//            }
//            writer.write(row);
//            row.clear();
//        }
//        for(int k = 0; k < 9; k++) {
//            val.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
//        }
//        row.add(val.toString());
//        val = new StringBuilder();
    }
}