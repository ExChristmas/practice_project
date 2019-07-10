package SortCSV;

import java.util.List;

interface ReaderCSVSort {

    boolean hasNextLine();

    List<String> read();

}