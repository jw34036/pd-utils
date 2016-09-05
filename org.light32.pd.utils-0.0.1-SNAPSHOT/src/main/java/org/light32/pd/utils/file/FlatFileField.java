package org.light32.pd.utils.file;

/**
 * a data field in a flat file record
 *
 * @author jwhitt 8/27/16
 */
public class FlatFileField {

    /**
     * the name of the field.  This is used for marshalling and mapping.
     */
    private final String name;

    /**
     * width of the field in bytes
     */
    private final int width;

    /**
     * indicates if this field is filler or not.  if it is, it will be read
     * for file pointer positioning purposes but otherwise ignored.
     */
    private final boolean filler;

    public FlatFileField(String name, int width) {
        this.name = name;
        this.width = width;
        this.filler = false;
    }

    public FlatFileField(String name, int width, boolean filler) {
        this.name = name;
        this.width = width;
        this.filler = filler;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public boolean isFiller() {
        return filler;
    }
}
