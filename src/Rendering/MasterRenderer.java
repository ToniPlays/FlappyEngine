package Rendering;

import Core.EngineService;

public class MasterRenderer extends EngineService {
	
	static Shader shader;
	static Mesh mesh;
	
	@Override
	public void Awake() {
		
//		super.Awake();
//		HashMap<String, String> shaders = new HashMap<String, String>();
//		shaders.put("vertex", "vertex");
//		shaders.put("fragment", "fragment");
//		
//		shader = new Shader(shaders);
//		Vertex[] vertices = {
//				new Vertex(new Vector3(-0.5f, -0.5f, 0), Color.fromHex("#0B3F7E")), 
//				new Vertex(new Vector3(-0.5f, 0.5f, 0), Color.fromHex("#1473e6")), 
//				new Vertex(new Vector3(0.5f, 0.5f, 0), Color.fromHex("#1473e6")), 
//				new Vertex(new Vector3(0.5f, -0.5f, 0), Color.fromHex("#0B3F7E"))};
//		
//		int[] indices = {0, 1, 2, 0, 2, 3};
//		mesh = new Mesh(vertices, indices);
	}
	
	public void OnRender() {
		
//		if(shader == null) return;
//		
//		GL30.glBindVertexArray(mesh.getVAO());
//		GL30.glEnableVertexAttribArray(0);
//		GL30.glEnableVertexAttribArray(1);
//		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
//		
//		shader.bind();
//		
//		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
//		shader.unbind();
//		
//		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//		
//		GL30.glDisableVertexAttribArray(0);
//		GL30.glDisableVertexAttribArray(1);
//		GL30.glBindVertexArray(0);
	}
	@Override
	public void Destroy() {
		
	}
}
