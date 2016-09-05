package org.light32.pd.utils.file;

import java.io.File;
import java.util.Map;

/**
 * SimpleFlatFile implementation using Map<String,String>
 * as target type for data records
 *
 * @author jwhitt 8/27/16
 */
public final class SimpleFlatFile extends FlatFile<Map<String, String>> {

    /**
     * Constructor for SimpleFlatFile. This requires a SimpleFlatFileFormat and not just a FlatFileFormat, so
     * that's why it's different.
     *
     * @param file
     * @param format
     * @param mode
     */
    public SimpleFlatFile(File file, SimpleFlatFileFormat format, FileMode mode) {
        super(file, format, mode);
    }

}
