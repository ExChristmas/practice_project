package SortCSV;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class DefaultWriter implements WriterCSVSort {

    private String fileName;
    private CSVWriter csvWriter;
    private char separator;
    private char quotechar;
    private char escapechar;
    private String lineEnd;

    public DefaultWriter(String fileName, char separator, char quotechar, char escapechar, String lineEnd) throws IOException {
        this.fileName = fileName;
        this.separator = separator;
        this.quotechar = quotechar;
        this.escapechar = escapechar;
        this.lineEnd = lineEnd;
        this.csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)),
                separator, quotechar, escapechar, lineEnd);
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void changeFile(String fileName) {
        this.fileName = fileName;
        try {
            this.csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(fileName)),
                    separator, quotechar, escapechar, lineEnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(List<String> row) {
        try {
            Iterator<String> it = row.iterator();
            //
            String[] row_write = new String[row.size()];
            for (int i = 0; i < row.size(); i++)
                row_write[i] = it.next();
            csvWriter.writeNext(row_write);
              csvWriter.flush();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}