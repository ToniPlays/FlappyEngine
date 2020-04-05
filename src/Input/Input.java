package Input;


import Core.Logger;

import java.util.HashMap;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {
	
	private ActionMaps maps;
	private HashMap<Integer, InputAction> keys = new HashMap<Integer, InputAction>();
	
	public void setMapping(ActionMaps inputMap) {
		maps = inputMap;
		if(maps == null) {
			Logger.LogError("Unable to set input mapping");
			return;
		}
		maps.InitInput();
	}
	
	public void Start() {
		Logger.Log("Input service enabled");
	}

	public void Update() {
		HashMap<String, ActionMap> mapping = maps.getMaps();
		HashMap<Integer, InputAction> _keys = new HashMap<Integer, InputAction>();
		
		for (String map : mapping.keySet()) {
			ActionMap curMap = mapping.get(map);
			if(curMap.isActive()) {
				HashMap<Integer, InputAction> activeKeys = curMap.getActiveKeys();
				for (int i : activeKeys.keySet()) {
					_keys.put(i, activeKeys.get(i));
				}
			}
		}
		keys = _keys;
	}
	public void Destroy() {
		Logger.Log("Destroyed Input-service");
		
	}
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		
		if(keys.containsKey(key)) {
			InputAction inputAction = keys.get(key);
			inputAction.action(action);
		}
	}
}
