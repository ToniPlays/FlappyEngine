package Core;

import java.time.LocalTime;

public class Logger {
	
	public static boolean isEnabled = false;
	public static void Log(String text) {
		
		if(!isEnabled) return;
		LocalTime time = LocalTime.now();
		System.out.println(time + ": " + text);
	}
	public static void LogError(String text) {
		
		if(!isEnabled) return;
		LocalTime time = LocalTime.now();
		System.err.println(time + ": " + text);
	}
}
