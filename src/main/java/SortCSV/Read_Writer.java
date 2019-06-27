//package SortCSV;
//
//import au.com.bytecode.opencsv.CSVReader;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import au.com.bytecode.opencsv.CSVReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Read_Writer {
//
//    public String fileName;
//    public char separator;
//    public char quotechar;
//
//    private File file = new File(fileName);
//
////    Read_Writer() {}
//
//    public List<String> readFirst() {
//        try {
//            List<String> component = new ArrayList<>();
//            FileReader reader = new FileReader(file);
//            CSVReader readerCSV = new CSVReader(reader, separator, quotechar, 0);
//            readerCSV.
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public String readRow() {
//        try {
//            FileReader reader = new FileReader(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
////        return row;
//    }
//
//    public void writeRow() {
//        try {
//            FileWriter writer = new FileWriter(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}