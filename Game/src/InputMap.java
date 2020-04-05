import org.lwjgl.glfw.GLFW;

import Core.Logger;
import Input.ActionMap;
import Input.ActionMaps;

public class InputMap extends ActionMaps {
	
	@Override
	public void InitInput() {
		
		ActionMap movement = AddMap("Movement", true);
		movement.AddAction("W", GLFW.GLFW_KEY_W);
		movement.AddAction("A", GLFW.GLFW_KEY_A);
		movement.AddAction("S", GLFW.GLFW_KEY_S);
		movement.AddAction("D", GLFW.GLFW_KEY_D);
		ActionMap test = AddMap("Test", true);
		test.AddAction("Toggle", GLFW.GLFW_KEY_SPACE).performed = () -> {
			map("Movement").setActive(!map("Movement").isActive());
		};
		
		Logger.LogError(this.toString());
	}
}
