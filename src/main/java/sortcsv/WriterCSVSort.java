package sortcsv;

import java.io.IOException;
import java.util.List;

public interface WriterCSVSort {

    void closeConnection() throws IOException;

    void changeFile(String fileName);

    void write(List<String> row);
}