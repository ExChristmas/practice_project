package SortCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;


public class NaturalMergeSort implements SortCSV {

    private String workFile1;
    private String workFile2;

    public NaturalMergeSort(String workFile1, String workFile2) {
        this.workFile1 = workFile1;
        this.workFile2 = workFile2;
    }

    public String getWorkFile1() {
        return this.workFile1;
    }

    public String getWorkFile2() {
        return this.workFile2;
    }

    public void setWorkFile1(String workFile1) {
        this.workFile1 = workFile1;
    }

    public void setWorkFile2(String workFile2) {
        this.workFile2 = workFile2;
    }

    private class Sorter {

        private Row row1;
        private Row row2;
        private List<String> first_row;
        private List<String> lst_read;
        private int s1;
        private int s2;
        private int mark;
        private List<String> end_range;

        Sorter() {
            row1 = new Row();
            row2 = new Row();
            lst_read = new ArrayList<>();
            this.s1 = 0;
            this.s2 = 0;
            this.mark = 1;
            end_range = new ArrayList<>();
            end_range.add("'");
        }

        void separation(String workFile1, String workFile2, Comparator<Row> comparator,
                        ReaderCSVSort reader, WriterCSVSort writer) {
            lst_read = reader.read();
            if (lst_read != null) { // file end check
                writer.changeFile(workFile1);
                writer.write(new ArrayList<>(lst_read)); // write first row in first file
                row1 = toRow(first_row, lst_read);
                lst_read = reader.read();
            }
            if (lst_read != null) { // file end check
                row2 = toRow(first_row, lst_read); // packaging second row values
                lst_read = reader.read();
            }
            while (lst_read != null) { // file division
                if (comparator.compare(row1, row2) > 0) {
                    switch (mark) {
                        case 1: {
                            writer.write(end_range);
                            mark = 2;
                            s1++;
                            break;
                        }
                        case 2: {
                            writer.write(end_range);
                            mark = 1;
                            s2++;
                            break;
                        }
                    }
                }
                if (mark == 1) {
                    writer.changeFile(workFile1);
                    writer.write(new ArrayList<>(row2.getRowValues()));
                } else {
                    writer.changeFile(workFile2);
                    writer.write(new ArrayList<>(row2.getRowValues()));
                }
                row1 = new Row(row2);
                lst_read = reader.read();
                if (lst_read != null) {
                    row2 = toRow(first_row, lst_read);
                } else {
                    break;
                }
            }
            if (s2 > 0 & mark == 2) {
                writer.changeFile(workFile2);
                writer.write(end_range);
            }
            if (s1 > 0 & mark == 1) {
                writer.changeFile(workFile1);
                writer.write(end_range);
            }
        }

//        void merge(ReaderCSVSort reader, WriterCSVSort writer) {
//
//
//            if (f1.hasNextLine()) {
//                row1 = toRow(first_row, f1.read());
//            }
//            if (f2.hasNextLine()) {
//                row2 = toRow(first_row, f2.read());
//            }
//
//            boolean file1, file2, hasN1, hasN2;
//
//            while (true) {
//                file1 = file2 = false;
//                //hasN1 = f1.hasNextLine();
//                //hasN2 = f2.hasNextLine();
//                while (!file1 && !file2) {
//                    if (comparator.compare(row1, row2) <= 0) {
//                        f.write(new ArrayList<>(row1.getRowValues()));
//                        lst_read = f1.read();
//                        if (lst_read.equals(end_range)) {
//                            file1 = true;
//                            if (f1.hasNextLine()) {
//                                row1 = toRow(first_row, f1.read());
//                            } else {
//                                hasN1 = true;
//                                break;
//                            }
//                        } else {
//                            row1 = toRow(first_row, lst_read);
//                        }
//                    } else {
//                        f.write(new ArrayList<>(row2.getRowValues()));
//                        lst_read = f2.read(); //
//                        row2 = toRow(first_row, lst_read); //
//                        if (lst_read.equals(end_range)) {
//                            row2 = toRow(first_row, f2.read());
//                            file2 = true;
//                        } else {
//                            row2 = toRow(first_row, lst_read);
//                        }
//                    }
//                }
//                while (!file1) {
//                    f.write(new ArrayList<>(row1.getRowValues()));
//                    lst_read = f1.read();
//                    if (lst_read.equals(end_range)) {
//                        row1 = toRow(first_row, f1.read());
//                        file1 = true;
//                    } else {
//                        row1 = toRow(first_row, lst_read);
//                    }
//                }
//                while (!file2) {
//                    f.write(new ArrayList<>(row2.getRowValues()));
//                    lst_read = f2.read();
//                    if (lst_read.equals(end_range)) {
//                        row2 = toRow(first_row, f2.read());
//                        file2 = true;
//                    } else {
//                        row2 = toRow(first_row, lst_read);
//                    }
//                }
//            }
//            file1 = file2 = false;
//            while (!file1 & f1.hasNextLine()) {
//                f.write(new ArrayList<>(row1.getRowValues()));
//                if ((lst_read = f1.read()).equals(end_range)) //
//                    file1 = true;
//                row1 = toRow(first_row, lst_read); //
//            }
//            while (!file2 & f2.hasNextLine()) {
//                f.write(new ArrayList<>(row2.getRowValues()));
//                if ((lst_read = f1.read()).equals(end_range)) //
//                    file2 = true;
//                row2 = toRow(first_row, lst_read); //
//            }
//        }//
//            (new File(workFileName1)).delete(); //
//            (new File(workFileName2)).delete(); //
        }

