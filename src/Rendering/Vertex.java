package Rendering;

import Utility.Color;
import math.Vector3;

public class Vertex {
	
	Vector3 position;
	Color color;
	public Vertex(Vector3 position, Color color) {
		this.position = position;
		this.color = color;
	}
	public Vector3 getPosition() {
		return position;
	}
	
	public Vector3 getColor() {
		return color.getColor();
	}
}
