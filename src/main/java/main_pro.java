import java.io.FileReader;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;

import SortCSV.Row;


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
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("A", "1");
        Row r = new Row(m);
        System.out.println(r.getValue("A"));
    }
}