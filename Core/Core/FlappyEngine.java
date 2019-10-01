package Core;

import Maths.Vector2;

public class FlappyEngine implements Runnable {

	public static final int ERROR = 0;
	public static final int LOG = 1;
	
	Thread gameThread;
	GameLoop loop;
	Display display;
	//Initialize engine and start
	
	public FlappyEngine(GameLoop game) {
		loop = game;
		gameThread = new Thread(this, "Game");
		gameThread.run();
		FlappyEngine.log("Engine initialization", FlappyEngine.LOG);
	}
	
	public void start() {
		display = new Display(new Vector2(1280, 720));
	}
	
	@Override
	public void run() {
		start();
		
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
