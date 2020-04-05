package Input;

import org.lwjgl.glfw.GLFW;

public class InputAction {
	
	int key;
	int lastState = GLFW.GLFW_RELEASE;
	public String name;
	public InputCallback performed;
	
	public InputAction(String name, int key) {
		this.name = name;
		this.key = key;
	}
	public void action(int action) {
		if(performed != null && lastState != GLFW.GLFW_PRESS && action == GLFW.GLFW_PRESS)
			performed.performed();
	}
}
