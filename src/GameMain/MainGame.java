package GameMain;


import org.lwjgl.glfw.GLFW;

import ComponentSystem.Shader;
import Core.FlappyEngine;
import Core.GameLoop;
import Core.Time;
import Entity.Camera;
import Entity.Object;
import EventSystem.Input;
import Loaders.OBJLoader;
import Maths.Vector3;
import Mesh.Mesh;
import Mesh.MeshRenderer;

public class MainGame extends GameLoop {
	
	Object object;
	
	public static void main(String[] args) {
		new FlappyEngine(new MainGame());
	}

	@Override
	public void Start() {
		Shader shader = new Shader(new String[] {"res/shaders/bgVertex.glsl", "res/shaders/bgFragment.glsl"});
		object = new Object("Testing");
		
		Mesh mesh = OBJLoader.load("res/models/cube.obj");
		object.transform.position = new Vector3(0, 0, -20f);
		object.transform.scale = new Vector3(1f, 1f, 1f);

		object.addComponent(mesh);
		object.addComponent(new MeshRenderer(shader));
	}

	@Override
	public void Update() {
		object.transform.scale = new Vector3(30f, 30f, 3f);
		float speed = 0.05f;
		System.out.println(Camera.main.transform.position);
		if(Input.getKey(GLFW.GLFW_KEY_W)) {
			Camera.main.transform.position.x += Time.deltaTime * speed;
		}
		if(Input.getKey(GLFW.GLFW_KEY_A)) {
			Camera.main.transform.rotation.y -= Time.deltaTime * speed;
		}
		if(Input.getKey(GLFW.GLFW_KEY_S)) {
			Camera.main.transform.position.x -= Time.deltaTime * speed;
		}
		if(Input.getKey(GLFW.GLFW_KEY_D)) {
			Camera.main.transform.rotation.y += Time.deltaTime * speed;
		}
		if(Input.getKeyDown(GLFW.GLFW_KEY_SPACE)) {
			object.removeComponent(Mesh.class);
			object.addComponent(OBJLoader.load("res/models/models.obj"));
		}
	}

	@Override
	public void Render() {
		
	}

	@Override
	public void Destroy() {
		
	}
}
