package org.light32.pd.utils.file;

/**
 * enum for mode strings for RandomAccessFile
 *
 * @author jwhitt 8/27/16
 */
public enum FileMode {

    // Open for reading only. Invoking any of the write methods of
    // the resulting object will cause an IOException to be thrown.
    READ_ONLY("r"),
    // Open for reading and writing. If the file does not already
    // exist then an attempt will be made to create it.
    READ_WRITE("rw"),
    // Open for reading and writing, as with "rw", and also
    // require that every update to the file's content or
    // metadata be written synchronously to the underlying
    // storage device.
    RW_SYNC("rws"),
    // Open for reading and writing, as with "rw", and also require
    // that every update to the file's content be
    // written synchronously to the underlying storage device.
    READ_WRITE_D("rwd");

    private final String modeStr;

    FileMode(String rwd) {
        this.modeStr = rwd;
    }

    public String getModeStr() {
        return modeStr;
    }
}
