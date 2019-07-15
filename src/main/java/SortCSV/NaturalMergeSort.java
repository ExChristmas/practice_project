package SortCSV;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;


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
        private int s1;
        private int s2;
        private int mark;
        private List<String> end_range;
        private boolean fl_firstRow;

        Sorter() {
            row1 = new Row();
            row2 = new Row();
            end_range = new ArrayList<>();
            end_range.add("'");
        }

        void initialization() {
            this.s1 = 0;
            this.s2 = 0;
            this.mark = 1;
        }

        void separation(String fileNameSort, String workFile1, String workFile2, Comparator<Row> comparator,
                        List<ReaderCSVSort> readers, WriterCSVSort writer) {
            List<String> lst_read;
            Iterator<ReaderCSVSort> it_readers = readers.iterator();
            ReaderCSVSort reader = it_readers.next();
            reader.changeFile(fileNameSort);
            if (!fl_firstRow) {
                first_row = reader.read();
                fl_firstRow = true;
            }
            lst_read = reader.read();
            if (lst_read != null) { // file end check
                writer.changeFile(workFile1);
                writer.write(new ArrayList<>(lst_read)); // write first row in first file
                row1 = toRow(first_row, lst_read);
                lst_read = reader.read();
            }
            if (lst_read != null) { // file end check
                row2 = toRow(first_row, lst_read); // packaging second row values
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
                    s1++;
                } else {
                    writer.changeFile(workFile2);
                    writer.write(new ArrayList<>(row2.getRowValues()));
                    s2++;
                }
                row1 = new Row(row2);
                lst_read = reader.read();
                if (lst_read != null) {
                    row2 = toRow(first_row, lst_read);
                } else {
                    break;
                }
            }
            if (s2 > 0 && mark == 2) {
                writer.changeFile(workFile2);
                writer.write(end_range);
            }
            if (s1 > 0 && mark == 1) {
                writer.changeFile(workFile1);
                writer.write(end_range);
            }
        }

        void merge(String fileSort, String workFile1, String workFile2, Comparator<Row> comparator,
                   List<ReaderCSVSort> readers, WriterCSVSort writer) throws IOException {
            new FileWriter(new File(fileSort), false);
            Iterator<ReaderCSVSort> it_readers = readers.iterator();
            ReaderCSVSort reader1 = it_readers.next();
            ReaderCSVSort reader2 = it_readers.next();
            writer.changeFile(fileSort);
            reader1.changeFile(workFile1);
            List<String> lst_read1 = reader1.read();
            reader2.changeFile(workFile2);
            List<String> lst_read2 = reader2.read();

            if (lst_read1 != null) {
                row1 = toRow(first_row, lst_read1);
            }
            if (lst_read2 != null) {
                row2 = toRow(first_row, lst_read2);
            }

            boolean file1, file2;

            while (lst_read1 != null && lst_read2 != null) {
                file1 = file2 = false;
                while (!file1 && !file2) {
                    if (comparator.compare(row1, row2) <= 0) {
                        writer.write(new ArrayList<>(row1.getRowValues()));
                        lst_read1 = reader1.read();
                        if (lst_read1.equals(end_range)) {
                            file1 = true;
                            lst_read1 = reader1.read();
                            if (lst_read1 != null) {
                                row1 = toRow(first_row, lst_read1);
                            }
                        } else {
                            row1 = toRow(first_row, lst_read1);
                        }
                    } else {
                        writer.write(new ArrayList<>(row2.getRowValues()));
                        lst_read2 = reader2.read(); //
                        if (lst_read2.equals(end_range)) {
                            file2 = true;
                            lst_read2 = reader2.read();
                            if (lst_read2 != null) {
                                row2 = toRow(first_row, lst_read2);
                            }
                        } else {
                            row2 = toRow(first_row, lst_read2);
                        }
                    }
                }
                while (!file1) {
                    writer.write(new ArrayList<>(row1.getRowValues()));
                    lst_read1 = reader1.read();
                    if (lst_read1.equals(end_range)) {
                        file1 = true;
                        lst_read1 = reader1.read();
                        if (lst_read1 != null)
                            row1 = toRow(first_row, lst_read1);
                    } else {
                        row1 = toRow(first_row, lst_read1);
                    }
                }
                while (!file2) {
                    writer.write(new ArrayList<>(row2.getRowValues()));
                    lst_read2 = reader2.read();
                    if (lst_read2.equals(end_range)) {
                        file2 = true;
                        lst_read2 = reader2.read();
                        if (lst_read2 != null)
                            row2 = toRow(first_row, lst_read2);
                    } else {
                        row2 = toRow(first_row, lst_read2);
                    }
                }
            }
            file1 = file2 = false;
            while (!file1 && lst_read1 != null) {
                writer.write(new ArrayList<>(row1.getRowValues()));
                lst_read1 = reader1.read();
                if (lst_read1.equals(end_range)) { //
                    file1 = true;
                    lst_read1 = reader1.read();
                    if (lst_read1 != null) {
                        row1 = toRow(first_row, lst_read1);
                    }
                } else {
                    row1 = toRow(first_row, lst_read1); //
                }
            }
            while (!file2 && lst_read2 != null) {
                writer.write(new ArrayList<>(row2.getRowValues()));
                lst_read2 = reader2.read();
                if (lst_read2.equals(end_range)) { //
                    file2 = true;
                    lst_read2 = reader2.read();
                    if (lst_read2 != null) {
                        row2 = toRow(first_row, lst_read2);
                    }
                } else {
                    row2 = toRow(first_row, lst_read2); // stoped here!!!
                }
            }
             if ((new File(workFile1)).delete()) {
                 System.out.println("File don't found or don't can delete"); //
             }
             if ((new File(workFile2)).delete()) {
                 System.out.println("File don't found or don't can delete"); //
             }
        }
    }

    private Row toRow(List<String> cols, List<String> vals) { //added
        if (cols.size() == vals.size()) {
            Map<String, String> row = new LinkedHashMap<>();
            Iterator<String> it_cols = cols.iterator();
            Iterator<String> it_vals = vals.iterator();
            while (it_cols.hasNext())
                row.put(it_cols.next(), it_vals.next());
            return new Row(row);
        } else {
            System.out.println("Column sizes and values differ");
            return null;
        }
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
    public void sort (String fileNameSort, Comparator<Row> comparator, List<ReaderCSVSort> readers, WriterCSVSort writer) {
        Sorter sorter = new Sorter();
        int s1 = 1, s2 = 1;
        try {
            while (s1 > 0 && s2 > 0) {
                sorter.initialization();
                sorter.separation(fileNameSort, workFile1, workFile2, comparator, readers, writer);
                sorter.merge(fileNameSort, workFile1, workFile2, comparator, readers, writer);
                s1 = sorter.s1;
                s2 = sorter.s2;
            }
        } catch (IOException e) {
            e.printStackTrace();
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