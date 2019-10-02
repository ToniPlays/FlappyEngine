package Core;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import ComponentSystem.Color;
import EventSystem.Input;
import Maths.Matrix4;
import Maths.Vector2;
import Maths.Vector3;

public class Display {
	
	Vector2 size;
	long window;
	private boolean wasResized = false;
	Input input;
	GLFWVidMode mode;
	
	public Display(Vector2 size) {
		this.size = size;
		
		//Init GLFW
		if(!glfwInit()) {
			FlappyEngine.log("Unable to init GLFW", FlappyEngine.ERROR);
		}
		
		mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
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
		Matrix4.projection(70f, size.x / size.y, 0f, 1000f);
		
		GLFWWindowSizeCallback resizeCallback = new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long window, int x, int y) {
				wasResized = true;
				size.x = x;
				size.y = y;
				Matrix4.projection(70f, size.x / size.y, 0.1f, 1000f);
			}
		};
		glfwSetWindowSizeCallback(window, resizeCallback);
		input = new Input();
		glfwSetKeyCallback(window, input);
		FlappyEngine.log("Created callbacks", FlappyEngine.LOG);
	}
	
	public void update() {
		input.update();
		
		if(wasResized) {
			GL11.glViewport(0, 0, (int) size.x, (int) size.y);
			wasResized = false;
		}
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		setClearColor(Color.WHITE);
		
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
	
	public void setClearColor(Color color) {
		Vector3 c = color.getColor();
		GL11.glClearColor(c.x, c.y, c.z, 1.0f);
	}
	
	public void setClearColor(Vector3 c) {
		GL11.glClearColor(c.x, c.y, c.z, 1.0f);
	}
	
	public void setTitle(String title) {
		glfwSetWindowTitle(window, title);
	}

	public void setFullscreen(boolean isFullscreen) {
		if(isFullscreen) {
			glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), 0, 0, (int) size.x, (int) size.y, 0);
		}
		else {
			
			glfwSetWindowMonitor(window, 0, (mode.width() - (int) size.x) / 2, (mode.height() - (int) size.y) / 2, mode.width(), mode.height(), 0);
		}
	} 
}
