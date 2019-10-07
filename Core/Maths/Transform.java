package Maths;

import java.util.ArrayList;

import Entity.Camera;
import Entity.GameObject;

public class Transform {
	
	public Transform parent;
	public GameObject gameObject;
	public Vector3 position = Vector3.ONE;
	public Vector3 rotation = Vector3.ONE;
	public Vector3 scale = Vector3.ONE;
	
	public ArrayList<Transform> childs = new ArrayList<Transform>();
	
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
	
	public Matrix4f getProjectedTransform() {
		
		Matrix4f transform = getMatrix();
		Matrix4f projection = Projection.getProjection();
		Matrix4f cameraRot = new Matrix4f().InitRotation(Camera.main.transform.rotation);
		Matrix4f cameraPos = new Matrix4f().InitTranslation(Camera.main.transform.position);

		return projection.Mul(cameraRot.Mul(cameraPos.Mul(transform)));
	}
	
	public Matrix4f getMatrix()
	{
		Matrix4f translationMatrix = new Matrix4f().InitTranslation(position);
		Matrix4f rotationMatrix = new Matrix4f().InitRotation(rotation);
		Matrix4f scaleMatrix = new Matrix4f().InitScale(scale.x, scale.y, scale.z);
		Matrix4f parentMatrix4f = new Matrix4f().InitIdentity();
		
		if(parent != null) 
			parentMatrix4f = parent.getMatrix();
		
		return parentMatrix4f.Mul(translationMatrix.Mul(rotationMatrix.Mul(scaleMatrix)));
	}
	@Override
	public String toString() {
		String line = "";
		if(parent.gameObject != null)
			line += "Parent " + parent.gameObject.name + "\n";
		line += "Position: " + position.toString() + "\n";
		line += "Rotation: " + rotation.toString() + "\n";
		line += "Scale: " + scale.toString() + "\n";
		
		for (Transform transform : childs) {
			line += transform.gameObject.name + "\n";
		}
		return line;
	}
}
