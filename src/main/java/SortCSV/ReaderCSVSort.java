package SortCSV;

import java.util.List;

public interface ReaderCSVSort {

    void changeFile(String fileName);

    List<String> read();

}