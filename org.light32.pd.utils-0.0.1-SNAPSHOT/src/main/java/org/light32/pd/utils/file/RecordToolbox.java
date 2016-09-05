package org.light32.pd.utils.file;

import java.util.function.Function;

/**
 * a provider of functions to process records from a file
 *
 * @author jwhitt 8/27/16
 */
public interface RecordToolbox {

    /**
     * supplies function called to process raw bytes into an object
     *
     * @return
     */
    <T> Function<byte[], T> recordParser();

    /**
     * supplies function called to process object into raw bytes
     *
     * @return
     */
    <T> Function<T, byte[]> recordBuilder();
}
