package org.light32.pd.utils.file;

import java.util.ArrayList;
import java.util.List;

/**
 * base class of all flat file formats.
 * <p>
 * This class is abstract and can't be instantiated. To use a FlatFileFormat, the options are:
 * <p>
 * - Write a subclass of this class that implements the RecordToolbox methods
 * - Instantiate one of the provided flat file formats (SimpleFlatFileFormat, etc.) and
 * add fields to it using addField()
 *
 * @author jwhitt 8/27/16
 */
public class FlatFileFormat {

    /**
     * stores the system newline width
     */
    protected final int separatorWidth = System.lineSeparator().getBytes().length;
    /**
     * list of fields in a record.
     */
    protected List<FlatFileField> fieldList;
    /**
     * caches the width of a record in the file.
     */
    protected int recordWidth;

    /**
     * add a field to the fieldList;
     * lazy instantiate the fieldList
     *
     * @param f
     */
    public final void addField(FlatFileField f) {
        if (fieldList == null) {
            fieldList = new ArrayList<>();
        }
        fieldList.add(f);
        recordWidth += f.getWidth();
    }

    public int getRecordWidth() {
        return recordWidth;
    }
    public int getLineWidth() {
        return (recordWidth + separatorWidth);
    }
    public List<FlatFileField> getFieldList() {
        return fieldList;
    }
}
