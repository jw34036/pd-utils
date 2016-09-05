package org.light32.pd.utils.file;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * on read, this FlatFileFormat will recordParser a record into a Map<String,String>
 * where the key = f.name
 * and the value = new String(next f.width() bytes)
 * <p>
 * on write, this FlatFileFormat will construct a record by mapping
 * in.get(f.name) to a buffer of format.recordWidth bytes
 *
 * @author jwhitt 8/27/16
 */
public final class SimpleFlatFileFormat extends FlatFileFormat implements RecordHandler<Map<String, String>> {

    @Override
    public Map<String, String> bytesToRecord(byte[] l) {
        Map<String, String> out = new HashMap<>();
        int cur = 0;
        for (FlatFileField f : fieldList) {
            if (!f.isFiller()) {
                // if line is short populate field with empty string
                if (cur > l.length || (cur + f.getWidth()) > l.length) {
                    out.put(f.getName(), "");
                } else {
                    out.put(f.getName(), new String(Arrays.copyOfRange(l, cur, cur + f.getWidth())));
                }
            }
            cur += f.getWidth();
        }

        return out;
    }

    @Override
    public byte[] recordToBytes(Map<String, String> rec) {
        byte[] outBuf = new byte[this.recordWidth];
        int pos = 0;
        for (FlatFileField f : fieldList) {
            Arrays.fill(outBuf, pos, pos + f.getWidth(), " ".getBytes()[0]);
            byte[] inBytes = rec.get(f.getName()).getBytes();
            for (byte inByte : inBytes) {
                outBuf[pos] = inByte;
                pos++;
            }
        }
        return outBuf;
    }
}
