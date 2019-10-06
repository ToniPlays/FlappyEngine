package Maths;

public class Vector2 {
	
	public float x, y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector2 add(Vector2 vector1, Vector2 vector2) {
		return new Vector2(vector1.x + vector2.x, vector1.y + vector2.y);
	}
	
	public static Vector2 subtract(Vector2 vector1, Vector2 vector2) {
		return new Vector2(vector1.x - vector2.x, vector1.y - vector2.y);
	}
	
	public static Vector2 multiply(Vector2 vector1, Vector2 vector2) {
		return new Vector2(vector1.x * vector2.x, vector1.y * vector2.y);
	}
	
	public static Vector2 divide(Vector2 vector1, Vector2 vector2) {
		return new Vector2(vector1.x / vector2.x, vector1.y / vector2.y);
	}
	
	public static float length(Vector2 vector) {
		return (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
	}
	
	public static Vector2 normalize(Vector2 vector) {
		float len = Vector2.length(vector);
		return Vector2.divide(vector, new Vector2(len, len));
	}
	
	public static float dot(Vector2 vector1, Vector2 vector2) {
		return vector1.x * vector2.x + vector1.y * vector2.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2 other = (Vector2) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "X: " + x + ", Y: " + y;
	}
}
