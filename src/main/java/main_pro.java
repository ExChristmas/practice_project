import java.io.FileReader;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class main_pro {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        CsvToBean csv = new CsvToBean();
        CSVReader reader = new CSVReader(new FileReader("newfile.csv"), ',', '"', 0);
        List<String> rows = new ArrayList<>();
        Map<Integer, String> map = new HashMap();
        String[] strs = reader.readNext();
//        for (String s : strs) {
//            for (int i = 0; i < s.length(); i++)
//                if ()
//            System.out.println(s);
//        }
//        String[] nextLine;
//        while ((nextLine = reader.readNext()) != null) {
//            if (nextLine != null) {
//                //Verifying the read data here
////                System.out.println(Arrays.toString(nextLine));
//                rows.add(Arrays.toString(nextLine));
//            }
//        }
//        for (Object obj : rows) {
//            System.out.println(obj);
//        }
    }
}