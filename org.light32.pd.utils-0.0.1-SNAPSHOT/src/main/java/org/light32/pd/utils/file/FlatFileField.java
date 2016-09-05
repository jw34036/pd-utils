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
    private String name;

    /**
     * width of the field in bytes
     */
    private int width;

    /**
     * indicates if this field is filler or not.  if it is, it will be read
     * for file pointer positioning purposes but otherwise ignored.
     */
    private boolean filler;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isFiller() {
        return filler;
    }

    public void setFiller(boolean filler) {
        this.filler = filler;
    }
}
