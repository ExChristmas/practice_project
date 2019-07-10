package SortCSV;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DefaultWriter implements WriterCSVSort {

    private CSVWriter csvWriter;

    DefaultWriter(String fileName, char separator, char quotechar, char escapechar, String lineEnd) throws IOException{
        csvWriter = new CSVWriter(new FileWriter(fileName, true), separator, quotechar, escapechar, lineEnd);
    }

    @Override
    public void write(List<String> row) {
        try {
            Iterator<String> it = row.iterator();
            String[] row_write = new String[row.size()];
            for (int i = 0; i < row.size(); i++)
                row_write[i] = it.next();
            csvWriter.writeNext(row_write);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
