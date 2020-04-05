package Core;

public abstract class Service {
	
	public abstract void Start();
	public abstract void Awake();
	public abstract void Update();
	public abstract void LateUpdate();
	public abstract void OnRender();
	public abstract void OnEnable();
	public abstract void OnDisable();
	public abstract void Destroy();
}
