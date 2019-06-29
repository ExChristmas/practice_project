package SortCSV;

import sun.misc.Contended;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class Row {

    private HashMap<String, String> values;

    public Row() {
        values = new HashMap<String, String>();
    }

    public Row(HashMap<String, String> values) {
        this.values = values;
    }

    public Row(String[] cols, String[] vals) {
        int len_cols = cols.length;
        try {
            for (int i = 0; i < len_cols; i++)
                values.put(cols[i], vals[i]);
        } catch (NullPointerException e) {
            System.out.println("Error, NullPointerException!");
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public String getValue(String val) throws
            NoSuchElementException {
        if (values.containsKey(val)) {
            return values.get(val);
        } else {
            throw new NoSuchElementException("");
        }
    }
}