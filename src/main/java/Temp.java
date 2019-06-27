import java.io.FileReader;
import java.util.Arrays;
import au.com.bytecode.opencsv.CSVReader;

public class Temp {
    public static void main(String[] args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("data.csv"), ',' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                System.out.println(Arrays.toString(nextLine));
            }
        }

    }
}
