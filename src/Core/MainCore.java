package Core;

import org.lwjgl.glfw.GLFW;

import Input.Input;
import Rendering.Display;
import Rendering.MasterRenderer;
import Utility.Time;
import objects.ObjectService;

public class MainCore {
	
	public static Application app;
	Display display;
	ServiceManager serviceManager;
	
	public void start(Application app) {
		
		MainCore.app = app;
		serviceManager = new ServiceManager();
		serviceManager.AddService("MasterRenderer", new MasterRenderer());
		serviceManager.AddService("Hierarchy", new ObjectService());
		display = new Display(1280, 720, "BlowEngine");
		app.input = new Input();
		GLFW.glfwSetKeyCallback(display.window, app.input);
		Logger.Log("Engine startup");
		
		run();
	}
	//Main loop
	public void run() {
		
		serviceManager.Awake();
		serviceManager.Start();
		app.input.Start();
		app.start();
		
		long startTime = System.currentTimeMillis();
		long frameTime = startTime;
		
		while(!display.isCloseRequested()) {
			
			startTime = System.currentTimeMillis(); 
			Time.unscaledDeltaTime = (startTime - frameTime) / Time.SECOND;
			Time.deltatime = Time.unscaledDeltaTime * Time.timeScale;
			Time.time += Time.deltatime;
			
			update();
			lateUpdate();
			render();
			frameTime = startTime;
		}
		cleanUp();
	}
	//Update objects
	public void update() {
		app.input.Update();
		app.run();
		serviceManager.UpdateAll();
	}
	public void lateUpdate() {
		serviceManager.LateUpdate();
	}
	//Render all
	public void render() {
		serviceManager.Render();
		app.OnClearFrame();
		display.ClearFrame();
	}
	//On close
	public void cleanUp() {
		app.closed();
		serviceManager.DestroyAll();
		display.destroy();
		Logger.Log("Engine closed");
	}
}
