package org.light32.pd.utils.file;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for SimpleFlatFileFormat
 * Also covers FlatFileFormat for now
 *
 * @author jwhitt 9/4/16
 */
public class SimpleFlatFileFormatTest {

    FlatFileFormat testFormat;

    @Before
    public void setUp() {
        testFormat = new SimpleFlatFileFormat();
        testFormat.addField(new FlatFileField("a", 5));
        testFormat.addField(new FlatFileField("b", 5));
        testFormat.addField(new FlatFileField("c", 3));

    }

    @Test
    public void recordParser() throws Exception {
        assertNotNull(testFormat.recordParser());
    }

    @Test
    public void recordBuilder() throws Exception {
        assertNotNull(testFormat.recordBuilder());
    }

    @Test
    public void addFieldTest() throws Exception {
        FlatFileFormat testFormat = new SimpleFlatFileFormat();
        testFormat.addField(new FlatFileField("a", 5));
        assertFalse(testFormat.getFieldList().isEmpty());
    }

    @Test
    public void getRecordWidthTest() throws Exception {
        assertEquals(13, testFormat.getRecordWidth());
    }

    @Test
    public void getLineWidthTest() throws Exception {
        assertEquals(14, testFormat.getLineWidth());
    }

    @Test
    public void getFieldListTest() throws Exception {
        assertEquals(3, testFormat.getFieldList().size());
    }

}