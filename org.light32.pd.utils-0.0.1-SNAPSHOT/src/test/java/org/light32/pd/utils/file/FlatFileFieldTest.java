package org.light32.pd.utils.file;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit Test for FlatFileField
 *
 * @author jwhitt 9/5/16
 */
public class FlatFileFieldTest {

    @Test
    public void testConstructor() {

        FlatFileField fieldB = new FlatFileField("b", 5);
        assertFalse(fieldB.isFiller());
    }

    @Test
    public void testGetName() {
        FlatFileField fieldA = new FlatFileField("a", 5, false);
        assertEquals("a", fieldA.getName());
    }
}