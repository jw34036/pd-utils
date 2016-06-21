package org.light32.pd.utils;

import java.io.Serializable;

public class OtherTestClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6513148421136097627L;
	private String otherHello = "Hello";
	private int otherVersion = 5;

	
	public String getOtherHello() {
		return otherHello;
	}
	public void setOtherHello(String otherHello) {
		this.otherHello = otherHello;
	}
	public int getOtherVersion() {
		return otherVersion;
	}
	public void setOtherVersion(int otherVersion) {
		this.otherVersion = otherVersion;
	}
	
}
