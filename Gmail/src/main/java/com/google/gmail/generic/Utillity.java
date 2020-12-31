package com.google.gmail.generic;

public class Utillity {
	public static void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String split(String number) {
		return number.split("\\.")[0];
	}
}
