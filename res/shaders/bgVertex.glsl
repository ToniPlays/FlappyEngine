#version 330

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 color;

uniform mat4 transform;
uniform mat4 view;
uniform mat4 projection;

out vec3 colorOut;

void main() {
	gl_Position = transform * vec4(position, 1.0f);
	colorOut = color * position;
}
