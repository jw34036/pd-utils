package org.light32.pd.utils.file.annotations;

/**
 * TestRecord
 *
 * @author jwhitt 8/27/16
 */
@FlatFile(fileId = "testfile")
public class TestRecord {

    private String fieldA;
    private String fieldB;
    private String fieldC;

    @FlatFileField(name = "FIELD-A", position = 1, width = 5)
    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }

    @FlatFileField(name = "FIELD-B", position = 2, width = 3)
    public String getFieldB() {
        return fieldB;
    }

    public void setFieldB(String fieldB) {
        this.fieldB = fieldB;
    }

    @FlatFileField(name = "FIELD-C", position = 3, width = 15)
    public String getFieldC() {
        return fieldC;
    }

    public void setFieldC(String fieldC) {
        this.fieldC = fieldC;
    }
}
