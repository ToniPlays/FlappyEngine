package Core;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

import Maths.Vector2;

public class Display {
	
	Vector2 size;
	
	public Display(Vector2 size) {
		this.size = size;
		//Init GLFW
		
		if(!glfwInit()) {
			FlappyEngine.log("Unable to init GLFW", FlappyEngine.ERROR);
		}
		
		GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		FlappyEngine.log("Display has been created", FlappyEngine.LOG);
	}
}
