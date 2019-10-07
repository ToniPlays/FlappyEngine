package Mesh;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import ComponentSystem.FlappyComponent;
import ComponentSystem.Shader;

public class MeshRenderer extends FlappyComponent {
	
	Shader shader;
	
	public MeshRenderer(Shader shader) {
		this.shader = shader;
	}
	
	@Override
	public void Render() {
		
		if(!gameObject.hasComponent(Mesh.class) || shader == null) {
			return;
		}
		
		Mesh mesh = (Mesh) gameObject.getComponent(Mesh.class);
		
		GL30.glBindVertexArray(mesh.getVAO());
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		shader.bind();
		shader.setUniform("transform", gameObject.transform.getProjectedTransform());
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	public void Destroy() {
		shader.Destroy();
	}
	@Override
	public String toString() {
		return "Meshrenderer for " + gameObject.name;
	}
}