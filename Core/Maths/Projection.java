package Maths;

public class Projection {
	
	public static float zNear = 0.1f;
	public static float zFar = 1000f;
	public static float width = 1280f;
	public static float height = 720f;
	public static float FOV = 70f;
	public static float aspectRatio = width / height;
	static Matrix4f projectionMatrix4f;
	
	public Projection(float fov, float w, float h, float near, float far) {
		
		FOV = fov;
		width = w;
		height = h;
		zNear = near;
		zFar = far;
		aspectRatio = width / height;
		
		projectionMatrix4f = new Matrix4f().InitPerspective(FOV, aspectRatio, zNear, zFar);
		
	}
	public static Matrix4f getProjection() {
		return projectionMatrix4f;
	}
}
