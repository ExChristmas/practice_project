package sortcsv;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;


public class NaturalMergeSort implements SortCSV {

    private String workFile1;
    private String workFile2;

    public NaturalMergeSort(String workFile1, String workFile2) {
        this.workFile1 = workFile1;
        this.workFile2 = workFile2;
    }

    public String getWorkFile1() {
        return this.workFile1;
    }

    public void setWorkFile1(String workFile1) {
        this.workFile1 = workFile1;
    }

    public String getWorkFile2() {
        return this.workFile2;
    }

    public void setWorkFile2(String workFile2) {
        this.workFile2 = workFile2;
    }

    private Row toRow(List<String> cols, List<String> vals) { //added
        if (cols.size() == vals.size()) {
            Map<String, String> row = new LinkedHashMap<>();
            Iterator<String> it_cols = cols.iterator();
            Iterator<String> it_vals = vals.iterator();
            while (it_cols.hasNext())
                row.put(it_cols.next(), it_vals.next());
            return new Row(row);
        } else {
            System.out.println("Column sizes and values differ");
            return null;
        }
    }

    private List<String> toList(String[] row) { //added (need?)
        int len_row = row.length;
        return new ArrayList<>(Arrays.asList(row).subList(0, len_row));
    }

    private void equalList(List<String> lst1, List<String> lst2) {
        lst1.clear();
        lst1.addAll(lst2);
    }

