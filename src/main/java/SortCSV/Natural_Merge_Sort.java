package SortCSV;

import java.io.File;

interface SortCSV {

    void sort(String fileNameSort, char separator, char quotechar,
              int number_line, char escapechar, String lineEnd);
}

public class Natural_Merge_Sort implements SortCSV {

    private String workFile1;
    private String workFile2;

    public Natural_Merge_Sort(String workFile1, String workFile2) {
        this.workFile1 = workFile1;
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
            Default_Read_Writer f = new Default_Read_Writer(fileNameSort, separator, quotechar,
                                                        number_line, escapechar, lineEnd);
            Default_Read_Writer f1 = new Default_Read_Writer(workFile1, separator, quotechar,
                    number_line, escapechar, lineEnd);
            Default_Read_Writer f2 = new Default_Read_Writer(workFile2, separator, quotechar,
                    number_line, escapechar, lineEnd);
        }
    }
}