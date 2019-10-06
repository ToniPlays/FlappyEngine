package GameMain;


import org.lwjgl.glfw.GLFW;

import ComponentSystem.Shader;
import Core.FlappyEngine;
import Core.GameLoop;
import Core.Time;
import Entity.Camera;
import Entity.GameObject;
import EventSystem.Input;
import Loaders.OBJLoader;
import Maths.Vector3;
import Mesh.Mesh;
import Mesh.MeshRenderer;

public class MainGame extends GameLoop {
	
	GameObject object;
	
	public static void main(String[] args) {
		new FlappyEngine(new MainGame());
	}

	@Override
	public void Start() {
		
		Shader shader = new Shader(new String[] {"res/shaders/bgVertex.glsl", "res/shaders/bgFragment.glsl"});
		object = new GameObject("Testing");
		
		Mesh mesh = OBJLoader.load("res/models/cube.obj");
		object.transform.position = new Vector3(0, 0, 10f);
		object.transform.scale = new Vector3(2f, 2f, 1f);

		object.addComponent(mesh);
		object.addComponent(new MeshRenderer(shader));
	}
	float time = 0;
	@Override
	public void Update() {
		time += Time.deltaTime; 
		
		object.transform.rotation = new Vector3((float) Math.sin(time) * 360f, (float) Math.sin(time) * 180, 0);
		
		if(Input.getKey(GLFW.GLFW_KEY_W)) {
			Camera.main.transform.position.z += Time.deltaTime;
		}
		if(Input.getKey(GLFW.GLFW_KEY_A)) {
			Camera.main.transform.position.x -= Time.deltaTime;
		}
		if(Input.getKey(GLFW.GLFW_KEY_S)) {
			Camera.main.transform.position.z -= Time.deltaTime;
		}
		if(Input.getKey(GLFW.GLFW_KEY_D)) {
			Camera.main.transform.position.x += Time.deltaTime;
		}
		if(Input.getKeyDown(GLFW.GLFW_KEY_SPACE)) {
			object.removeComponent(Mesh.class);
			object.addComponent(OBJLoader.load("res/models/models.obj"));
		}
		
	}

	@Override
	public void Render() {}

	@Override
	public void Destroy() {}
}
