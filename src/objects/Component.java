package objects;

import java.util.ArrayList;

public class Component extends Object {

	private ArrayList<Component> components = new ArrayList<Component>();
	private boolean isActive = true;
	
	public void setActive(boolean active) {
		if(isActive != active) {
			isActive = active;
//			if(isActive) System.out.println("Enable this");
//			else System.out.println("Disable this");
		}
	}
	public boolean isActive() {
		return isActive;
	}
	
	public Component(String name) {
		super(name);
	}
	public <T> Component getComponent(Class<T> component) {
		for (Component comp : components) {
			if(comp.getClass() == component) {
				return comp;
			}
		}
		return null;
	}
	public <T> void AddComponent(Component component) {
		components.add(component);
		component.Awake();
		component.Start();
	}
	public <T> void RemoveComponent(Class<T> component) {
		components.remove(getComponent(component));
	}
	
	public void OnRender() {
		if(!isActive) return;
		for (Component component : components) {
			component.OnRender();
		}
	}
	public void Awake() {}
	public void Start() {}
	public void Update() {
		if(!isActive) return;
	}
	
	
	@Override
	public void Destroy() {
		for (Component component : components) {
			component.Destroy();
		}
		super.Destroy();
	}
}