    private Row toRow(List<String> cols, List<String> vals) { //added
        Map<String, String> row = new LinkedHashMap<>();
        Iterator<String> it_cols = cols.iterator();
        Iterator<String> it_vals = vals.iterator();
        System.out.println(cols.size());
        System.out.println(vals.size());
        while (it_cols.hasNext())
            row.put(it_cols.next(), it_vals.next());
        return new Row(row);
    }

    private List<String> toList(String[] row) { //added (need?)
        int len_row = row.length;
        return new ArrayList<>(Arrays.asList(row).subList(0, len_row));
    }

    private ReadWriterCSV createReadWriter(String nameFile, char separator, char quotechar,
                                           int number_line, char escapechar, String lineEnd) {
        return new DefaultReadWriter(nameFile, separator, quotechar, number_line, escapechar, lineEnd);
    }

    private void equalList(List<String> lst1, List<String> lst2) {
        lst1.clear();
        lst1.addAll(lst2);
    }

    @Override
    public void sort (String fileNameSort, Comparator<Row> comparator, ReaderCSVSort reader, WriterCSVSort writer) {
        Sorter sorter = new Sorter();
        while(sorter.s1 > 0 && sorter.s2 > 0) {
            sorter.separation(workFile1, workFile2, comparator, reader, writer);
        }
    }
//
//    public void sort(String fileNameSort, String sortColumn, char separator, char quotechar,
//                     int number_line, char escapechar, String lineEnd) {
//        try {
//            int s1, s2, mark;
//            s1 = s2 = 1;
//            List<String> end_range = new ArrayList<>();
//            end_range.add("'");
//            Comparator<Row> comparator = new DefaultRowComparator(sortColumn); // comparator for compare rows
//            List<String> first_row = f.read(); // read name columuns
//            List<String> lst_read;
//            Row row1;// objects Row for compare
//            Row row2 = new Row();
//            while (s1 > 0 & s2 > 0) { //
//                mark = 1; //
//                s1 = 0; //
//                s2 = 0;
//                row1 = toRow(first_row, f.read()); // packaging first row values
//                if (f.hasNextLine()) // file end check
//                    f1.write(new ArrayList<>(row1.getRowValues())); // write first row in first file
//                if (f.hasNextLine()) // file end check
//                    row2 = toRow(first_row, f.read()); // packaging second row values
//                while (true) { // file division
//                    if (comparator.compare(row1, row2) > 0) {
//                        switch (mark) {
//                            case 1: {
//                                f1.write(end_range);
//                                mark = 2;
//                                s1++;
//                                break;
//                            }
//                            case 2: {
//                                f2.write(end_range);
//                                mark = 1;
//                                s2++;
//                                break;
//                            }
//                        }
//                    }
//                    if (mark == 1) {
//                        f1.write(new ArrayList<>(row2.getRowValues()));
//                    } else {
//                        f2.write(new ArrayList<>(row2.getRowValues()));
//                    }
//                    row1 = new Row(row2);
//                    if (!f.hasNextLine()) break;
//                    row2 = toRow(first_row, f.read());
//                }
//                if (s2 > 0 & mark == 2) {
//                    f2.write(end_range);
//                }
//                if (s1 > 0 & mark == 1) {
//                    f1.write(end_range);
//                }
//
//                //clearing main file
//                new FileWriter(new File("newfileCopy.csv"), false);
//
//                if (f1.hasNextLine()) {
//                    row1 = toRow(first_row, f1.read());
//                }
//                if (f2.hasNextLine()) {
//                    row2 = toRow(first_row, f2.read());
//                }
//
//                boolean file1, file2, hasN1, hasN2;
//
//                while (true) {
//                    file1 = file2 = false;
//                    //hasN1 = f1.hasNextLine();
//                    //hasN2 = f2.hasNextLine();
//                    while (!file1 && !file2) {
//                        if (comparator.compare(row1, row2) <= 0) {
//                            f.write(new ArrayList<>(row1.getRowValues()));
//                            lst_read = f1.read();
//                            if (lst_read.equals(end_range)) {
//                                file1 = true;
//                                if (f1.hasNextLine()) {
//                                    row1 = toRow(first_row, f1.read());
//                                } else {
//                                    hasN1 = true;
//                                    break;
//                                }
//                            } else {
//                                row1 = toRow(first_row, lst_read);
//                            }
//                        } else {
//                            f.write(new ArrayList<>(row2.getRowValues()));
//                            lst_read = f2.read(); //
//                            row2 = toRow(first_row, lst_read); //
//                            if (lst_read.equals(end_range)) {
//                                row2 = toRow(first_row, f2.read());
//                                file2 = true;
//                            } else {
//                                row2 = toRow(first_row, lst_read);
//                            }
//                        }
//                    }
//                    while (!file1) {
//                        f.write(new ArrayList<>(row1.getRowValues()));
//                        lst_read = f1.read();
//                        if (lst_read.equals(end_range)) {
//                            row1 = toRow(first_row, f1.read());
//                            file1 = true;
//                        } else {
//                            row1 = toRow(first_row, lst_read);
//                        }
//                    }
//                    while (!file2) {
//                        f.write(new ArrayList<>(row2.getRowValues()));
//                        lst_read = f2.read();
//                        if (lst_read.equals(end_range)) {
//                            row2 = toRow(first_row, f2.read());
//                            file2 = true;
//                        } else {
//                            row2 = toRow(first_row, lst_read);
//                        }
//                    }
//                }
//                file1 = file2 = false;
//                while (!file1 & f1.hasNextLine()) {
//                    f.write(new ArrayList<>(row1.getRowValues()));
//                    if ((lst_read = f1.read()).equals(end_range)) //
//                        file1 = true;
//                    row1 = toRow(first_row, lst_read); //
//                }
//                while (!file2 & f2.hasNextLine()) {
//                    f.write(new ArrayList<>(row2.getRowValues()));
//                    if ((lst_read = f1.read()).equals(end_range)) //
//                        file2 = true;
//                    row2 = toRow(first_row, lst_read); //
//                }
//            }//
//            (new File(workFileName1)).delete(); //
//            (new File(workFileName2)).delete(); //
//        } catch (IOException e) {
//            e.printStackTrace();
//            }
    }
}