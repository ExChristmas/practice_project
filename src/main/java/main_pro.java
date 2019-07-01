import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import SortCSV.Row;
import SortCSV.Default_Read_Writer;


public class main_pro {

    public static void main(String[] args) throws Exception {
//        CSVReader reader = new CSVReader(new FileReader("newfile.csv"), ',', '"', 0);
//        String[] firstLine = reader.readNext();
//        String[] nextLine = reader.readNext();
//        while ((nextLine = reader.readNext()) != null) {
//            if (nextLine != null) {
//                System.out.println(Arrays.toString(nextLine));
//            }
//        }
//        HashMap<String, String> m = new HashMap<String, String>();
//        m.put("A", "1");
//        Row r = new Row(m);
//        System.out.println(r.getValue("A"));
//
//        Default_Read_Writer read_writer = new Default_Read_Writer("newfile.csv",
//                ',', '"', 0);
//        System.out.println(read_writer.Read());

        CSVWriter csvWriter = new CSVWriter(new FileWriter("newfile.csv", true), ',', '"', '|', "\n");
        String[] strs = new String[10];
        for (int i = 0; i < 10; i++)
            strs[i] = "Alex";
        csvWriter.writeNext(strs);
        csvWriter.writeNext(strs);
        csvWriter.writeNext(strs);
        csvWriter.close();
//        ArrayList<String> row = read_writer.Read();
//        int sizeOfrow = row.size();
//        Iterator<String> it = row.iterator();
//        String[] row_write = new String[sizeOfrow];
//        for (int i = 0; i < sizeOfrow; i++)
//            row_write[i] = it.next();
//        csvWriter.writeNext(row_write);
    }
}