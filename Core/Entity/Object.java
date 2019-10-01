package Entity;

import java.util.ArrayList;

import ComponentSystem.FlappyComponent;
import Maths.Transform;

public class Object {
	public String name;
	public Transform transform;
	public Object parent;
	public ArrayList<Object> childs = new ArrayList<Object>();
	public ArrayList<FlappyComponent> components = new ArrayList<FlappyComponent>();
	
	public Object(Object parent, String name) {
		if(this.getClass() != Scene.class)
			parent.childs.add(this);
		this.name = name;
		this.parent = parent;
	}
}
