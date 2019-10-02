package Mesh;

import Maths.Vector3;

public class Vertex {
	private Vector3 position, color;
	
	public Vertex(Vector3 position, Vector3 color) {
		this.position = position;
		this.color = color;
	}

	public Vector3 getPosition() {
		return position;
	}
	
	public Vector3 getColor() {
		return color;
	}
}
