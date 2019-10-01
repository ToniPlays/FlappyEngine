package Core;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import Maths.Vector2;

public class Display {
	
	Vector2 size;
	long window;
	private boolean wasResized = false;
	
	public Display(Vector2 size) {
		this.size = size;
		//Init GLFW
		
		if(!glfwInit()) {
			FlappyEngine.log("Unable to init GLFW", FlappyEngine.ERROR);
		}
		
		GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		window = glfwCreateWindow((int) size.x, (int) size.y, FlappyEngine.VERSION, 0, 0);
		glfwSetWindowPos(window, (mode.width() - (int) size.x) / 2, (mode.height() - (int) size.y) / 2);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		createCallbacks();
		
		glfwShowWindow(window);
		glfwSwapInterval(0);
		FlappyEngine.log("Display has been created", FlappyEngine.LOG);
	}
	
	public void createCallbacks() {
		GLFWWindowSizeCallback resizeCallback = new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long window, int x, int y) {
				wasResized = true;
				size.x = x;
				size.y = y;
			}
		};
		glfwSetWindowSizeCallback(window, resizeCallback);
	}
	
	public void update() {
		if(wasResized) {
			GL11.glViewport(0, 0, (int) size.x, (int) size.y);
			wasResized = false;
		}
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		glfwPollEvents();
	}
	
	public void destroy() {
		
		glfwDestroyWindow(window);
		glfwTerminate();
	}

	public boolean isCloseRequested() {
		return glfwWindowShouldClose(window);
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
	}
}