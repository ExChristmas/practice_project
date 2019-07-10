package SortCSV;

import java.util.*;

public class Row {

    private Map<String, String> values;

    public Row(int size) {
        initializeMap(size);
    }

    public Row (Row other) {
        this(other.getValues());
    }

    public Row(Map<String, String> values) {
        initializeMap(values.size());
        for (Map.Entry<String, String> entry : values.entrySet())
            this.values.put(entry.getKey(), entry.getValue());
    }

    public Row(String[] cols, String[] vals) {
        if (cols == null || vals == null) {
            System.out.println("Columns or Values is null");
        } else if (cols.length != vals.length) {
            System.out.println("Lengths Columns and Values is not equal");
        } else {
            initializeMap(cols.length);
            // исправить
            try {
                for (int i = 0; i < cols.length; i++)
                    values.put(cols[i], vals[i]);
            } catch (NullPointerException e) {
                System.out.println("Error, NullPointerException!");
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }

    private void initializeMap(int size) {
        this.values = new LinkedHashMap<>(size);
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