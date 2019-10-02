package Entity;

import java.util.ArrayList;

import ComponentSystem.FlappyComponent;
import Core.FlappyEngine;
import Maths.Transform;
import Maths.Vector3;

public class Object {
	
	public String name;
	public Transform transform = new Transform(Vector3.ZERO, Vector3.ZERO, Vector3.ONE);
	public Object parent;
	private boolean active = true;
	public ArrayList<Object> childs = new ArrayList<Object>();
	public ArrayList<FlappyComponent> components = new ArrayList<FlappyComponent>();
	
	public Object(Object parent, String name) {
		
		if(this.getClass() != Scene.class)
			parent.childs.add(this);
		
		this.name = name;
		this.parent = parent;
	}
	
	public Object(String name) {
		this.name = name;
		this.parent = FlappyEngine.getCurrentScene();
		parent.childs.add(this);
	}
	
	public void Update() {
		for (FlappyComponent component : components) {
			if(component.isActive())
				component.Update();
		}
		for (Object child : childs) {
			if(child.active)
				child.Update();
		}
	}
	
	public void Render() {
		for (FlappyComponent component : components) {
			if(component.isActive())
				component.Render();
		}
		for (Object child : childs) {
			if(child.active)
				child.Render();
		}
	}
	
	public void Destroy() {
		for (FlappyComponent component : components) {
			component.Destroy();
		}
		for (Object child : childs) {
			child.Destroy();
		}
	}
	public void addComponent(FlappyComponent component) {
		components.add(component);
		component.parent = this;
	}
	
	public <T> FlappyComponent getComponent(Class<T> component) {
		for (FlappyComponent c : components) {
			if(c.getClass().equals(component)) {
				return c;
			}
		}
		return null;
	}
	
	public <T> boolean hasComponent(Class<T> component) {
		for (FlappyComponent c : components) {
			if(c.getClass().equals(component)) {
				return true;
			}
		}
		return false;
	}

	public <T> void removeComponent(Class<T> comp) {
		for (int i = 0; i < components.size(); i++) {
			if(components.get(i).getClass().equals(comp)) {
				System.out.println("Removed component");
				components.remove(i);
				return;
			}
		}
	}
}
