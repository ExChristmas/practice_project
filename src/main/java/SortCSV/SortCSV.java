package SortCSV;

import java.util.Comparator;

public interface SortCSV {

        void sort(String fileNameSort, Comparator<Row> comparator, ReaderCSVSort reader, WriterCSVSort writer);

}
