package Core;

public class EngineService extends Service {

	
	private boolean isActive = true;
	
	@Override
	public void Awake() {
		Logger.Log("Awake on " + this.getClass().getName());
	}
	
	@Override
	public void Start() {}
	
	@Override
	public void Update() {}

	@Override
	public void LateUpdate() {}
	
	@Override
	public void OnRender() {}
	@Override
	public void OnEnable() {
		Logger.Log("Enabled " + this.getClass().getName());
	}

	@Override
	public void OnDisable() {
		Logger.Log("Disabled " + this.getClass().getName());
	}

	@Override
	public void Destroy() {
		Logger.Log("Destroy on " + this.getClass().getName());
	}

	public void setActive(boolean active) {
		
		if(active != isActive) {
			isActive = active;
			if(isActive) OnEnable();
			else OnDisable();
		}
	}
	public boolean isActive() {
		return isActive;
	}
}
