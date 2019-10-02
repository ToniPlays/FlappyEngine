package Core;

import Editor.Editor;
import Entity.Scene;
import Maths.Vector2;

public class FlappyEngine implements Runnable {

	public static final int ERROR = 0;
	public static final int LOG = 1;
	public static final String VERSION = "FlappyEngine v0.0.1 Pre-Alpha";
	
	Thread gameThread;
	
	GameLoop loop;
	Display display;
	static Scene currentScene;
	//Initialize engine and start
	
	public FlappyEngine(GameLoop game) {
		loop = game;
		gameThread = new Thread(this, "Game");
		gameThread.start();
		Thread editor = new Thread(new Editor(), "Editor");
		editor.start();
	}
	
	public void start() {
		display = new Display(new Vector2(1280, 720));
		currentScene = new Scene("Init scene");
		loop.Start();
		FlappyEngine.log("Engine initialization complete", FlappyEngine.LOG);
	}
	
	@Override
	public void run() {
		start();
		
		while(!display.isCloseRequested()) {
			update();
			render();
		}
		
		cleanUp();
	}
	public void update() {
		
		currentScene.Update();
		loop.Update();
		display.update();
	}
	public void render() {
		currentScene.Render();
		loop.Render();
		display.swapBuffers();
	}
	private void cleanUp() {
		currentScene.Destroy();
		loop.Destroy();
		display.destroy();
		log("Engine shutdown", LOG);
	}
	public static Scene getCurrentScene() {
		return currentScene;
	}
	
	//Engine logging
	public static void log(String text, int i) {
		
		String log = java.time.LocalTime.now() + ": " + text;
		if(i == ERROR) {
			System.err.println(log);
			System.exit(0);
		}
		else System.out.println(log);
	}
}
