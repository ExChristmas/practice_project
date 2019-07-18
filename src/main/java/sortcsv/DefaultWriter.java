package sortcsv;

import au.com.bytecode.opencsv.CSVWriter;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class DefaultWriter implements WriterCSVSort {

    private static final Logger logger = Logger.getLogger(DefaultWriter.class);

    private String fileName;
    private CSVWriter csvWriter;
    private char separator;
    private char quoteChar;
    private char escapeChar;
    private String lineEnd;

    public DefaultWriter(String fileName, char separator, char quoteChar, char escapeChar, String lineEnd) throws IOException {
        this.fileName = fileName;
        this.separator = separator;
        this.quoteChar = quoteChar;
        this.escapeChar = escapeChar;
        this.lineEnd = lineEnd;
        this.csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)),
                separator, quoteChar, escapeChar, lineEnd);
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void closeConnection() throws IOException {
        csvWriter.close();
    }

    @Override
    public void changeFile(String fileName) {
        this.fileName = fileName;
        try {
            this.csvWriter.close();
            this.csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)),
                    separator, quoteChar, escapeChar, lineEnd);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void write(List<String> row) {
        try {
            Iterator<String> it = row.iterator();
            String[] row_write = new String[row.size()];
            for (int i = 0; i < row.size(); i++)
                row_write[i] = it.next();
            csvWriter.writeNext(row_write);
            csvWriter.flush();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}