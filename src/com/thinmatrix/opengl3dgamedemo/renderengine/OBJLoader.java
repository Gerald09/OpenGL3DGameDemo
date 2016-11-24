package com.thinmatrix.opengl3dgamedemo.renderengine;

import com.thinmatrix.opengl3dgamedemo.models.RawModel;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {
    public static RawModel loadOBJModel(String filename, Loader loader) {
        FileReader fr = null;
        try {
            fr = new FileReader(new File("res/" + filename + ".obj"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        int[] indicesArray = null;
        try {
            while (true) {
                line = br.readLine();
                String[] currentLine = line.split(" ");
                if (line.startsWith("v ")) {
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    vertices.add(vertex);
                } else if (line.startsWith("vt ")) {
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                } else if (line.startsWith("vn ")) {
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                } else if (line.startsWith("f ")) {
                    textureArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }
            while (line != null) {
                if (!line.startsWith("f ")) {
                    line = br.readLine();
                    break;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");
                processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);

                line = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        verticesArray = new float[vertices.size() * 3];
        indicesArray = new int[indices.size()];
        int vertexPointer = 0;
        for (Vector3f vertex : vertices) {
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }

        for (int i = 0; i < indices.size(); i++) {
            indicesArray[i] = indices.get(i);
        }
        return loader.loadToVao(verticesArray, textureArray, indicesArray);
    }

    private static void processVertex(String[] vertexData,
                                      List<Integer> indices,
                                      List<Vector2f> textures,
                                      List<Vector3f> normals,
                                      float[] textureArray,
                                      float[] normalArray) {
        int currentVertexPosition = Integer.parseInt(vertexData[0]) - 1;
        indices.add(currentVertexPosition);

        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
        textureArray[currentVertexPosition * 2] = currentTex.x;
        textureArray[currentVertexPosition * 2 + 1] = 1 - currentTex.y;

        Vector3f currentNormal = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalArray[currentVertexPosition * 3] = currentNormal.x;
        normalArray[currentVertexPosition * 3 + 1] = currentNormal.y;
        normalArray[currentVertexPosition * 3 + 2] = currentNormal.z;
    }
}
