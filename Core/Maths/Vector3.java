package Maths;

public class Vector3 {
	public static final Vector3 ZERO = new Vector3(0, 0, 0);
	public static final Vector3 ONE = new Vector3(1, 1, 1);
	
	public float x;
	public float y;
	public float z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3 add(Vector3 vector1, Vector3 vector2) {
		return new Vector3(vector1.x + vector2.x, vector1.y + vector2.y, vector1.z + vector2.z);
	}
	
	public static Vector3 subtract(Vector3 vector1, Vector3 vector2) {
		return new Vector3(vector1.x - vector2.x, vector1.y - vector2.y, vector1.z - vector2.z);
	}
	
	public static Vector3 multiply(Vector3 vector1, Vector3 vector2) {
		return new Vector3(vector1.x * vector2.x, vector1.y * vector2.y, vector1.z * vector2.z);
	}
	
	public static Vector3 divide(Vector3 vector1, Vector3 vector2) {
		return new Vector3(vector1.x / vector2.x, vector1.y / vector2.y, vector1.z / vector2.z);
	}
	
	public static float length(Vector3 vector) {
		return (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z);
	}
	
	public static Vector3 normalize(Vector3 vector) {
		float len = Vector3.length(vector);
		return Vector3.divide(vector, new Vector3(len, len, len));
	}
	
	public static float dot(Vector3 vector1, Vector3 vector2) {
		return vector1.x * vector2.x + vector1.y * vector2.y + vector1.z * vector2.z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
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
		Vector3 other = (Vector3) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "X: " + x + ", Y: " + y + ", Z: " + z;
	}
}
