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
        Default_Read_Writer read_writer = new Default_Read_Writer("newfile.csv",
                ',', '"', 0, '|', "\n");
        ArrayList<Row> rows = new ArrayList<>();
        ArrayList<String> first_row = read_writer.Read();
        ArrayList<String> components = new ArrayList<>();
        Iterator<String> it_first_row = first_row.iterator();
        Iterator<String> it_components = components.iterator();
        boolean fl = true;
        HashMap<String, String> row_temp = new HashMap<>();
        Row row = new Row();
        while(true) {
            components.clear();
            components = read_writer.Read();
            if (components.size() == 0)
                break;
            for (int i = 0; i < first_row.size(); i++)
                row_temp.put(it_first_row.next(), it_components.next());
            rows.add(new Row(row_temp));
            row_temp.clear();
            it_first_row = first_row.iterator();
            it_components = components.iterator();
        }
    }
}