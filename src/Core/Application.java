package Core;

import org.lwjgl.glfw.GLFW;

import Input.ActionMaps;
import Input.Input;

public class Application implements AppInterface {
	
	private MainCore mainCore;
	public Input input;
	
	protected ActionMaps inputMap;
	//Start the engine
	public Application() {
		
		mainCore = new MainCore();
		mainCore.start(this);
	}

	@Override
	public void OnClearFrame() {}

	@Override
	public void start() {
	}

	@Override
	public void run() {
	}

	@Override
	public void closed() {
		input.Destroy();
		Logger.Log("Application closed");
	}	
	
	public EngineService GetService(String name) {
		return mainCore.serviceManager.getService(name);
	}
	
	public void AddService(String name, EngineService service) {
		mainCore.serviceManager.AddService(name, service);
	}
	public void RemoveService(String name) {
		mainCore.serviceManager.RemoveService(name);
	}
	public boolean serviceExists(String name) {
		return mainCore.serviceManager.serviceExists(name);
	}
	public void Log(String text) {
		Logger.Log(text);
	}
	public void Quit() {
		GLFW.glfwSetWindowShouldClose(mainCore.display.window, true);
	}
}
