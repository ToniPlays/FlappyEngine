import org.lwjgl.opengl.GL11;

import Core.Application;
import Rendering.Display;
import Rendering.Mesh;
import Rendering.Vertex;
import Utility.Color;
import components.MeshRenderer;
import math.Vector3;
import objects.GameObject;

public class SandboxMain extends Application {
	
	int frames = 0;
	float time;
	
	public static void main(String[] args) {
		new SandboxMain();
	}
	@Override
	public void start() {
		
		inputMap = new InputMap();
		input.setMapping(inputMap);
		super.start();
		Log("Application started");
		Display.SetTitle("Sandbox");
		GL11.glClearColor(0.1f, 0.1f, 0.1f, 1);

		Vertex[] Rvertices = {
				new Vertex(new Vector3(-0.8f, -0.5f, 0), Color.fromHex("#BA0000")), 
				new Vertex(new Vector3(-0.8f, 0.5f, 0), Color.fromHex("#FF1717")), 
				new Vertex(new Vector3(-0.1f, 0.5f, 0), Color.fromHex("#FF1717")), 
				new Vertex(new Vector3(-0.1f, -0.5f, 0), Color.fromHex("#BA0000"))};
		
		int[] indices = {0, 1, 2, 0, 2, 3};
		Mesh red = new Mesh(Rvertices, indices);
		
		
		Vertex[] Bvertices = {
				new Vertex(new Vector3(0.1f, -0.5f, 0), Color.fromHex("#0D4A93")), 
				new Vertex(new Vector3(0.1f, 0.5f, 0), Color.fromHex("#1473e6")), 
				new Vertex(new Vector3(0.8f, 0.5f, 0), Color.fromHex("#1473e6")), 
				new Vertex(new Vector3(0.8f, -0.5f, 0), Color.fromHex("#0D4A93"))};
		
		Mesh blue = new Mesh(Bvertices, indices);
		
		GameObject redGO = new GameObject("Red");
		GameObject BlueGO = new GameObject("Blue");
		
		redGO.AddComponent(new MeshRenderer(red));
		BlueGO.AddComponent(new MeshRenderer(blue));
		
		inputMap.map("Movement").getAction("A").performed = () -> {
			redGO.setActive(!redGO.isActive());
		};
		inputMap.map("Movement").getAction("D").performed = () -> {
			BlueGO.setActive(!BlueGO.isActive());
		};
		
	}
	
	@Override
	public void run() {
		super.run();
	}	
	@Override
	public void closed() {
		super.closed();
	}
}
