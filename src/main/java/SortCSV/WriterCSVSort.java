package SortCSV;

import java.util.List;

public interface WriterCSVSort {

    void changeFile(String fileName);

    void write(List<String> row);

}