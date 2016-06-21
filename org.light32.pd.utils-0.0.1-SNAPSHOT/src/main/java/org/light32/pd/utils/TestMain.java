package org.light32.pd.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class TestMain {
	

	void themethod() throws MalformedURLException { 
		
		URL url = new URL("http://wtf.is.this.shit.com");
	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		System.out.println("0 memory: "+ Runtime.getRuntime().freeMemory());

		String x = "Allocated String";
		Long xl[] = {50492948102984L, 50492948102984L, 50492948102984L,50492948102984L,50492948102984L};

		System.out.println("1 memory: "+ Runtime.getRuntime().freeMemory());
for(int i=0; i<=10000; i++) { 
	String stri = "string #"+i;
}
		System.out.println("1.5 memory: "+ Runtime.getRuntime().freeMemory());

		{
			String y = "Another allocated String";
			String s = "Another all123ocated String";
			String g = "Another all4212ocated String";

			Long yl[] = {504929481029774L, 9992948102984L, 20123L,2301290388102984L,5049299102984L};
			Long bl[] = {5049294810222984L, 9992948102984L, 2930131223L,2301290388102984L,5049299102984L};
			Long cl[] = {5049294810984L, 9992948102984L, 2930123L,2304490388102984L,49299102984L};

			System.out.println("2 memory: "+ Runtime.getRuntime().freeMemory());

		}
		
		System.out.println("3 memory: "+ Runtime.getRuntime().freeMemory());
		new TestMain().themethod();
		System.out.println("4 memory: "+ Runtime.getRuntime().freeMemory());

		System.gc();
		System.out.println("5 memory: "+ Runtime.getRuntime().freeMemory());

		
	}

}
