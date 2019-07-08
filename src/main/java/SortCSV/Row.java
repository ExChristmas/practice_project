package SortCSV;

import java.util.*;

public class Row {

    private Map<String, String> values;

    public Row() {
        initializeMap();
    }

    public Row (Row other) {
        this(other.getValues());
    }

    public Row(Map<String, String> values) {
        initializeMap();
        for (Map.Entry<String, String> entry : values.entrySet())
            this.values.put(entry.getKey(), entry.getValue());
    }

    public Row(String[] cols, String[] vals) {
        initializeMap();
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

    private void initializeMap() {
        this.values = new LinkedHashMap<>();
    }

    public Map<String, String> getValues() {
        return values;
    }

    public Collection<String> getRowValues() {
        return this.values.values();
    }

    public String getValue(String val) throws
            NoSuchElementException {
        if (values.containsKey(val)) {
            return values.get(val);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void setValue(String column, String value) {
        values.put(column, value);
    }

//    @Override
//    public int compareTo(Row o) {
//
//        int result = this.
//    }
}