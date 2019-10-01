package Maths;

public class Transform {
	public Vector3 position, rotation, scale;
	
	public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Matrix4 getMatrix() {
		return Matrix4.transform(position, rotation, scale);
	}
}
