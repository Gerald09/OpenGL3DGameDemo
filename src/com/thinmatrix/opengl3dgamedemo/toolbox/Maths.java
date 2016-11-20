package com.thinmatrix.opengl3dgamedemo.toolbox;

import com.thinmatrix.opengl3dgamedemo.entities.Camera;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Maths {
    public static Matrix4f createTransformationMatrix(Vector3f translation,
                                                      float rx,
                                                      float ry,
                                                      float rz,
                                                      float scale) {
        Matrix4f matrix = new Matrix4f();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate(rx, new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate(ry, new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate(rz, new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), matrix, matrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPosition = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f.translate(negativeCameraPosition, matrix, matrix);
        return matrix;
    }
}