    @Override
    public void sort (String fileNameSort, Comparator<Row> comparator, List<ReaderCSVSort> readers, WriterCSVSort writer) {
        Sorter sorter = new Sorter();
        int fileFlag1 = 1, fileFlag2 = 1;
        try {
            while (fileFlag1 > 0 && fileFlag2 > 0) {
                sorter.initialization();
                sorter.separation(fileNameSort, workFile1, workFile2, comparator, readers, writer);
                sorter.merge(fileNameSort, workFile1, workFile2, comparator, readers, writer);
                fileFlag1 = sorter.fileFlag1;
                fileFlag2 = sorter.fileFlag2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Sorter {

        private Row row1;
        private Row row2;
        private List<String> first_row;
        private int fileFlag1;
        private int fileFlag2;
        private int marker;
        private List<String> end_range;

        Sorter() {
            row1 = new Row();
            row2 = new Row();
            end_range = new ArrayList<>();
            end_range.add("'");
        }

        void initialization() {
            this.fileFlag1 = 0;
            this.fileFlag2 = 0;
            this.marker = 1;
        }

        void separation(String fileNameSort, String workFile1, String workFile2, Comparator<Row> comparator,
                        List<ReaderCSVSort> readers, WriterCSVSort writer) {
            List<String> lst_read;
            Iterator<ReaderCSVSort> it_readers = readers.iterator();
            ReaderCSVSort reader = it_readers.next();
            reader.changeFile(fileNameSort);
            first_row = reader.read();
            lst_read = reader.read();
            if (lst_read != null) { // file end check
                writer.changeFile(workFile1);
                writer.write(new ArrayList<>(lst_read)); // write first row in first file
                row1 = toRow(first_row, lst_read);
                lst_read = reader.read();
            }
            if (lst_read != null) { // file end check
                row2 = toRow(first_row, lst_read); // packaging second row values
            }
            while (lst_read != null) { // file division
                if (comparator.compare(row1, row2) > 0) {
                    switch (marker) {
                        case 1: {
                            writer.write(end_range);
                            marker = 2;
                            fileFlag1++;
                            break;
                        }
                        case 2: {
                            writer.write(end_range);
                            marker = 1;
                            fileFlag2++;
                            break;
                        }
                    }
                }
                if (marker == 1) {
                    writer.changeFile(workFile1);
                    writer.write(new ArrayList<>(row2.getRowValues()));
                    fileFlag1++;
                } else {
                    writer.changeFile(workFile2);
                    writer.write(new ArrayList<>(row2.getRowValues()));
                    fileFlag2++;
                }
                row1 = new Row(row2);
                lst_read = reader.read();
                if (lst_read != null) {
                    row2 = toRow(first_row, lst_read);
                } else {
                    break;
                }
            }
            if (fileFlag2 > 0 && marker == 2) {
                writer.changeFile(workFile2);
                writer.write(end_range);
            }
            if (fileFlag1 > 0 && marker == 1) {
                writer.changeFile(workFile1);
                writer.write(end_range);
            }
        }

        void merge(String fileSort, String workFile1, String workFile2, Comparator<Row> comparator,
                   List<ReaderCSVSort> readers, WriterCSVSort writer) throws IOException {
            new FileWriter(new File(fileSort), false);
            Iterator<ReaderCSVSort> it_readers = readers.iterator();
            ReaderCSVSort reader1 = it_readers.next();
            ReaderCSVSort reader2 = it_readers.next();
            writer.changeFile(fileSort);
            writer.write(first_row);
            reader1.changeFile(workFile1);
            List<String> lst_read1 = reader1.read();
            List<String> lst_read2 = null;
            if (fileFlag2 != 0) {
                reader2.changeFile(workFile2);
                lst_read2 = reader2.read();
            }

            if (lst_read1 != null) {
                row1 = toRow(first_row, lst_read1);
            }
            if ((lst_read2 != null) && (fileFlag2 != 0)) {
                row2 = toRow(first_row, lst_read2);
            }

            boolean file1, file2;

            while (lst_read1 != null && lst_read2 != null) {
                file1 = file2 = false;
                while (!file1 && !file2) {
                    if (comparator.compare(row1, row2) <= 0) {
                        assert row1 != null; //
                        writer.write(new ArrayList<>(row1.getRowValues()));
                        lst_read1 = reader1.read();
                        if (lst_read1.equals(end_range)) {
                            file1 = true;
                            lst_read1 = reader1.read();
                            if (lst_read1 != null) {
                                row1 = toRow(first_row, lst_read1);
                            }
                        } else {
                            row1 = toRow(first_row, lst_read1);
                        }
                    } else {
                        assert row2 != null;
                        writer.write(new ArrayList<>(row2.getRowValues()));
                        lst_read2 = reader2.read(); //
                        if (lst_read2.equals(end_range)) {
                            file2 = true;
                            lst_read2 = reader2.read();
                            if (lst_read2 != null) {
                                row2 = toRow(first_row, lst_read2);
                            }
                        } else {
                            row2 = toRow(first_row, lst_read2);
                        }
                    }
                }
                while (!file1) {
                    assert row1 != null;
                    writer.write(new ArrayList<>(row1.getRowValues()));
                    lst_read1 = reader1.read();
                    if (lst_read1.equals(end_range)) {
                        file1 = true;
                        lst_read1 = reader1.read();
                        if (lst_read1 != null)
                            row1 = toRow(first_row, lst_read1);
                    } else {
                        row1 = toRow(first_row, lst_read1);
                    }
                }
                while (!file2) {
                    assert row2 != null;
                    writer.write(new ArrayList<>(row2.getRowValues()));
                    lst_read2 = reader2.read();
                    if (lst_read2.equals(end_range)) {
                        file2 = true;
                        lst_read2 = reader2.read();
                        if (lst_read2 != null)
                            row2 = toRow(first_row, lst_read2);
                    } else {
                        row2 = toRow(first_row, lst_read2);
                    }
                }
            }
            file1 = file2 = false;
            while (!file1 && lst_read1 != null) {
                assert row1 != null;
                writer.write(new ArrayList<>(row1.getRowValues()));
                lst_read1 = reader1.read();
                if (lst_read1.equals(end_range)) { //
                    file1 = true;
                    lst_read1 = reader1.read();
                    if (lst_read1 != null) {
                        row1 = toRow(first_row, lst_read1);
                    }
                } else {
                    row1 = toRow(first_row, lst_read1); //
                }
            }
            while (!file2 && lst_read2 != null) {
                assert row2 != null;
                writer.write(new ArrayList<>(row2.getRowValues()));
                lst_read2 = reader2.read();
                if (lst_read2.equals(end_range)) { //
                    file2 = true;
                    lst_read2 = reader2.read();
                    if (lst_read2 != null) {
                        row2 = toRow(first_row, lst_read2);
                    }
                } else {
                    row2 = toRow(first_row, lst_read2); // stoped here!!!
                }
            }
             if (!(new File(workFile1)).delete()) {
                 System.out.println("File don't found or don't can delete"); //
             }
             if (!(new File(workFile2)).delete()) {
                 System.out.println("File don't found or don't can delete"); //
             }
        }
    }
}