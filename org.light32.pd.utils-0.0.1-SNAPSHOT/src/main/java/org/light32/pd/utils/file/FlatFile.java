package org.light32.pd.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * a FlatFile is a randomAccessFile that contains 1..n Records.
 *
 * @author jwhitt 8/27/16
 */
public class FlatFile<T> implements AutoCloseable {

    private static final long MIN_POS = 0;
    private final FlatFileFormat format;
    private final FileMode mode;
    private File fileObj;
    private RandomAccessFile randomAccessFile;

    public FlatFile(File file, FlatFileFormat format, FileMode mode) {
        this.fileObj = file;
        this.format = format;
        this.mode = mode;
    }


    /**
     * opens the file, throwing an IllegalStateException if
     * the file is already open.
     *
     * @throws IOException
     */
    public void open() throws IOException {
        if (randomAccessFile != null) {
            throw new IllegalStateException("file is already open");
        }
        randomAccessFile = new RandomAccessFile(fileObj, mode.getModeStr());
    }


    /**
     * opens the file, closing it first if it is already open.
     *
     * @throws IOException
     */
    public void reopen() throws IOException {
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        randomAccessFile = new RandomAccessFile(fileObj, mode.getModeStr());
    }

    /**
     * seeks to an absolute position in the randomAccessFile.
     *
     * @param position
     * @throws IOException
     */
    public void seek(long position) throws IOException {
        randomAccessFile.seek(position);
    }

    /**
     * safe reverse method, checks current position
     *
     * @param n
     */
    public void stepReverse(int n, boolean safe) throws IOException {
        long newPos = (randomAccessFile.getFilePointer() - (n * format.getLineWidth()));

        if (safe && newPos < MIN_POS) {
            newPos = MIN_POS;
        }
        randomAccessFile.seek(newPos);
    }

    /**
     * safe forward method, checks current position
     *
     * @param n
     */
    public void stepForward(int n, boolean safe) throws IOException {
        long newPos = (randomAccessFile.getFilePointer() + (n * format.getLineWidth()));

        if (safe && newPos > randomAccessFile.length()) {
            newPos = randomAccessFile.length();
        }
        randomAccessFile.seek(newPos);
    }

    /**
     * rewinds the randomAccessFile pointer to the previous record.
     *
     * @throws IOException
     */
    public void prev() throws IOException {
        stepReverse(1, true);
    }

    /**
     * advances the randomAccessFile pointer to the next record.
     *
     * @throws IOException
     */
    public void next() throws IOException {
        stepForward(1, true);
    }

    //
    // read methods
    //

    /**
     * reads the record without permanently changing the position of the randomAccessFile pointer.
     * this method uses the format recordParser() to process the record.
     *
     * @return
     */
    public T look() throws IOException {
        T record = readRecord(format.recordParser());
        prev();
        return record;
    }

    /**
     * reads the record without permanently changing the position of the randomAccessFile pointer.
     * this method uses a supplied lookCallback to process the record.
     *
     * @return
     */
    public T look(Function<byte[], T> lookCallback) throws IOException {
        T record = readRecord(lookCallback);
        prev();
        return record;
    }

    /**
     * reads a line of bytes from the randomAccessFile,
     * calls recordParser on the bytes,
     * returns the result,
     * advancing the randomAccessFile pointer to the next record.
     *
     * @return
     */
    public T readRecord() throws IOException {
        return readRecord(format.recordParser());
    }

    /**
     * reads a line of bytes from the randomAccessFile,
     * calls recordParser on the bytes,
     * returns the result,
     * advancing the randomAccessFile pointer to the next record.
     *
     * @return
     */
    public T readRecord(Function<byte[], T> readCallback) throws IOException {
        final byte[] buf = new byte[format.getRecordWidth()];
        randomAccessFile.read(buf);
        return readCallback.apply(buf);
    }

    /**
     * returns records from file as a Stream<T>
     * This is a very crude implementation that just uses Files.lines() to generate
     * a stream, no buffered reads or anything are done.  if you have large files,
     * you still should do it the hard way.
     *
     * @param readCallback function to be used to parse records
     * @return Stream of flatfile type
     * @throws IOException
     */
    public Stream<T> readStream(Function<byte[], T> readCallback) throws IOException {
        return Files.lines(fileObj.toPath()).map(
                (s) -> {
                    return readCallback.apply(s.getBytes());
                });
    }

    /**
     * returns records from file as a Stream<T>
     *
     * @return
     * @throws IOException
     */
    public Stream<T> readStream() throws IOException {
        return readStream(format.recordParser());
    }

    //
    // read bytes as String methods
    //

    /**
     * read the next record into a String, and wind the randomAccessFile pointer back afterward
     *
     * @return
     * @throws IOException
     */
    public String lookString() throws IOException {
        String line = randomAccessFile.readLine();
        prev();
        return line;
    }


    /**
     * read the next record as a String, advancing the randomAccessFile pointer
     *
     * @return
     * @throws IOException
     */
    public String readString() throws IOException {
        return randomAccessFile.readLine();
    }

    //
    // Write methods
    //

    /**
     * calls recordBuilder on the record,
     * and writes the resulting bytes to the randomAccessFile
     *
     * @return
     */
    public void writeRecord(Function<T, byte[]> writeCallback, T record) throws IOException {
        randomAccessFile.write(writeCallback.apply(record));
        randomAccessFile.write(System.lineSeparator().getBytes());
    }

    /**
     * reads a line of bytes from the randomAccessFile,
     * calls recordParser on the bytes,
     * returns the result,
     * advancing the randomAccessFile pointer to the next record.
     *
     * @return
     */
    public void writeRecord(T record) throws IOException {
        writeRecord(format.recordBuilder(), record);
    }

    /**
     * write string to the randomAccessFile
     *
     * @param line
     * @throws IOException
     */
    public void writeString(String line) throws IOException {
        randomAccessFile.write(line.getBytes());
        randomAccessFile.write(System.lineSeparator().getBytes());
    }

    @Override
    public void close() throws Exception {
        randomAccessFile.close();
    }

    public String getPathname() {
        return fileObj.getAbsolutePath();
    }

    public RandomAccessFile getRandomAccessFile() {
        return randomAccessFile;
    }

    public void setRandomAccessFile(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }

    public FlatFileFormat getFormat() {
        return format;
    }


}
