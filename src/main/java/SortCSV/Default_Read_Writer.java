package SortCSV;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface Read_WriterCSV {

    ArrayList<String> Read();

    void Write(ArrayList<String> row);

}

public class Default_Read_Writer implements Read_WriterCSV {

    private String fileName;
    private char separator;
    private char quotechar;
    private int number_line;
    private char escapehar;
    private String lineEnd;

//    public Default_Read_Writer(String)
    public Default_Read_Writer(String fileName, char separator, char quotechar,
                               int number_line, char escapechar, String lineEnd) {
        this.fileName = fileName;
        this.separator = separator;
        this.quotechar = quotechar;
        this.number_line = number_line;
        this.escapehar = escapechar;
        this.lineEnd = lineEnd;
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

    public char getEscapehar() {
        return this.escapehar;
    }

    public String getLineEnd() {
        return this.lineEnd;
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

    public void setEscapehar(char escapehar) {
        this.escapehar = escapehar;
    }

    public void setLineEnd(String lineEnd) {
        this.lineEnd = lineEnd;
    }

    public ArrayList<String> Read() {
        ArrayList<String> components = new ArrayList<>();
        try {
            FileReader reader = new FileReader(new File(fileName));
            CSVReader csvReader = new CSVReader(reader, separator, quotechar, number_line);
            String[] components_temp = csvReader.readNext();
            for (String s : components_temp) {
                components.add(s);
            }
        } catch (IOException e) {
                e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            number_line++;
            return components;
        }
    }

    public void Write(ArrayList<String> row) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            CSVWriter csvWriter = new CSVWriter(writer, separator, quotechar, escapehar, lineEnd);
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