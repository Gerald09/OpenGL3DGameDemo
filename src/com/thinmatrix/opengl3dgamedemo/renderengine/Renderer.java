package com.thinmatrix.opengl3dgamedemo.renderengine;

import com.thinmatrix.opengl3dgamedemo.entities.Entity;
import com.thinmatrix.opengl3dgamedemo.models.RawModel;
import com.thinmatrix.opengl3dgamedemo.models.TextureModel;
import com.thinmatrix.opengl3dgamedemo.shader.StaticShader;
import com.thinmatrix.opengl3dgamedemo.toolbox.Maths;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

public class Renderer {
    public void prepare() {
        GL11.glClearColor(1, 0, 0, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void render(Entity entity, StaticShader shader) {
        TextureModel textureModel = entity.getTextureModel();
        RawModel model = textureModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoId());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);

        Matrix4f matrix = Maths.createTransformationMatrix(entity.getPosition(),
                entity.getRx(),
                entity.getRy(),
                entity.getRz(),
                entity.getScale());
        shader.loadTransformationMatrix(matrix);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureModel.getTexture().getId());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

}
