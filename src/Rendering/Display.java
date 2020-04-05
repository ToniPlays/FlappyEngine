package Rendering;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import math.Vector2;

public class Display {

	public long window = -1;
	private boolean wasResized = false;
	Vector2 size;
	static String title = "";

	public Display(int w, int h, String title) {

		size = new Vector2(w, h);
		//Init GLFW functions
		if(!GLFW.glfwInit()) {
			throw new IllegalStateException("Unable to init GLFW");
		}
		//Set window hints
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

		//Create window
		window = GLFW.glfwCreateWindow(w, h, title, 0, 0);
		if(window == -1) {
			throw new IllegalStateException("Could not create window");
		}

		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(window);

		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glFrontFace(GL11.GL_CW);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_CULL_FACE);

		GLFWWindowSizeCallback resizeCallback = new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long window, int x, int y) {
				wasResized = true;
				size.x = x;
				size.y = y;
			}
		};
		GLFW.glfwSetWindowSizeCallback(window, resizeCallback);
	}
	public void ClearFrame() {
		
		if(wasResized) {
			GL11.glViewport(0, 0, (int) size.x, (int) size.y);
			wasResized = false;
		}
		
		GLFW.glfwSetWindowTitle(window, title);
		
		GLFW.glfwSwapBuffers(window);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GLFW.glfwPollEvents();
	}
	public void destroy() {
		GLFW.glfwDestroyWindow(window);
	}
	public boolean isCloseRequested() {
		return GLFW.glfwWindowShouldClose(window);
	}
	public static void SetTitle(String text) {
		title = text + " | BlowEngine";
	}
}
