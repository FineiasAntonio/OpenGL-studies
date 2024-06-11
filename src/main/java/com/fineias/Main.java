package com.fineias;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;

public class Main {

    private GLCanvas glCanvas;

    public static void main(String[] args) {
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glCapabilities.setDoubleBuffered(true);
        glCapabilities.setHardwareAccelerated(true);


        GLJPanel panel = new GLJPanel(glCapabilities);
        panel.addGLEventListener(new GLEventListener() {
            @Override
            public void init(GLAutoDrawable glAutoDrawable) {
                new Thread(() -> {while (true) panel.display();}).start();
            }

            @Override
            public void dispose(GLAutoDrawable glAutoDrawable) {

            }

            @Override
            public void display(GLAutoDrawable glAutoDrawable) {
                GL2 gl2 = glAutoDrawable.getGL().getGL2();
                gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

                gl2.glBegin(GL2.GL_TRIANGLES);
                gl2.glColor3f(1.0f, 0.0f, 0.0f); // Vermelho
                gl2.glVertex3f(1.0f, 0.0f, 0.0f);
                gl2.glColor3f(0.0f, 1.0f, 0.0f); // Verde
                gl2.glVertex3f(0.0f, 1.0f, 0.0f);
                gl2.glColor3f(0.0f, 0.0f, 1.0f); // Azul
                gl2.glVertex3f(-1.0f, 0.0f, 0.0f);
                gl2.glEnd();
            }

            @Override
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

            }
        });
        panel.setSize(400,400);

        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        
        Animator animator = new Animator(panel);
        animator.start();
    }


}