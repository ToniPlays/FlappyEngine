#version 330

layout(location = 0) in vec4 position;
layout(location = 1) in vec3 color;

uniform mat4 transform;
uniform mat4 view;
uniform mat4 projection;
uniform vec3 light;

out vec3 colorOut;

void main() {
	gl_Position = projection * view * transform * position;
	colorOut = color * light;
}
