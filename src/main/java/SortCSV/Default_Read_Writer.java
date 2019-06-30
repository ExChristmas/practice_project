package SortCSV;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

interface Read_WriterCSV {

    public ArrayList<String> Read();

    public void Write();

}

public class Default_Read_Writer implements Read_WriterCSV {

    private String fileName;
    private char separator;
    private char quotechar;

    public Default_Read_Writer(String fileName, char separator, char quotechar) {
        this.fileName = fileName;
        this.separator = separator;
        this.quotechar = quotechar;
    }

    public String getFileName() {
        return this.fileName;
    }
    public char getSeparator() {
        return this.separator;
    }

    public char getQuotechar() {
        return this.quotechar;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public void setQuotechar(char quotechar) {
        this.quotechar = quotechar;
    }

    public ArrayList<String> Read() {
        ArrayList<String> components = new ArrayList<>();
        try {
            FileReader reader = new FileReader(new File(fileName));
            CSVReader readerCSV = new CSVReader(reader, separator, quotechar);
            String[] components_temp = readerCSV.readNext();
            for (String s : components_temp) {
                components.add(s);
            }
        } catch (IOException e) {
                e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            return components;
        }
    }

    public void Write() {
        try {
            FileWriter writer = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}