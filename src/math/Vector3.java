package math;

public class Vector3 {
	public static final Vector3 ZERO = new Vector3(0, 0, 0);
	public static final Vector3 ONE = new Vector3(1, 1, 1);
	
	public float x, y, z;

	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void Normalized() {
		float len = Vector3.length(this);
		Vector3.divide(this, new Vector3(len, len, len));
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
	
	public Vector3 Cross(Vector3 r)
	{
		float x = this.y * r.z - this.z * r.y;
		float y = this.z * r.x - this.x * r.z;
		float z = this.x * r.y - this.y * r.x;
		
		return new Vector3(x, y, z);
	}
	
	public Vector3 mul(float r)
	{
		return new Vector3(this.x * r, this.y * r, this.z * r);
	}
	
	public Vector3 Rotate(Vector3 axis, float angle)
	{
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.Cross(axis.mul(sinAngle)).Add(           //Rotation on local X
				(this.mul(cosAngle)).Add(                     //Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}
	
	/*public Vector3 Rotate(Quaternion rotation)
	{
		Quaternion conjugate = rotation.Conjugate();
		Quaternion w = rotation.Mul(this).Mul(conjugate);
		return new Vector3(w.GetX(), w.GetY(), w.GetZ());
	}*/
	
	public Vector3 Add(Vector3 r)
	{
		return new Vector3(this.x + r.x, this.y + r.y, this.z + r.z);
	}
	
	public float dot(Vector3 r)
	{
		return this.x * r.x + this.y * r.y + this.z * r.y;
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

	public Vector3 sub(Vector3 position) {
		this.x = x - position.x;
		this.y = y - position.y;
		this.z = z - position.z;
		return this;
	}
}
