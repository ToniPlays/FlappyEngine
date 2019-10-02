package GameMain;


import org.lwjgl.glfw.GLFW;

import ComponentSystem.Color;
import ComponentSystem.Shader;
import Core.FlappyEngine;
import Core.GameLoop;
import Core.Time;
import Entity.Object;
import EventSystem.Input;
import Maths.Vector3;
import Mesh.Mesh;
import Mesh.MeshRenderer;
import Mesh.Vertex;

public class MainGame extends GameLoop {
	
	Object object;
	
	public static void main(String[] args) {
		new FlappyEngine(new MainGame());
	}

	@Override
	public void Start() {
		Shader shader = new Shader(new String[] {"res/shaders/bgVertex.glsl", "res/shaders/bgFragment.glsl"});
		object = new Object("Testing");
		Mesh mesh = new Mesh(new Vertex[] {
				new Vertex(new Vector3(-0.5f, 0.5f, 0f), new Color(19, 160, 50).getColor()), 
				new Vertex(new Vector3(-0.5f, -0.5f, 0f), new Color(19, 200, 36).getColor()),
				new Vertex(new Vector3(0.5f, -0.5f, 0f), new Color(19, 189, 10).getColor()),
				new Vertex(new Vector3(0.5f, 0.5f, 0f), new Color(19, 150, 36).getColor())
		}, new int[] {
				0, 1, 2,
				0, 3, 2
		});
		object.addComponent(mesh);
		object.addComponent(new MeshRenderer(shader));
	}

	@Override
	public void Update() {
		float speed = 0.1f;
		if(Input.getKeyDown(GLFW.GLFW_KEY_SPACE)) {
			MeshRenderer rd = (MeshRenderer) object.getComponent(MeshRenderer.class);
			rd.setActive(!rd.isActive());
		}
		
		if(Input.getKey(GLFW.GLFW_KEY_W)) {
			object.transform.scale.x += Time.deltaTime * speed * 0.01f;
		}
		if(Input.getKey(GLFW.GLFW_KEY_A)) {
			object.transform.position.z -= Time.deltaTime * speed;
		}
		if(Input.getKey(GLFW.GLFW_KEY_S)) {
			object.transform.scale.x -= Time.deltaTime * speed * 0.01f;
		}
		if(Input.getKey(GLFW.GLFW_KEY_D)) {
			object.transform.position.z += Time.deltaTime * speed;
		}
	}

	@Override
	public void Render() {
		
	}

	@Override
	public void Destroy() {
		
	}
}
