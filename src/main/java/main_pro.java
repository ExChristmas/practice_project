import java.io.FileWriter;
import java.util.*;

import SortCSV.*;
import java.io.File;

public class main_pro {

    public static void main(String[] args) throws Exception {
//        DefaultReadWriter read_writer = new DefaultReadWriter("newfile.csv",
//                ',', '"', 0, '|', "\n");
//        List<Row> rows = new ArrayList<>();
//        List<String> first_row = read_writer.read();
//        List<String> components = new ArrayList<>();
//        Iterator<String> it_first_row;
//        Iterator<String> it_components;
//        boolean fl = true;
//        Map<String, String> row_temp = new LinkedHashMap<>();
//        Row row = new Row();
//        while(read_writer.hasNextLine()) {
//            it_first_row = first_row.iterator();
//            components.clear();
//            components = read_writer.read();
//            it_components = components.iterator();
//            for (int i = 0; i < first_row.size(); i++)
//                row_temp.put(it_first_row.next(), it_components.next());
//            rows.add(new Row(row_temp));
//            row_temp.clear();
//        }
//        for (Row value : rows) {
//            System.out.println(value.getValues());
//        }
//        read_writer.setFileName("newfileCopy.csv");
//        read_writer.write(first_row);
//        List<String> row_write = new ArrayList<>();
//        for (Iterator<Row> it = rows.iterator(); it.hasNext(); ) {
//            for (Map.Entry<String, String> entry : it.next().getValues().entrySet())
//                row_write.add(entry.getValue());
//            read_writer.write(row_write);
//            row_write.clear();
//        }
//        it_first_row = first_row.iterator();
//        String col = it_first_row.next();
//        col = it_first_row.next();
//        col = it_first_row.next();
//        System.out.println(col);
//        Comparator<Row> comparator = new DefaultRowComparator(col);
//        Iterator<Row> it_rows = rows.iterator();
//        Row[] columns_list = new Row[rows.size()];
//        for (int i = 0; i < rows.size(); i++)
//        columns_list[i] = it_rows.next();
//        System.out.println(comparator.compare(columns_list[0], columns_list[0]));
//        System.out.println(comparator.compare(columns_list[0], columns_list[1]));
//        System.out.println(comparator.compare(columns_list[0], columns_list[2]));
//        System.out.println(comparator.compare(columns_list[0], columns_list[3]));
//        Row row1;
//        Row row2;
//        row1 = new Row(columns_list[0]);
//        row2 = new Row(columns_list[1]);
//        System.out.println(row1.getValues());
//        System.out.println(row2.getValues());
//        columns_list[0].setValue("Id", "10");
//        columns_list[1].setValue("Id", "11");
//        System.out.println(row1.getValues());
//        System.out.println(row2.getValues());
//        List<String> a = new ArrayList<>();
//        List<String> b = new ArrayList<>();
//        a.add("1");
//        a.add("2");
//        a.add("3");
//        b.add("4");
//        b.add("5");
//        b.add("6");
//        System.out.println(a);
//        b.add("7");
//        System.out.println(a);
//
//        NaturalMergeSort sort = new NaturalMergeSort("s", "s");
//        Row row = sort.toRow(a, b);
//        System.out.println(row.getValues());
//        a.clear();
//        System.out.println(row.getValues());
//
//        DefaultReadWriter read_writer = new DefaultReadWriter("newfile.csv",
//                ',', '"', 0, '|', "\n");
//        a = read_writer.read();
//        System.out.println(a);
//        b = read_writer.read();
//        System.out.println(a);
//        System.out.println(b);



//        (new FileWriter(new File("newfileCopy.csv"))).write("");
//        NaturalMergeSort sort = new NaturalMergeSort("f1.csv", "f2.csv");
//        sort.sort("newfile.csv", "Id", ',', '"', 0, '|', "\n");
        File f = new File("data.csv");
    }
}