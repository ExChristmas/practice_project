package sortcsv;

import java.io.IOException;
import java.util.List;

public interface ReaderCSVSort {

    void closeConnection() throws IOException;

    void changeFile(String fileName);

    List<String> read();

}