#version 330 core

in vec3 colorOut;
out vec4 outColor;

void main() {
	outColor = vec4(colorOut, 1.0f);
}
