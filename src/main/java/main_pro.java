import java.util.*;

import SortCSV.*;

public class main_pro {

    public static void main(String[] args) throws Exception {
//        ReaderCSVSort reader = new DefaultReader("newfile.csv",
//                ',', '"');
//        List<Row> rows = new ArrayList<>();
//        List<String> first_row = reader.read();
//        List<String> components = new ArrayList<>();
//        Iterator<String> it_first_row;
//        Iterator<String> it_components;
//        boolean fl = true;
//        Map<String, String> row_temp = new LinkedHashMap<>();
//        Row row = new Row();
//        while(true) {
//            it_first_row = first_row.iterator();
//            components.clear();
//            components = reader.read();
//            if (components == null) {
//                break;
//            }
//            it_components = components.iterator();
//            for (int i = 0; i < first_row.size(); i++)
//                row_temp.put(it_first_row.next(), it_components.next());
//            rows.add(new Row(row_temp));
//            row_temp.clear();
//        }
//        for (Row value : rows) {
//            System.out.println(value.getValues());
//        }
//        WriterCSVSort writer = new DefaultWriter("newfileCopy.csv",
//                ',', '$', ' ', "\n");
//        writer.write(first_row);
//        List<String> row_write = new ArrayList<>();
//        for (Iterator<Row> it = rows.iterator(); it.hasNext(); ) {
//            for (Map.Entry<String, String> entry : it.next().getValues().entrySet())
//                row_write.add(entry.getValue());
//            writer.write(row_write);
//            row_write.clear();
//        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
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
//        File f = new File("data.csv");


////////////////////////////////////////////////!!!!!!!!!!!!!!!!!/////////////////////////
//        FileInputStream fis = new FileInputStream("newfile.csv");
//        InputStreamReader isr = new InputStreamReader(fis);
//
//
//        CSVReader reader = new CSVReader(isr, ',', ';', '|');
//
//        String[] cols, vals;
//        cols = reader.readNext();
//        vals = reader.readNext();
//        Map<String, String> values = new LinkedHashMap<>();
//        while (vals != null) {
//            List<String> temp = new ArrayList<>(Arrays.asList(vals));
//            for (int i = 0; i < cols.length; i++)
//                values.put(cols[i], vals[i]);
//            System.out.println(temp);
//            vals = reader.readNext();
//            if (vals == null)
//                break;
//        }
//
//        fis.close();
//
//        System.out.println(values);
//
//        int i = 0;
//        cols = new String[values.size()];
//        vals = new String[values.size()];
//        for (Map.Entry<String, String> entry : values.entrySet()) {
//            cols[i] = entry.getKey();
//            vals[i] = entry.getValue();
//            i++;
//        }
//
//        FileOutputStream fos = new FileOutputStream("newfileCopy.csv", true);
//        OutputStreamWriter osw = new OutputStreamWriter(fos);
//
//        CSVWriter writer = new CSVWriter(osw);
//
//        writer.writeNext(cols);
//        writer.writeNext(vals);
//        writer.close();

        List<ReaderCSVSort> readers = new ArrayList<>();
        readers.add(new DefaultReader("newfile.csv", ',', '"'));
        readers.add(new DefaultReader("newfile.csv", ',', '"'));
        WriterCSVSort writer = new DefaultWriter("workfile1.csv",
                ',', '"', '|', "\n");
        Comparator<Row> comparator = new DefaultRowComparator("Id");
        SortCSV sortCSV = new NaturalMergeSort("workfile1.csv", "workfile2.csv");
        sortCSV.sort("newfile.csv", comparator, readers, writer);
    }
}