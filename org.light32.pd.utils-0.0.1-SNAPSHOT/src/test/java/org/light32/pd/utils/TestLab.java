package org.light32.pd.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.light32.pd.utils.json.JSONUtils;


/**
 * random things that have been tried in the course of this project
 * 
 * @author jwhitt
 *
 */
public class TestLab {

	
	@Test
	public void testSerialize() { 
		TestClass testObj = new TestClass(1);
		System.out.println("Reference:");
		System.out.println("----------------");
		System.out.println(JSONUtils.REFERENCE.toJSON(testObj));

		System.out.println("Jackson:");
		System.out.println("----------------");
		System.out.println(JSONUtils.JACKSON.toJSON(testObj));

	}

	
	@Test
	public void meAgainstTheWorld() throws IOException, URISyntaxException, InstantiationException, IllegalAccessException { 
		System.out.println("Performance comparison test");
		System.out.println("---------------------------");

		// Reference
		long before = System.currentTimeMillis();
		for(int x=0;x<10000;x++) { 
			TestClass testObj = new TestClass(x);
			String json = JSONUtils.REFERENCE.toJSON(testObj);
		}
		long after = System.currentTimeMillis();
		System.out.println("Serialize[10000]: Reference: start=["+(before)+"] stop=["+(after)+"] elapsed=["+(after-before)+"]");

		// Jackson
		long before_jax = System.currentTimeMillis();
		for(int x=0;x<10000;x++) { 
			TestClass testObj = new TestClass(x);
			String json = JSONUtils.JACKSON.toJSON(testObj);
		}
		long after_jax = System.currentTimeMillis();
		System.out.println("Serialize[10000]: Jackson: start=["+(before_jax)+"] stop=["+(after_jax)+"] elapsed=["+(after_jax-before_jax)+"]");

		// deserialization test
		String testJson = FileUtils.readFileFromClasspath(this.getClass(), "testJson.txt");
		
		// Jackson
		long before_jax_d = System.currentTimeMillis();
		for(int x=0;x<10000;x++) { 
			TestClass testObj = JSONUtils.JACKSON.fromJSON(TestClass.class, testJson);
		}
		long after_jax_d = System.currentTimeMillis();
		System.out.println("Deserialize[10000]: Jackson: start=["+(before_jax_d)+"] stop=["+(after_jax_d)+"] elapsed=["+(after_jax_d-before_jax_d)+"]");

	}
	
	@Test
	public void testHttpType() { 
		
	}
	
}
