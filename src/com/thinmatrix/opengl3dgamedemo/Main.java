package com.thinmatrix.opengl3dgamedemo;

import com.thinmatrix.opengl3dgamedemo.renderengine.DisplayManager;
import com.thinmatrix.opengl3dgamedemo.renderengine.Loader;
import com.thinmatrix.opengl3dgamedemo.renderengine.RawModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.Renderer;

import org.lwjgl.opengl.Display;

public class Main {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                // left bottom triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                // right top triangle
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        RawModel model = loader.loadToVao(vertices);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            // game logic
            renderer.render(model);
            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
