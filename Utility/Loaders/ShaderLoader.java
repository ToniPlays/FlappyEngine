package Loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ShaderLoader {
	public static String load(String path) {
		
		StringBuilder result = new StringBuilder();
		
		try {
			BufferedReader rd = new BufferedReader(new FileReader(new File(path)));
			
			String line = "";
			while((line = rd.readLine()) != null) {
				result.append(line).append("\n");
			}
			rd.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result.toString();
	}
}
