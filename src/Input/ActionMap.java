package Input;

import java.util.HashMap;

import Core.Logger;

public class ActionMap {
	
	String name;
	private boolean active = true;
	private HashMap<String, InputAction> actions = new HashMap<String, InputAction>();

	public ActionMap(String name) {
		this.name = name;
	}
	
	public final InputAction AddAction(String name, int action) {
		InputAction _action = new InputAction(name, action);
		actions.put(name, _action);
		return _action;
	}
	public HashMap<String, InputAction> getActions() {
		return actions;
	}
	public HashMap<Integer, InputAction> getActiveKeys() {
		
		HashMap<Integer, InputAction> keys = new HashMap<Integer, InputAction>();
		
		for (String key : actions.keySet()) {
			InputAction inputAction = actions.get(key);
			keys.put(inputAction.key, inputAction);
		}
		return keys;
	}
	public InputAction getAction(String key) {
		return actions.get(key);
	}
	public void setActive(boolean b) {
		Logger.Log("Actionmap " + name + " setActive = " + b);
		active = b;
	}
	public boolean isActive() {
		return active;
	}
}
