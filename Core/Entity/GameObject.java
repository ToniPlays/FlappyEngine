package Entity;

import java.util.ArrayList;

import ComponentSystem.FlappyComponent;
import Core.FlappyEngine;
import Maths.Transform;
import Maths.Vector3;

public class GameObject {
	
	public String name;
	public Transform transform = new Transform(Vector3.ZERO, Vector3.ZERO, Vector3.ONE);
	public GameObject parent;
	private boolean active = true;
	public ArrayList<GameObject> childs = new ArrayList<GameObject>();
	public ArrayList<FlappyComponent> components = new ArrayList<FlappyComponent>();
	
	
	public GameObject(GameObject parent, String name) {
		
		if(this.getClass() != Scene.class)
			parent.childs.add(this);
		
		this.name = name;
		this.parent = parent;
	}
	
	public GameObject(String name) {
		this.name = name;
		this.parent = FlappyEngine.getCurrentScene();
		parent.childs.add(this);
	}
	
	public void Update() {
		for (FlappyComponent component : components) {
			if(component.isActive())
				component.Update();
		}
		for (GameObject child : childs) {
			if(child.active)
				child.Update();
		}
	}
	
	public void Render() {
		for (FlappyComponent component : components) {
			if(component.isActive())
				component.Render();
		}
		for (GameObject child : childs) {
			if(child.active)
				child.Render();
		}
	}
	
	public void Destroy() {
		for (FlappyComponent component : components) {
			component.Destroy();
		}
		for (GameObject child : childs) {
			child.Destroy();
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
