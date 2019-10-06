package Maths;

import Entity.Camera;

public class Transform {
	
	public Vector3 position, rotation, scale;
	
	public Transform()
	{
		position = Vector3.ZERO;
		rotation = Vector3.ZERO;
		scale = new Vector3(1,1,1);
	}
	
	public Transform(Vector3 position, Vector3 rotation, Vector3 scale)
	{
		this.position = position;
		this.rotation = rotation;
		if(scale == null) 
			scale = Vector3.ONE;
		else
			this.scale = scale;
	}
	
	/*public void Rotate(Vector3 axis, float angle)
	{
		rotation = new Quaternion(axis, angle).Mul(rotation).Normalized();
	}

	public void LookAt(Vector3 point, Vector3 up)
	{
		rotation = GetLookAtRotation(point, up);
	}*/

	/*public Quaternion GetLookAtRotation(Vector3 point, Vector3 up)
	{
		return new Quaternion(new Matrix4f().InitRotation(Vector3.normalize(point.sub(position)), up));
	}*/
	public Matrix4f getProjectedTransform() {
		
		Matrix4f transform = getMatrix();
		Matrix4f projection = Projection.getProjection();
		Matrix4f cameraRot = new Matrix4f().InitRotation(Camera.main.transform.rotation);
		Matrix4f cameraPos = new Matrix4f().InitTranslation(Camera.main.transform.getPos());
		return projection.Mul(cameraRot.Mul(cameraPos.Mul(transform)));
	}
	public Matrix4f getMatrix()
	{
		Matrix4f translationMatrix = new Matrix4f().InitTranslation(position);
		Matrix4f rotationMatrix = new Matrix4f().InitRotation(rotation);
		Matrix4f scaleMatrix = new Matrix4f().InitScale(scale.x, scale.y, scale.z);

		return translationMatrix.Mul(rotationMatrix.Mul(scaleMatrix));
	}
	public Vector3 getPos() {
		return position;
	}
	@Override
	public String toString() {
		return position.toString();
	}
}
