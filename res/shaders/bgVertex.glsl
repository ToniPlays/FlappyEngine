#version 330

layout(location = 0) in vec4 position;
layout(location = 1) in vec3 color;

uniform mat4 transform;
uniform vec3 light;

out vec3 colorOut;

void main() {
	gl_Position = position * transform;
	colorOut = color * light;
}
