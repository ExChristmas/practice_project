package sortcsv;

import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultReader implements ReaderCSVSort {

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
            e.printStackTrace();
        }
    }

    @Override
    public List<String> read() {
        List<String> components = new ArrayList<>();
        String[] components_temp;
        try {
            if ((components_temp = csvReader.readNext()) == null) {
                return null;
            } else {
                components.addAll(Arrays.asList(components_temp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return components;
    }
}