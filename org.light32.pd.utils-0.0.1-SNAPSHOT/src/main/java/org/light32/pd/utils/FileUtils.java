/**
 * 
 */
package org.light32.pd.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author jwhitt
 *
 */
public class FileUtils {

	
	public static String readFileFromClasspath(Class<?> clazz, String fileName) throws IOException, URISyntaxException {
	
		return new String(Files.readAllBytes(
	    				Paths.get(clazz.getClassLoader().getResource(fileName).toURI())));
	}
}
