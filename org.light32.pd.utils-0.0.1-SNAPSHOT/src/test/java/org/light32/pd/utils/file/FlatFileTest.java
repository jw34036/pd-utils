package org.light32.pd.utils.file;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for FlatFile<T>
 *
 * @author jwhitt 9/5/16
 */
public class FlatFileTest {

    static FlatFile<String> testFile;
    static FlatFileFormat stringFormat;

    static RecordHandler<String> stringHandler = new RecordHandler<String>() {
        @Override
        public String bytesToRecord(byte[] bytes) {
            return new String(bytes);
        }

        @Override
        public byte[] recordToBytes(String record) {
            return record.getBytes();
        }
    };

    @BeforeClass
    public static void beforeClass() throws URISyntaxException {
        stringFormat = new FlatFileFormat();
        stringFormat.addField(new FlatFileField("line", 6));

        File testFileObj = new File(FlatFileTest.class.getClassLoader().getResource("testFlatFile2.dat").toURI());
        testFile = new FlatFile<String>(testFileObj, FileMode.READ_ONLY, stringFormat, stringHandler);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        if (testFile != null && testFile.isOpen()) {
            testFile.close();
        }
    }

    @After
    public void after() throws Exception {
        // close testFile if open
        if (testFile != null && testFile.isOpen()) {
            testFile.close();
        }
    }


    /////////////////////////////

    @Test
    public void open_Test() throws Exception {
        testFile.open();
        assertTrue(testFile.isOpen());
        assertEquals(0, testFile.position());
    }

    @Test(expected = IllegalStateException.class)
    public void open_TestNoReopen() throws Exception {
        testFile.open();
        testFile.open();
    }

    @Test
    public void reopen_Test() throws Exception {
        testFile.open();
        testFile.reopen();
        assertTrue(testFile.isOpen());
        assertEquals(0, testFile.position());
    }

    @Test
    public void seek_Test() throws Exception {
        testFile.open();
        testFile.seek(13);
        assertEquals(13, testFile.position());
        assertEquals(13, testFile.getRandomAccessFile().getFilePointer());
    }

    @Test
    public void next_Test() throws Exception {
        testFile.open();
        testFile.next();
        assertEquals("LINE02", testFile.look());
    }

    @Test
    public void fwd_Test() throws Exception {
        testFile.open();
        testFile.fwd(4);
        assertEquals("LINE05", testFile.look());
    }


    @Test
    public void stepForward_Test() throws Exception {
        testFile.open();
        testFile.stepForward(2, true);
        assertEquals("LINE03", testFile.look());
    }

    @Test
    public void prev() throws Exception {

    }


    @Test
    public void stepReverse() throws Exception {

    }

    @Test
    public void look() throws Exception {

    }

    @Test
    public void look1() throws Exception {

    }

    @Test
    public void readRecord() throws Exception {

    }

    @Test
    public void readRecord1() throws Exception {

    }

    @Test
    public void readStream() throws Exception {

    }

    @Test
    public void readStream1() throws Exception {

    }

    @Test
    public void lookString() throws Exception {

    }

    @Test
    public void readString() throws Exception {

    }

    @Test
    public void writeRecord() throws Exception {

    }

    @Test
    public void writeRecord1() throws Exception {

    }

    @Test
    public void writeString() throws Exception {

    }

    @Test
    public void close() throws Exception {

    }

}