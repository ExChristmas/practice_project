package SortCSV;

import java.util.Comparator;

interface SortCSV {

    void sort(String fileNameSort, char separator, char quotechar,
              int number_line, char escapechar, String lineEnd);

}

public class NaturalMergeSort implements SortCSV {

    private String workFileName1;
    private String workFileName2;
    private Comparator<Row> comparator;

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

    private ReadWriterCSV createReadWriter(String nameFile, char separator, char quotechar,
                                      int number_line, char escapechar, String lineEnd) {
        return new DefaultReadWriter(nameFile, separator, quotechar, number_line, escapechar, lineEnd);
    }

    public void sort(String fileNameSort, char separator, char quotechar,
                     int number_line, char escapechar, String lineEnd) {
        int s1, s2, a1, a2, mark;
        s1 = s2 = 1;
        ReadWriterCSV f = createReadWriter(fileNameSort, separator, quotechar, number_line, escapechar, lineEnd);
        ReadWriterCSV f1 = createReadWriter(workFileName1, separator, quotechar, number_line, escapechar, lineEnd);
        ReadWriterCSV f2 = createReadWriter(workFileName2, separator, quotechar, number_line, escapechar, lineEnd);
        Row row1 = new Row();
        Row row2 = new Row();
        while (s1 > 0 & s2 > 0) {
            mark = 1;
            s1 = 0;
            s2 = 0;
            f.read();
        }
    }
}