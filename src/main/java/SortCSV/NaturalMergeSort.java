package SortCSV;

import java.util.*;

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
        this.workFileName1 =  workFile1;
    }

    public void setWorkFile2(String workFile2) {
        this.workFileName2 = workFile2;
    }

    public Row toRow(List<String> cols, List<String> vals) { //added
        Map<String, String> row = new LinkedHashMap<>();
        Iterator<String> it_cols = cols.iterator();
        Iterator<String> it_vals = vals.iterator();
        while(it_cols.hasNext())
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

    //deal whith comparation and reading!!!
    public void sort(String fileNameSort, String sortColumn, char separator, char quotechar,
                     int number_line, char escapechar, String lineEnd) {
        int s1, s2, a1, a2, mark;
        s1 = s2 = 1;
        ReadWriterCSV f = createReadWriter(fileNameSort, separator, quotechar,
                number_line, escapechar, lineEnd); // reader for main file
        ReadWriterCSV f1 = createReadWriter(workFileName1, separator, quotechar,
                number_line, escapechar, lineEnd); // reader for first file
        ReadWriterCSV f2 = createReadWriter(workFileName2, separator, quotechar,
                number_line, escapechar, lineEnd); // reader for second file
        Comparator<Row> comparator = new DefaultRowComparator(sortColumn); // comparator for compare rows
        List<String> lst_check = new ArrayList<>(); //for checking at the end of the file
        List<String> first_row = f.read(); // read name columuns
        Row row1, row2; // objects Row for compare
        while (s1 > 0 & s2 > 0) { //
            mark = 1; //
            s1 = 0; //
            s2 = 0;
            row1 = toRow(first_row, f.read()); // packaging first row values into main file
            lst_check = f.read();
            if (lst_check.size() != 0) // file end check
                f1.write(row1.getRowValues()); // write first row in first file
            lst_check = f.read(); // reaging next row
            if(lst_check.size() != 0) // file end check
                row2 = toRow(first_row, lst_check); // packaging row
            while (lst_check.size() != 0) { // file division
                 if (comparator.compare(row1, row2) < 0) {

                 }
            }
        }
    }
}