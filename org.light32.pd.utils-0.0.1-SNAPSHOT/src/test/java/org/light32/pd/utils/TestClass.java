package org.light32.pd.utils;

import java.util.HashMap;
import java.util.Map;

public class TestClass { 
	private int version =1;
	private boolean chicago = false;
	private String name="TestClass";
	private int[] numbers = { 1,2,3,4,5,6,7,8,9,10 };
	private String[] stringArray = { "A", "B", "C", "D", "E F G"};
	private float valueOfPi = 3.1415f;
	

	private TestEnum testEnumValue = TestEnum.FOO;
	private OtherTestClass memberObject = new OtherTestClass();
		
	private Map<String,String> testMap = null;
	
	public TestClass() { 
		this.version = 1;
	}
	public TestClass(int version) { 
		this.version = version;
	}
	public Map<String,String> getTestMap() { 
		Map<String,String> foo = new HashMap<String,String>();
		foo.put("ABCD", "1234");
		foo.put("EFGH", "5678");
		foo.put("99", "10000value");
		return foo;
	}
	
	public int getVersion() { return version; }
	public boolean isChicago() { return chicago; }
	public Integer getAgeOfProgrammer() { return 39; } 
	public void runStuff() { }
	private int thisMethodIsPrivate() { return -1; }
	public String getName() { return name; }
	public String[] getStringArray() { return stringArray; }
	
	public int[] getNumbers() { return numbers; }
	public float getValueOfPi() {
		return valueOfPi;
	}
	public OtherTestClass getMemberObject() { return memberObject; }
	public TestEnum getTestEnumValue() {return testEnumValue;}
}