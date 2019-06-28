import java.io.FileReader;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;

import SortCSV.Row;

import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

public class main_pro {

    public static void main(String[] args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("newfile.csv"), ',', '"', 0);
        String[] firstLine = reader.readNext();
        String[] nextLine = reader.readNext();
//        while ((nextLine = reader.readNext()) != null) {
//            if (nextLine != null) {
//                System.out.println(Arrays.toString(nextLine));
//            }
//        }

        Row a1 = new Row(firstLine, nextLine);
        System.out.println(a1.getValues());
        System.out.println(a1.getValues().get(" id"));
    }
}