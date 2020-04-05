package objects;

import Core.Logger;

public class Object {
	
	String name = "";
	
	public Object(String name) {
		
	}
	public Object Instantiate(Object original) {
		return original;
	}
	public void Destroy() {
		Logger.Log("Destroyed " + this.name);
	}
}
