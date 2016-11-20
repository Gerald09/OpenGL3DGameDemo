package com.thinmatrix.opengl3dgamedemo;

import com.thinmatrix.opengl3dgamedemo.renderengine.DisplayManager;

import org.lwjgl.opengl.Display;

public class Main {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        while (!Display.isCloseRequested()) {
            // game logic
            // render
            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
