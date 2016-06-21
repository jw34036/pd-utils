package org.light32.pd.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testNvl() {
		String nullStr = null;
		String emptyStr = "";
		String okStr = "OK";
		String notokStr = "NOT OK";

		assertEquals("nvl() was passed null, did not return default", okStr, StringUtils.nvl(nullStr,okStr));
		assertEquals("nvl() was passed not-null, did not return same", okStr, StringUtils.nvl(okStr,  notokStr));
		assertEquals("nvl() was passed empty string, did not return same", emptyStr, StringUtils.nvl(emptyStr,  okStr));

	}

	@Test
	public void testNevl() {
		String nullStr = null;
		String emptyStr = "";
		String okStr = "OK";
		String notokStr = "NOT OK";

		assertEquals("nevl() was passed null, did not return default", okStr, StringUtils.nevl(nullStr,okStr));
		assertEquals("nevl() was passed empty string, did not return default", okStr, StringUtils.nevl(emptyStr,  okStr));
		assertEquals("nevl() was passed not-null, did not return same", okStr, StringUtils.nevl(okStr,  notokStr));
	}

	@Test
	public void testM2var() { 
		String getter = "getSomething";
		String setter = "setWhatever";
		String booleanGetter = "isChicago";
		String notagetterorsetter = "runForrestRun";
		String constructor = "MillionDollarIdea";
		
		assertEquals("something", StringUtils.m2var(getter));
		assertEquals("whatever", StringUtils.m2var(setter));
		assertEquals("chicago", StringUtils.m2var(booleanGetter));
		assertEquals("runForrestRun", StringUtils.m2var(notagetterorsetter));
		assertEquals("millionDollarIdea", StringUtils.m2var(constructor));
		
		
	}

	@Test
	public void testMethodIsAccessor() { 
		String getter = "getSomething";
		String setter = "setWhatever";
		String booleanGetter = "isChicago";
		String notagetterorsetter = "runForrestRun";
		String constructor = "MillionDollarIdea";
		
		assertTrue(StringUtils.methodIsAccessor(getter));
		assertTrue(StringUtils.methodIsAccessor(setter));
		assertTrue(StringUtils.methodIsAccessor(booleanGetter));
		assertFalse(StringUtils.methodIsAccessor(notagetterorsetter));
		assertFalse(StringUtils.methodIsAccessor(constructor));
	}
}
