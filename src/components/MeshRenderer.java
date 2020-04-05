package components;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import Rendering.Mesh;
import Rendering.Shader;
import objects.Component;

public class MeshRenderer extends Component {
	
	Shader shader;
	Mesh mesh;
	
	public MeshRenderer(Mesh mesh) {
		super("MeshRenderer");
		this.mesh = mesh;
	}
	
	@Override
	public void Awake() {
		super.Awake();
		HashMap<String, String> shaders = new HashMap<String, String>();
		shaders.put("vertex", "vertex");
		shaders.put("fragment", "fragment");
		
		shader = new Shader(shaders);
	}
	@Override
	public void OnRender() {
		
		if(shader == null) 
			return;
		
		GL30.glBindVertexArray(mesh.getVAO());
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		shader.bind();
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	@Override
	public void Destroy() {
		shader.Destroy();
		mesh.Destroy();
		super.Destroy();
	}
}
