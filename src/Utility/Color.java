package Utility;

import math.Vector3;

public class Color {
	
	public static final Color BLACK = new Color(0f, 0f, 0f, 1f);
	public static final Color WHITE = new Color(1f, 1f, 1f, 1f);
	public static final Color RED = new Color(1f, 0f, 0f, 1f);
	public static final Color GREEN = new Color(0f, 1f, 0f, 1f);
	public static final Color BLUE = new Color(0f, 0f, 1f, 1f);
	
	Vector3 color = new Vector3(0, 0, 0);
	public float a;
	
	public Color(int r, int g, int b, int a) {
		color.x = (float) r / 255;
		color.y = (float) g / 255;
		color.z = (float) b / 255;
		this.a = (float) a / 255;
	}
	public Color(float r, float g, float b, float a) {
		color.x = r;
		color.y = g;
		color.z = b;
		this.a = a;
	}
	public Vector3 getColor() {
		return color;
	}
	public static Color fromHex(String hex) {
		
		hex = hex.toUpperCase();
		hex = hex.substring(1, hex.length());
		
		int r = Integer.parseInt(hex.substring(0, 2), 16);
		int g = Integer.parseInt(hex.substring(2, 4), 16);
		int b = Integer.parseInt(hex.substring(4, 6), 16);
		
		
		return new Color(r, g, b, 1);
	}
}
