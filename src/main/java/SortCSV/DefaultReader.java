package SortCSV;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultReader implements ReaderCSVSort {

    private CSVReader csvReader;
    private int number_line;

    DefaultReader(String fileName, char separator, char qoutechar, int number_line) throws IOException{
        this.number_line = number_line;
        csvReader = new CSVReader(new FileReader(new File(fileName)), separator, qoutechar, this.number_line);
    }

    @Override
    public boolean hasNextLine() {
        try {
            if (csvReader.readNext() != null)
                return true;
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> read() {
        List<String> components = new ArrayList<>();
        String[] components_temp;
        try {
            if ((components_temp = csvReader.readNext()) == null)
                return components;
            else
                components.addAll(Arrays.asList(components_temp));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error");
        }
        number_line++;
        return components;
    }
}
