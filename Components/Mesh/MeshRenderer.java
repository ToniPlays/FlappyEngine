package Mesh;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import ComponentSystem.FlappyComponent;
import ComponentSystem.Shader;
import Core.FlappyEngine;
import Maths.Matrix4;
import Maths.Vector3;

public class MeshRenderer extends FlappyComponent {
	
	Mesh mesh;
	Shader shader;
	
	public MeshRenderer(Shader shader) {
		this.shader = shader;
	}
	@Override
	public void Render() {
		if(!parent.hasComponent(Mesh.class) || shader == null) {
			return;
		}
		if(mesh == null) {
			mesh = (Mesh) parent.getComponent(Mesh.class);
		}
		
		GL30.glBindVertexArray(mesh.getVAO());
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		shader.bind();
		shader.setUniform("transform", Matrix4.multiply(parent.transform.getMatrix(), FlappyEngine.getProjection()));
		shader.setUniform("light", new Vector3(1, 1, 1));
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}