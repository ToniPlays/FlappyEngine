package Loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import ComponentSystem.Color;
import Core.FlappyEngine;
import Maths.Vector3;
import Mesh.Mesh;
import Mesh.Vertex;

public class OBJLoader {
	public static Mesh load(String path) {
		String[] split = path.split("\\.");

		if (!split[split.length - 1].equals("obj"))
			FlappyEngine.log("Filetype not supported", FlappyEngine.ERROR);

		ArrayList<Vertex> verticesList = new ArrayList<Vertex>();
		ArrayList<Integer> indicesList = new ArrayList<Integer>();

		try {
			BufferedReader rd = new BufferedReader(new FileReader(new File(path)));

			String line = "";
			while ((line = rd.readLine()) != null) {
				String[] tokens = Utils.removeEmpty(line.split(" "));
				if (tokens.length == 0 || tokens[0].equals("#"))
					continue;
				else if (tokens[0].equals("v")) {
					verticesList.add(new Vertex(
							new Vector3(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3])), Color.BLUE));
				} else if (tokens[0].equals("f")) {
					for (int i = 1; i < tokens.length; i++) {
						String[] splitted = tokens[i].split("/");
						indicesList.add(Integer.parseInt(splitted[0]) - 1);
					}

				}
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Vertex[] vertices = new Vertex[verticesList.size()];
		Integer[] indices = new Integer[indicesList.size()];

		System.out.println("Loaded mesh with " + vertices.length + ", indices " + indices.length);
		verticesList.toArray(vertices);
		indicesList.toArray(indices);
		Mesh mesh = new Mesh(vertices, Utils.toIntArray(indices));
		mesh.fileName = path;
		return mesh;
	}
}
