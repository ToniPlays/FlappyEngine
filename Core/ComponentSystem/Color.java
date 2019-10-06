package ComponentSystem;

import Maths.Vector3;

public class Color {
	public static final Vector3 BLACK = new Vector3(0f, 0f, 0f);
	public static final Vector3 WHITE = new Vector3(1f, 1f, 1f);
	public static final Vector3 RED = new Vector3(1f, 0f, 0f);
	public static final Vector3 GREEN = new Vector3(0f, 1f, 0f);
	public static final Vector3 BLUE = new Vector3(0f, 0f, 1f);
	
	Vector3 color = new Vector3(0, 0, 0);
	
	public Color(int r, int g, int b) {
		color.x = (float) r / 255;
		color.y = (float) g / 255;
		color.z = (float) b / 255;
		System.out.println(color.toString());
	}
	public Color(float r, float g, float b) {
		color.x = r;
		color.y = g;
		color.z = b;
	}
	public Vector3 getColor() {
		return color;
	}
}
