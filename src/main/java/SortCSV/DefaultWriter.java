package SortCSV;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DefaultWriter implements WriterCSVSort {

    private FileWriter fileWriter;
    private char separator;
    private char quotechar;
    private char escapechar;
    private String lineEnd;

    public DefaultWriter(String fileName, char separator, char quotechar, char escapechar, String lineEnd) throws IOException {
        this.separator = separator;
        this.quotechar = quotechar;
        this.escapechar = escapechar;
        this.lineEnd = lineEnd;
        this.fileWriter = new FileWriter(fileName, true);
    }

    @Override
    public void write(List<String> row) {
        try {
            CSVWriter csvWriter = new CSVWriter(fileWriter, separator, quotechar, escapechar, lineEnd);
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
