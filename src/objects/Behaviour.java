package objects;

import Core.MainCore;

public class Behaviour extends Component {

	public Behaviour(String name) {
		super(name);
		this.name = name;
		ObjectService obj = (ObjectService) MainCore.app.GetService("Hierarchy");
		if(obj != null) 
			obj.AddObject(this);
	}
	public void Awake() {}
	public void Start() {}
	public void Update() {
		super.Update();
		
	}
}
