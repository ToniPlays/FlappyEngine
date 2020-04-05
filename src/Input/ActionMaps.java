package Input;

import java.util.HashMap;

public class ActionMaps {
	
	HashMap<String, ActionMap> maps = new HashMap<String, ActionMap>();
	public void InitInput() {};
	
	public final ActionMap AddMap(String map) {
		return AddMap(map, false);
	}
	public final ActionMap AddMap(String map, boolean isEnabled) {
		ActionMap acmap = new ActionMap(map);
		acmap.setActive(isEnabled);
		maps.put(map, acmap);
		return acmap;
	}
	public final ActionMap map(String map) {
		return maps.get(map);
	}
	public HashMap<String, ActionMap> getMaps() {
		return maps;
	}
	
	@Override
	public String toString() {
		
		String mapping = "\nINPUT MAPPINGS\n";
		for (String key : maps.keySet()) {
			ActionMap map = maps.get(key);
			mapping += "Map: " + key + " enabled: " + map.isActive() + "\n";
			
			for (String actionKey: map.getActions().keySet()) {
				mapping += "-> " + actionKey + "\n";
			}
		}
		return mapping;
	}
}
