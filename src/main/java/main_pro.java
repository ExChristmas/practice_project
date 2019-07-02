import java.util.*;

import SortCSV.*;

public class main_pro {

    public static void main(String[] args) throws Exception {
        Default_Read_Writer read_writer = new Default_Read_Writer("newfile.csv",
                ',', '"', 0, '|', "\n");
        List<Row> rows = new ArrayList<>();
        List<String> first_row = read_writer.read();
        List<String> components = new ArrayList<>();
        Iterator<String> it_first_row;
        Iterator<String> it_components;
        boolean fl = true;
        LinkedHashMap<String, String> row_temp = new LinkedHashMap<>();
        Row row = new Row();
        while(true) {
            it_first_row = first_row.iterator();
            components.clear();
            components = read_writer.read();
            it_components = components.iterator();
            if (components.size() == 0)
                break;
            for (int i = 0; i < first_row.size(); i++)
                row_temp.put(it_first_row.next(), it_components.next());
            rows.add(new Row(row_temp));
            row_temp.clear();
        }
        for (Row value : rows) {
            System.out.println(value.getValues());
        }
        read_writer.setFileName("newfileCopy.csv");
        read_writer.write(first_row);
        List<String> row_write = new ArrayList<>();
        for (Iterator<Row> it = rows.iterator(); it.hasNext(); ) {
            for (Map.Entry<String, String> entry : it.next().getValues().entrySet())
                row_write.add(entry.getValue());
            read_writer.write(row_write);
            row_write.clear();
        }
    }

}