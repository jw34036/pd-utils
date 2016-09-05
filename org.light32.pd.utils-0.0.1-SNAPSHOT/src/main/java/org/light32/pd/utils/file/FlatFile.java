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
    private final RecordHandler<T> handler;
    private final FlatFileFormat format;
    private final FileMode mode;
    private File fileObj;
    private RandomAccessFile randomAccessFile;

    /**
     * basic constructor only requires File and mode.
     * <p>
     * If you use this, the format cannot be leveraged for parsing and
     * record seeking meta operations, so you'll need to handle those yourself
     * by passing in functions to the relevant methods of this instance.
     *
     * @param file
     * @param mode
     */
    public FlatFile(File file, FileMode mode) {
        this.fileObj = file;
        this.mode = mode;
        this.format = null;
        this.handler = null;
    }

    /**
     * constructor taking a FlatFileFormat.
     * <p>
     * This constructor will also use the format passed in as
     * the RecordHandler if it implements the interface.
     * <p>
     * this pattern is used in the provided flat file types and
     * their formats as created by the FileFactory
     *
     * @param file
     * @param mode
     * @param format
     */
    public FlatFile(File file, FileMode mode, FlatFileFormat format) {
        this.fileObj = file;
        this.format = format;
        if (format instanceof RecordHandler) {
            this.handler = (RecordHandler<T>) format;
        } else {
            this.handler = null;
        }
        this.mode = mode;
    }

    /**
     * constructor taking all discrete parameters.
     *
     * @param file
     * @param mode
     * @param format
     * @param handler
     */
    public FlatFile(File file, FileMode mode, FlatFileFormat format, RecordHandler<T> handler) {
        this.fileObj = file;
        this.format = format;
        this.handler = handler;
        this.mode = mode;
    }

    //////////////////////////////////////////////////////////////////
    //
    // open, seek
    //
    //////////////////////////////////////////////////////////////////

    /**
     * opens the file, throwing an IllegalStateException if
     * the file is already open.
     *
     * @throws IOException
     */
    public void open() throws IOException {
        if (isOpen()) {
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

    public void fwd(int p) throws IOException {
        stepForward(p, true);
    }

    public void rwd(int p) throws IOException {
        stepReverse(p, true);
    }


    /**
     * rewinds the randomAccessFile pointer safely to the previous record.
     *
     * @throws IOException
     */
    public void prev() throws IOException {
        rwd(1);
    }

    /**
     * advances the randomAccessFile pointer safely to the next record.
     *
     * @throws IOException
     */
    public void next() throws IOException {
        fwd(1);
    }


    //////////////////////////////////////////////////////////////////
    //
    // read methods
    //
    //////////////////////////////////////////////////////////////////

    /**
     * reads the record without permanently changing the position of the randomAccessFile pointer.
     * this method uses the handler recordParser() to process the record.
     *
     * @return
     */
    public T look() throws IOException {
        T record = readRecord(handler::bytesToRecord);
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
        return readRecord(handler::bytesToRecord);
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
     * a stream.  if you have large files, you still should do it the hard way.
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
     * returns records from file as a Stream<T>,
     * using the handler's recordParser() as a callback
     *
     * @return
     * @throws IOException
     */
    public Stream<T> readStream() throws IOException {
        return readStream(handler::bytesToRecord);
    }

    //////////////////////////////////////////////////////////////////
    //
    // read bytes as String methods
    //
    //////////////////////////////////////////////////////////////////


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


    //////////////////////////////////////////////////////////////////
    //
    // Write methods
    //
    //////////////////////////////////////////////////////////////////

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
        writeRecord(handler::recordToBytes, record);
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


    //////////////////////////////////////////////////////////////////
    //
    // state queries
    //
    //////////////////////////////////////////////////////////////////

    /**
     * returns true if the file is open, and has a
     * valid file descriptor
     *
     * @return
     * @throws IOException
     */
    public boolean isOpen() throws IOException {
        return (randomAccessFile != null
                && randomAccessFile.getChannel().isOpen());
    }

    /**
     * returns current position in file
     *
     * @return
     */
    public long position() throws IOException {
        return randomAccessFile.getFilePointer();
    }


    //////////////////////////////////////////////////////////////////
    //
    // close and cleanup
    //
    //////////////////////////////////////////////////////////////////

    @Override
    public void close() throws Exception {
        randomAccessFile.close();
    }

    //////////////////////////////////////////////////////////////////
    //
    // getters and setters
    //
    //////////////////////////////////////////////////////////////////

    public String getPathname() {
        return fileObj.getAbsolutePath();
    }

    /**
     * getter for local package use (unit/integration tests and API backchannel stuff)
     *
     * @return RandomAccessFile
     */
    RandomAccessFile getRandomAccessFile() {
        return randomAccessFile;
    }

    public void setRandomAccessFile(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }



}
