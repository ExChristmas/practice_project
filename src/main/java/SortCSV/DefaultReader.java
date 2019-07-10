package SortCSV;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// deal with "number_line" and CSVreader
public class DefaultReader implements ReaderCSVSort {

    private FileReader fileReader;
    private char separator;
    private char quotechar;
    private int number_line;

    public DefaultReader(String fileName, char separator, char qoutechar, int number_line) throws IOException{
        this.number_line = number_line;
        this.quotechar = qoutechar;
        this.separator = separator;
        fileReader = new FileReader(new File(fileName));
    }

    @Override
    public boolean hasNextLine() {
        try {
            CSVReader csvReader = new CSVReader(fileReader, separator, quotechar, this.number_line);
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
            CSVReader csvReader = new CSVReader(fileReader, separator, quotechar, this.number_line);
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
