package ComponentSystem;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import Core.FlappyEngine;
import Loaders.ShaderLoader;
import Maths.Matrix4f;
import Maths.Vector2;
import Maths.Vector3;

public class Shader {
	
	private HashMap<String, Integer> locations = new HashMap<String, Integer>();
	
	private int programID, vertexID, fragmentID;
	
	public Shader(String[] shaders) {

		Create(shaders);
	}
	
	public void Create(String[] shaders) {
		
		programID = GL20.glCreateProgram();
		vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		
		String file = ShaderLoader.load(shaders[0]);
		
		GL20.glShaderSource(vertexID, file);
		GL20.glCompileShader(vertexID);
		
		if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			FlappyEngine.log("Vertex Shader: " + GL20.glGetShaderInfoLog(vertexID), FlappyEngine.ERROR);
		}
		
		fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		
		file = ShaderLoader.load(shaders[1]);
		GL20.glShaderSource(fragmentID, file);
		GL20.glCompileShader(fragmentID);
		
		if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			FlappyEngine.log("Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentID), FlappyEngine.ERROR);
		}
		
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		
		GL20.glLinkProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			FlappyEngine.log("Program Linking: " + GL20.glGetProgramInfoLog(programID), FlappyEngine.ERROR);
		}
		
		GL20.glValidateProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
			FlappyEngine.log("Program Validation: " + GL20.glGetProgramInfoLog(programID), FlappyEngine.ERROR);
		}
	}
	
	public void bind() {
		GL30.glUseProgram(programID);
	}
	public void unbind() {
		GL30.glUseProgram(0);
	}
	
	public void Destroy() {
		
		GL30.glDetachShader(programID, vertexID);
		GL30.glDetachShader(programID, fragmentID);
		GL30.glDeleteShader(vertexID);
		GL30.glDeleteShader(fragmentID);
		GL30.glDeleteProgram(programID);
	}
	
	public int getUniformLocation(String name) {
		if(locations.containsKey(name)) {
			return locations.get(name);
		}
		int location = GL30.glGetUniformLocation(programID, name);
		if(location == -1) 
			FlappyEngine.log("Location for " + name + " was not found", FlappyEngine.LOG);
		return location;
	}
	
	public void setUniform(String name, int value) {
		GL30.glUniform1ui(getUniformLocation(name), value);
	}
	
	public void setUniform(String name, float value) {
		GL30.glUniform1f(getUniformLocation(name), value);
	}
	
	public void setUniform(String name, Vector2 value) {
		GL30.glUniform2f(getUniformLocation(name), value.x, value.y);
	}
	
	public void setUniform(String name, Vector3 value) {
		GL30.glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
	}
	
	public void setUniform(String name, Matrix4f value) {
		GL30.glUniformMatrix4fv(getUniformLocation(name), true, Matrix4f.getAll(value));
	}
	
	public void setUniform(String name, boolean value) {
		GL30.glUniform1ui(getUniformLocation(name), value ? 1 : 0);
	}
}
