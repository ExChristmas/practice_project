package sortcsv;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultReader implements ReaderCSVSort {

    private static final Logger logger = Logger.getLogger(DefaultReader.class);

    private String fileName;
    private CSVReader csvReader;
    private char separator;
    private char quoteChar;

    public DefaultReader(String fileName, char separator, char quoteChar) throws IOException {
        this.quoteChar = quoteChar;
        this.separator = separator;
        this.fileName = fileName;
        this.csvReader = new CSVReader(new InputStreamReader(new FileInputStream(fileName)), separator, quoteChar);
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void changeFile(String fileName) {
        this.fileName = fileName;
        try {
            this.csvReader = new CSVReader(new InputStreamReader(new FileInputStream(fileName)), separator, quoteChar);
        } catch (IOException e) {
            logger.error("IOExeption in change file from reader");
            e.printStackTrace();
        }
    }

    @Override
    public List<String> read() {
        List<String> components = new ArrayList<>();
        String[] components_temp;
        try {
            if ((components_temp = csvReader.readNext()) == null) {
                logger.info("End of file when reading");
                return null;
            } else {
                components.addAll(Arrays.asList(components_temp));
            }
        } catch (IOException e) {
            logger.error("IOExeption when reading");
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Some error when reading");
        }
        return components;
    }
}