package org.light32.pd.utils.file;

/**
 * Describes interface for handling FlatFile records.
 * <p>
 * This is an alternative for providing read/write parse functions
 * for the FlatFile interface; a class implementing a RecordHandler
 * can provide these methods as references to the FlatFileFormat:
 *
 * @author jwhitt 9/5/16
 */
public interface RecordHandler<M> {

    /**
     * given a byte[] array, parses it into a record of type T
     *
     * @param bytes
     * @return
     */
    M bytesToRecord(byte[] bytes);

    /**
     * method record to bytes
     *
     * @param record
     * @return
     */
    byte[] recordToBytes(M record);
}
