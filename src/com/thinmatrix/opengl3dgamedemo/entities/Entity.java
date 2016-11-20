package com.thinmatrix.opengl3dgamedemo.entities;

import com.thinmatrix.opengl3dgamedemo.models.TextureModel;

import org.lwjgl.util.vector.Vector3f;

public class Entity {
    private TextureModel textureModel;
    private Vector3f position;
    private float rx;
    private float ry;
    private float rz;
    private float scale;

    public Entity(TextureModel textureModel, Vector3f position, float rx, float ry, float rz, float scale) {
        this.textureModel = textureModel;
        this.position = position;
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.scale = scale;
    }

    public void increasePosition(float dx, float dy, float dz) {
        position.x += dx;
        position.y += dy;
        position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz) {
        rx += dx;
        ry += dy;
        rz += dz;
    }

    public TextureModel getTextureModel() {
        return textureModel;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getRx() {
        return rx;
    }

    public float getRy() {
        return ry;
    }

    public float getRz() {
        return rz;
    }

    public float getScale() {
        return scale;
    }
}
