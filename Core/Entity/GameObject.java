package Entity;

import java.util.ArrayList;

import ComponentSystem.FlappyComponent;
import Core.FlappyEngine;
import Maths.Transform;
import Maths.Vector3;

public class GameObject {
	
	public String name = "GameObject";
	public Transform transform = new Transform(Vector3.ZERO, Vector3.ZERO, Vector3.ONE);
	public ArrayList<FlappyComponent> components = new ArrayList<FlappyComponent>();
	
	private boolean active = true;
	
	
	public GameObject(Transform parent, String name) {
		this.name = name;
		this.transform.parent = parent;
		this.transform.gameObject = this;
		
		if(this.getClass() != Scene.class) {
			transform.parent.childs.add(transform);	
		}
	}
	
	public GameObject(String name) {
		this.name = name;
		
		if(this.getClass() == Scene.class) {
			this.transform.parent = new Transform();
		}
		else 
			this.transform.parent = FlappyEngine.getCurrentScene().transform;
		
		this.transform.gameObject = this;
		transform.parent.childs.add(transform);
		
		System.out.println(name + " parent " + transform.parent.gameObject);
	}
	
	public void Update() {
		for (FlappyComponent component : components) {
			if(component.isActive())
				component.Update();
		}
		
		for (Transform child : transform.childs) {
			if(child.gameObject.active)
				child.gameObject.Update();
		}
	}
	
	public void Render() {
		for (FlappyComponent component : components) {
			if(component.isActive())
				component.Render();
		}
		for (Transform child : transform.childs) {
			if(child.gameObject.active)
				child.gameObject.Render();
		}
	}
	
	public void Destroy() {
		for (FlappyComponent component : components) {
			component.Destroy();
		}
		for (Transform child : transform.childs) {
			child.gameObject.Destroy();
		}
	}
	public void addComponent(FlappyComponent component) {
		components.add(component);
		component.gameObject = this;
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
				components.remove(i);
				return;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Gamobject " + name + " of type (" + this.getClass().getSimpleName() + ")";
	}
}
