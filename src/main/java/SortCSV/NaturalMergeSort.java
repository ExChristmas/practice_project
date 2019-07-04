package SortCSV;

import java.util.Comparator;

interface SortCSV {

    void sort(String fileNameSort, char separator, char quotechar,
              int number_line, char escapechar, String lineEnd);

}

public class NaturalMergeSort implements SortCSV {

    private String workFile1;
    private String workFile2;
    private Comparator<Row> comparator;

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
        this.workFile1 =  workFile1;
    }

    public void setWorkFile2(String workFile2) {
        this.workFile2 = workFile2;
    }



    public void sort(String fileNameSort, char separator, char quotechar,
                     int number_line, char escapechar, String lineEnd) {
        int s1, s2, a1, a2, mark;
        s1 = s2 = 1;
        while (s1 > 0 & s2 > 0) {
            mark = 1;
            s1 = 0;
            s2 = 0;
            DefaultReadWriter f = new DefaultReadWriter(fileNameSort, separator, quotechar,
                    number_line, escapechar, lineEnd);
            DefaultReadWriter f1 = new DefaultReadWriter(workFile1, separator, quotechar,
                    number_line, escapechar, lineEnd);
            DefaultReadWriter f2 = new DefaultReadWriter(workFile2, separator, quotechar,
                    number_line, escapechar, lineEnd);
            f.read();
            
        }
    }
}