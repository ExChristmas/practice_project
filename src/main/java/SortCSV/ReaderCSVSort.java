package SortCSV;

import java.util.List;

public interface ReaderCSVSort {

    boolean hasNextLine();

    List<String> read();

}