import java.io.FileReader;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;

import SortCSV.Row;
import SortCSV.Default_Read_Writer;

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

        Default_Read_Writer read_writer = new Default_Read_Writer("newfile.csv", ',', '"');
        int it = 0;
        while (it < 2) {
            String[] row = read_writer.Read();
            for (int i = 0; i < row.length; i++)
                System.out.print(row[i] + " ");
            System.out.print("\n");
            it++;
        }
    }
}