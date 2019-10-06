package ComponentSystem;

import Entity.GameObject;

public class FlappyComponent {
	
	public GameObject gameObject;
	boolean active = true;

	public void OnEnable() {
		
	}
	
	public void OnDisable() {
		
	}
	
	public void Update() {
		
	}
	
	public void Render() {
		
	}
	
	public void Destroy() {
		
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
}
