package com.thinmatrix.opengl3dgamedemo.shader;

import com.thinmatrix.opengl3dgamedemo.renderengine.Loader;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/vertexShader";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(Loader.VERTEX_INDEX, "position");
        bindAttribute(Loader.TEXTURE_COORDS_INDEX, "textureCoords");
    }
}
