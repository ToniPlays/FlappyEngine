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
	private static Display display;
	static Scene currentScene;
	//Initialize engine and start
	float time = 0;
	int frames = 0;
	
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
		long startTime = System.currentTimeMillis();
		long frameTime = 0;
				
		while(!display.isCloseRequested()) {
			
			startTime = System.currentTimeMillis(); 
			Time.deltaTime = (startTime - frameTime);
			
			update();
			render();
			frameTime = startTime;
		}
		cleanUp();
	}
	public void update() {
		time += Time.deltaTime;
		frames++;
		
		if(time > 200) {
			setTitle("Fps: " + frames * 5);
			time = 0;
			frames = 0;
		}
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
	public static void setTitle(String title) {
		display.setTitle(VERSION + " | " + title);
	}
}
