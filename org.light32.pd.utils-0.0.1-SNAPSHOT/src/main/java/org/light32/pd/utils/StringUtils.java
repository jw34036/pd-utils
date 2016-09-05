package org.light32.pd.utils;

import java.security.MessageDigest;

/**
 * String Utils
 * 
 * @author jwhitt 05/25/2015
 *
 */
public class StringUtils {

    /**
     * returns the defaultVal if value is null,
     * otherwise returns value
     */
    public static String nvl(String value, String defaultVal) {
        return (value == null) ? defaultVal : value;
    }

    /**
     * returns the defaultVal if value is null or an empty string,
     * otherwise returns value
     *
     * @param value
     * @param defaultVal
     * @return String
     */
    public static String nevl(String value, String defaultVal) {
        return (value == null || value.equals("")) ? defaultVal : value;
    }

    /**
     * Generates sha256 hash of String, suitable for Git-style ID
     * generation
     *
     * @param base
     * @return
     */
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
    }

    /**
     * sets a string to an exact length N by either truncating the string to
     * N characters in length or padding with padChar until N characters in length.
     *
     * @param s
     * @param target
     * @param padChar
     * @return
     */
    public static String exactLength(String s, int target, char padChar) {
        if (s.length() >= target) {
            return s.substring(0, target);
        }
        // else
        while (s.length() < target) {
            s = s + padChar;
        }
        return s;
    }
}