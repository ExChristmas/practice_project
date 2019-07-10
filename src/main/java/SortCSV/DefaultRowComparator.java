package SortCSV;

import java.util.Comparator;

public class DefaultRowComparator implements Comparator<Row> {

    private String column;

    DefaultRowComparator(String column) {
        this.column = column;
    }

    @Override
    public int compare(Row o1, Row o2) {
        return o1.getValue(column).compareTo(o2.getValue(column));
    }
}