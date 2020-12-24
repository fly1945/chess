package xyz.chengzi.aeroplanechess.util;

public class Sleeper {
	public static void sleep(int i) {
		try {
			Thread.currentThread().sleep(i);
		}catch(Exception e) {
			
		}
	}
}
