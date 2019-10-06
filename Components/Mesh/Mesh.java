package Mesh;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;


import org.lwjgl.system.MemoryUtil;

import ComponentSystem.FlappyComponent;

public class Mesh extends FlappyComponent {
	
	private int vao, pbo, ibo, cbo;
	private int[] indices;
	Vertex[] vertices;
	
	public Mesh(Vertex[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
		
		//Generate vertex array
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		
		//Create vertex position buffer
		FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] posData = new float[vertices.length * 3];
		
		for (int i = 0; i < vertices.length; i++) {
			posData[i * 3] = vertices[i].getPosition().x;
			posData[i * 3 + 1] = vertices[i].getPosition().y;
			posData[i * 3 + 2] = vertices[i].getPosition().z;
		}
		
		positionBuffer.put(posData).flip();
		pbo = storeData(positionBuffer, 0, 3);
		
		//Create vertex color buffer
		FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] colorData = new float[vertices.length * 3];
		
		for (int i = 0; i < vertices.length; i++) {
			colorData[i * 3] = vertices[i].getColor().x;
			colorData[i * 3 + 1] = vertices[i].getColor().y;
			colorData[i * 3 + 2] = vertices[i].getColor().z;
		}
		colorBuffer.put(colorData).flip();
		cbo = storeData(colorBuffer, 1, 3);
		
		//Create indices buffer
		IntBuffer indiceBuffer = MemoryUtil.memAllocInt(indices.length);
		indiceBuffer.put(indices).flip();
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indiceBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public int storeData(FloatBuffer buffer, int index, int size) {
		int id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, id);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return id;
	}
	
	public void Destroy() {
		
		glDeleteBuffers(pbo);
		glDeleteBuffers(ibo);
		glDeleteBuffers(cbo);
		glDeleteVertexArrays(vao);
	}

	public int getVAO() {
		return vao;
	}
	public int getIBO() {
		return ibo;
	}
	public int getPBO() {
		return pbo;
	}
	public int getCBO() {
		return cbo;
	}
	public int[] getIndices() {
		return indices;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Vertex i : vertices) {
			result.append(i.getPosition().toString()).append("\n");
		}
		result.append("\n");
		for (int i = 0; i < indices.length; i+=3) {
			result.append(indices[i] + "/").append(indices[i + 1] + "/").append(indices[i + 2] + "\n");
		}
		
		return result.toString();
	}
}
