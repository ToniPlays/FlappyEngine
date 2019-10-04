package Loaders;

import java.util.ArrayList;

public class Utils {
	public static String[] removeEmpty(String[] strings) {
		ArrayList<String> result = new ArrayList<String>();
		
		for (String string : strings) {
			if(!string.equals("")) result.add(string);
		}
		String[] res = new String[result.size()];
		return result.toArray(res);
	}

	public static int[] toIntArray(Integer[] indices) {
		int[] indice = new int[indices.length];
		
		for (int i = 0; i < indices.length; i++) {
			indice[i] = indices[i].intValue();
		}
		return indice;
	}
}
