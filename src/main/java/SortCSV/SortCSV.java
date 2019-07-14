package SortCSV;

import java.util.List;
import java.util.Comparator;

public interface SortCSV {

        void sort(String fileNameSort, Comparator<Row> comparator, List<ReaderCSVSort> readers, WriterCSVSort writer);

}
