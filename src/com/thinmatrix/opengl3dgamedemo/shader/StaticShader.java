package com.thinmatrix.opengl3dgamedemo.shader;

import com.thinmatrix.opengl3dgamedemo.renderengine.Loader;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/vertexShader";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader";

    private int location_transformationMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(Loader.VERTEX_INDEX, "position");
        bindAttribute(Loader.TEXTURE_COORDS_INDEX, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = getUniformLocation("transformationMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix4f) {
        loadMatrix(location_transformationMatrix, matrix4f);
    }

}
