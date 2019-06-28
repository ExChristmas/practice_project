package SortCSV;

import java.util.Map;
import java.util.HashMap;

public class Row {

    private Map<String, String> values;

    public Row() {
        values = new HashMap<>();
    }

    public Row(String[] cols, String[] vals) {
        values = new HashMap<>();

        for (int i = 0; i < cols.length; i++)
            values.put(cols[i], vals[i]);
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}