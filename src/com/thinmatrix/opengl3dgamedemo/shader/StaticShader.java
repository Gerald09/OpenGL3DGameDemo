package com.thinmatrix.opengl3dgamedemo.shader;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/vertexShader";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0, "position");
    }
}
