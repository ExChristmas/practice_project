package SortCSV;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Row {

    private Map<String, String> values;

    public Row() {
        values = new HashMap<>();
    }

    public Row(HashMap<String, String> values) {
        this.values = new HashMap<>();
        for (Map.Entry<String, String> entry : values.entrySet())
            this.values.put(entry.getKey(), entry.getValue());
    }

    public Row(String[] cols, String[] vals) {
        this.values = new HashMap<>();
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



    public Map<String, String> getValues() {
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