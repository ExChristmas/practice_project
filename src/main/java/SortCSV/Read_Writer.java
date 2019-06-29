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

    public String[] Read();

    public void Write();

}

public class Default_Read_Writer implements Read_WriterCSV {

    private String fileName;
    private char separator;
    private char quotechar;

//    Default_Read_Writer() {}

    public String[] Read() {
        try {
            List<String> component = new ArrayList<>();
            FileReader reader = new FileReader(new File(fileName));
            CSVReader readerCSV = new CSVReader(reader, separator, quotechar, 0);
            readerCSV.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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