package Core;

import java.util.HashMap;

public class ServiceManager {
	
	HashMap<String, EngineService> services = new HashMap<String, EngineService>();
	
	public void AddService(String name, EngineService service) {
		
		services.put(name, service);
	}
	public boolean serviceExists(String name) {
		return services.containsKey(name);
	}
	public void RemoveService(String name) {
		getService(name).Destroy();
		services.remove(name);
	}
	
	public EngineService getService(String name) {
		return services.get(name);
	}
	
	public void Awake() {
		Logger.Log("ServiceManager Awake");
		for (String key : services.keySet()) {
			services.get(key).Awake();
		}
	}
	
	public void Start() {
		Logger.Log("ServiceManager Start");
		for (String key : services.keySet()) {
			services.get(key).Start();
		}
	}
	
	public void UpdateAll() {
		for (String key : services.keySet()) {
			EngineService service = services.get(key);
			if(service.isActive())
				service.Update();
		}
	}
	
	public void LateUpdate() {
		for (String key : services.keySet()) {
			EngineService service = services.get(key);
			if(service.isActive())
				service.LateUpdate();
		}
	}
	public void Render() {
		for (String key : services.keySet()) {
			EngineService service = services.get(key);
			if(service.isActive())
				service.OnRender();
		}
	}
	public void DestroyAll() {
		Logger.Log("ServiceManager destroy");
		for (String key : services.keySet()) {
			services.get(key).Destroy();
		}
	}
	
}
