package SortCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

interface SortCSV {

    void sort(String fileNameSort, String sortColumn, char separator, char quotechar,
              int number_line, char escapechar, String lineEnd);

}

public class NaturalMergeSort implements SortCSV {

    private String workFileName1;
    private String workFileName2;

    public NaturalMergeSort(String workFile1, String workFile2) {
        this.workFileName1 = workFile1;
        this.workFileName2 = workFile2;
    }

    public String getWorkFile1() {
        return this.workFileName1;
    }

    public String getWorkFile2() {
        return this.workFileName2;
    }

    public void setWorkFile1(String workFile1) {
        this.workFileName1 = workFile1;
    }

    public void setWorkFile2(String workFile2) {
        this.workFileName2 = workFile2;
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

    public void sort(String fileNameSort, String sortColumn, char separator, char quotechar,
                     int number_line, char escapechar, String lineEnd) {
        try {
            int s1, s2, mark;
            s1 = s2 = 1;
            List<String> end_range = new ArrayList<>();
            end_range.add("'");
            ReadWriterCSV f = createReadWriter(fileNameSort, separator, quotechar,
                    number_line, escapechar, lineEnd); // reader for main file
            ReadWriterCSV f1 = createReadWriter(workFileName1, separator, quotechar,
                    number_line, escapechar, lineEnd); // reader for first file
            ReadWriterCSV f2 = createReadWriter(workFileName2, separator, quotechar,
                    number_line, escapechar, lineEnd); // reader for second file
            Comparator<Row> comparator = new DefaultRowComparator(sortColumn); // comparator for compare rows
            List<String> first_row = f.read(); // read name columuns
            List<String> lst_read;
            Row row1 = new Row();// objects Row for compare
            Row row2 = new Row();
            while (s1 > 0 & s2 > 0) { //
                mark = 1; //
                s1 = 0; //
                s2 = 0;
                row1 = toRow(first_row, f.read()); // packaging first row values
                if (f.hasNextLine()) // file end check
                    f1.write(new ArrayList<>(row1.getRowValues())); // write first row in first file
                if (f.hasNextLine()) // file end check
                    row2 = toRow(first_row, f.read()); // packaging second row values
                while (true) { // file division
                    if (comparator.compare(row1, row2) > 0) {
                        switch (mark) {
                            case 1: {
                                f1.write(end_range);
                                mark = 2;
                                s1++;
                                break;
                            }
                            case 2: {
                                f2.write(end_range);
                                mark = 1;
                                s2++;
                                break;
                            }
                        }
                    }
                    if (mark == 1) {
                        f1.write(new ArrayList<>(row2.getRowValues()));
                    } else {
                        f2.write(new ArrayList<>(row2.getRowValues()));
                    }
                    row1 = new Row(row2);
                    if (!f.hasNextLine()) break;
                    row2 = toRow(first_row, f.read());
                }
                if (s2 > 0 & mark == 2) {
                    f2.write(end_range);
                }
                if (s1 > 0 & mark == 1) {
                    f1.write(end_range);
                }

                //clearing main file
                new FileWriter(new File("newfileCopy.csv"), false);

                if (f1.hasNextLine()) {
                    row1 = toRow(first_row, f1.read());
                }
                if (f2.hasNextLine()) {
                    row2 = toRow(first_row, f2.read());
                }
                boolean file1, file2;

                //------------------- TODO ----------------------------------

                while (true) {/////////////
                    file1 = file2 = false;
                    boolean hasN1 = f1.hasNextLine();
                    boolean hasN2 = f2.hasNextLine();
                    while (!file1 && !file2) {
                        if (comparator.compare(row1, row2) <= 0) {
                            f.write(new ArrayList<>(row1.getRowValues()));
                            lst_read = f1.read(); //
                            if (lst_read.equals(end_range)) {
                                row1 = toRow(first_row, f1.read()); //
                                file1 = true;
                            } else {
                                row1 = toRow(first_row, lst_read);
                            }
                        } else {
                            f.write(new ArrayList<>(row2.getRowValues()));
                            lst_read = f2.read(); //
                            row2 = toRow(first_row, lst_read); //
                            if (lst_read.equals(end_range)) //
                                file2 = true;
                        }
                    }
                    while (!file1) {
                        f.write(new ArrayList<>(row1.getRowValues()));
                        if ((lst_read = f1.read()).equals(end_range)) //
                            file1 = true;
                        row1 = toRow(first_row, lst_read); //
                    }
                    while (!file2) {
                        f.write(new ArrayList<>(row2.getRowValues()));
                        if ((lst_read = f2.read()).equals(end_range)) //
                            file2 = true;
                        row2 = toRow(first_row, lst_read); //
                    }
                }
                file1 = file2 = false;
                while (!file1 & f1.hasNextLine()) {
                    f.write(new ArrayList<>(row1.getRowValues()));
                    if ((lst_read = f1.read()).equals(end_range)) //
                        file1 = true;
                    row1 = toRow(first_row, lst_read); //
                }
                while (!file2 & f2.hasNextLine()) {
                    f.write(new ArrayList<>(row2.getRowValues()));
                    if ((lst_read = f1.read()).equals(end_range)) //
                        file2 = true;
                    row2 = toRow(first_row, lst_read); //
                }
            }//
            (new File(workFileName1)).delete(); //
            (new File(workFileName2)).delete(); //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}