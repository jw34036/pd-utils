package org.light32.pd.utils.file;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO describe class
 *
 * @author jwhitt 9/4/16
 */
public class FileModeTest {
    @Test
    public void getModeStr() throws Exception {
        assertEquals("r", FileMode.READ_ONLY.getModeStr());
    }

}