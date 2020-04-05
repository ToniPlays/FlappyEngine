package objects;

import java.util.ArrayList;

import Core.EngineService;
import Core.Logger;

public class ObjectService extends EngineService {
	
	private ArrayList<Behaviour> objects = new ArrayList<Behaviour>();

	public void AddObject(Behaviour object) {
		Logger.Log("Added object " + object.name + ", current objects in scene: " + objects.size());
		objects.add(object);
		object.Awake();
		object.Start();
	}
	@Override
	public void Awake() {
		
	}
	@Override
	public void Start() {
		Logger.Log("GameObjects " + objects.size());
		for (Behaviour behaviour : objects) {
			behaviour.Awake();
		}
		for (Behaviour behaviour : objects) {
			behaviour.Start();
		}
	}
	@Override
	public void OnRender() {
		for (Behaviour behaviour : objects) {
			behaviour.OnRender();
		}
		super.OnRender();
	}
	@Override
	public void Update() {
		for (Behaviour behaviour : objects) {
			behaviour.Update();
		}
		super.Update();
	}
	@Override
	public void Destroy() {
		for (Object object : objects) {
			object.Destroy();
		}
		super.Destroy();
	}
}
