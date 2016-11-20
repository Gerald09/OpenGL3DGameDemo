package com.thinmatrix.opengl3dgamedemo;

import com.thinmatrix.opengl3dgamedemo.models.TextureModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.DisplayManager;
import com.thinmatrix.opengl3dgamedemo.renderengine.Loader;
import com.thinmatrix.opengl3dgamedemo.models.RawModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.Renderer;
import com.thinmatrix.opengl3dgamedemo.shader.StaticShader;
import com.thinmatrix.opengl3dgamedemo.textures.ModelTexture;

import org.lwjgl.opengl.Display;

public class Main {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader staticShader = new StaticShader();

        // 顶点坐标
        float[] vertices = {
                -0.5f, 0.5f, 0f, // v0
                -0.5f, -0.5f, 0f, // v1
                0.5f, -0.5f, 0f, // v2
                0.5f, 0.5f, 0f, // v3
        };

        // texture坐标点
        float[] textureCoords = {
                0, 0, // v0
                0, 1, // v1
                1, 1, // v2
                1, 0, // v3
        };

        // 顶点顺序
        int[] indices = {
                0, 1, 3,
                3, 1, 2,
        };

        RawModel model = loader.loadToVao(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("facebook-256"));
        TextureModel textureModel = new TextureModel(model, texture);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            staticShader.start();
            renderer.render(textureModel);
            staticShader.stop();

            DisplayManager.updateDisplay();
        }

        staticShader.cleanUp();
        loader.cleanUp();

        DisplayManager.closeDisplay();
    }
}
