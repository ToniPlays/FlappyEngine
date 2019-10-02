package EventSystem;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {

	private static final int up = 0;
	private static final int pressed = 1;
	private static final int down = 2;
	private static final int released = 3;
	/* 0 = up
	 * 1 = pressed
	 * 2 = held down
	 * 3 = key up
	 */
	
	public static int[] keys = new int[GLFW.GLFW_KEY_LAST];
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(keys[key] == 2 && action == 0)
			action = 3;
		keys[key] = action;
		
	}
	
	public void update() {
		for (int i = 0; i < keys.length; i++) {
			if(keys[i] == pressed) {
				keys[i] = down;
			}
			else if(keys[i] == released) {
				keys[i] = up;
			}
		}
	}
	
	public static boolean getKey(int key) {
		int i = keys[key];
		if(i == pressed || i == down) 
			return true;
		return false;
	}
	
	public static boolean getKeyDown(int key) {
		return keys[key] == pressed;
	}
	
	public static boolean getKeyUp(int key) {
		return keys[key] == released;
	}
}
