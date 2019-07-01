import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import SortCSV.Row;
import SortCSV.Default_Read_Writer;


public class main_pro {

    public static void main(String[] args) throws Exception {
//        Default_Read_Writer read_writer = new Default_Read_Writer("newfile.csv",
//                ',', '"', 0, '|', "\n");
//        ArrayList<String> arrayList = read_writer.Read();
//        read_writer.Write(arrayList);
//        read_writer.Write(arrayList);
//        read_writer.Write(arrayList);
        CSVWriter csvWriter = new CSVWriter(new FileWriter("newfile.csv", true),
                ',', '"', '|', "\n");
        String[] strs = new String[10];
        for (int i = 0; i < 10; i++)
            strs[i] = "Alex";
        csvWriter.writeNext(strs);
        csvWriter.writeNext(strs);
        csvWriter.writeNext(strs);
        csvWriter.close();
    }
}