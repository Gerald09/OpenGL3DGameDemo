package com.thinmatrix.opengl3dgamedemo;

import com.thinmatrix.opengl3dgamedemo.entities.Camera;
import com.thinmatrix.opengl3dgamedemo.entities.Entity;
import com.thinmatrix.opengl3dgamedemo.models.TextureModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.DisplayManager;
import com.thinmatrix.opengl3dgamedemo.renderengine.Loader;
import com.thinmatrix.opengl3dgamedemo.models.RawModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.OBJLoader;
import com.thinmatrix.opengl3dgamedemo.renderengine.Renderer;
import com.thinmatrix.opengl3dgamedemo.shader.StaticShader;
import com.thinmatrix.opengl3dgamedemo.textures.ModelTexture;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Main {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader staticShader = new StaticShader();
        Renderer renderer = new Renderer(staticShader);

        RawModel model = OBJLoader.loadOBJModel("stall", loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
        TextureModel textureModel = new TextureModel(model, texture);

        Entity entity = new Entity(textureModel, new Vector3f(0, 0, -50), 0, 0, 0, 1);

        Camera camera = new Camera();

        while (!Display.isCloseRequested()) {
//            entity.increasePosition(0, 0, -0.0001f);
//            entity.increaseRotation(0, 0, 360f);
            entity.increaseRotation(0, 0.02f, 0);
            renderer.prepare();
            camera.move();
            staticShader.start();
            staticShader.loadViewMatrix(camera);
            renderer.render(entity, staticShader);
            staticShader.stop();

            DisplayManager.updateDisplay();
        }

        staticShader.cleanUp();
        loader.cleanUp();

        DisplayManager.closeDisplay();
    }
}
