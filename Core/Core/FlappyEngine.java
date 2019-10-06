package Core;

import Editor.Editor;
import Entity.Camera;
import Entity.Scene;
import Maths.Matrix4;
import Maths.Vector2;
import Maths.Vector3;

public class FlappyEngine implements Runnable {

	public static final int ERROR = 0;
	public static final int LOG = 1;
	public static final String VERSION = "FlappyEngine v0.2.0 Pre-Alpha";
	
	Thread gameThread;
	
	GameLoop loop;
	private static Display display;
	static Scene currentScene;
	//Initialize engine and start
	float time = 0;
	int frames = 0;
	static boolean isFullscreen = false;
	
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
		Camera camera = new Camera();
		camera.transform.position = new Vector3(0, 0, -20);

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
			Time.unscaledDeltaTime = (startTime - frameTime);
			Time.deltaTime = Time.unscaledDeltaTime * Time.timeScale;
			Time.time += Time.deltaTime;
			
			update();
			render();
			frameTime = startTime;
		}
		cleanUp();
	}
	public void update() {
		display.update();
		
		time += Time.unscaledDeltaTime;
		frames++;
		
		if(time > 200) {
			setTitle("Fps: " + frames * 5);
			time = 0;
			frames = 0;
		}
		currentScene.Update();
		loop.Update();
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

	public static void setFullscreen(boolean mode) {
		isFullscreen = mode;
		display.setFullscreen(isFullscreen);
	}

	public static boolean fullscreen() {
		return isFullscreen;
	}

	public static Matrix4 getProjection() {
		float ar = display.size.x / display.size.y;
		return Matrix4.projection(70f, ar, 0.1f, 1000f);
	}
}
