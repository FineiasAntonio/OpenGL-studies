package com.fineias;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.util.random.RandomGenerator;

public class Main {



    public static void main(String[] args) {
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glCapabilities.setDoubleBuffered(true);
        glCapabilities.setHardwareAccelerated(true);


        GLJPanel panel = new GLJPanel(glCapabilities);
        panel.addGLEventListener(new GLEventListener() {

            private float angle = 0f;

            @Override
            public void init(GLAutoDrawable glAutoDrawable) {

            }

            @Override
            public void dispose(GLAutoDrawable glAutoDrawable) {

            }

            @Override
            public void display(GLAutoDrawable glAutoDrawable) {
                GL2 gl2 = glAutoDrawable.getGL().getGL2();
                gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
                gl2.glLoadIdentity();

                GLU glu = GLU.createGLU(gl2);
                glu.gluLookAt(
                        0.0, 0.0, 5.0,
                        0.0, 0.0, 0.0,
                        0.0, 1.0, 0.0
                );

                gl2.glRotatef(angle, 1.0f, 1.0f, 0.0f);

                // Pyramid faces
                gl2.glBegin(GL2.GL_TRIANGLES);

                /*
                 * Front triangle
                 */
                gl2.glColor3f(1.0f, 0.0f, 0.0f);
                gl2.glVertex3f(0.0f, 1.0f, 0.0f);
                gl2.glColor3f(0.0f, 1.0f, 0.0f);
                gl2.glVertex3f(-1.0f, -1.0f, 1.0f);
                gl2.glColor3f(0.0f, 0.0f, 1.0f);
                gl2.glVertex3f(1.0f, -1.0f, 1.0f);
                /*
                 * Right triangle
                 */
                gl2.glColor3f(1.0f, 0.0f, 0.0f);
                gl2.glVertex3f(0.0f, 1.0f, 0.0f);
                gl2.glColor3f(0.0f, 0.0f, 1.0f);
                gl2.glVertex3f(1.0f, -1.0f, 1.0f);
                gl2.glColor3f(0.0f, 1.0f, 0.0f);
                gl2.glVertex3f(1.0f, -1.0f, -1.0f);
                /*
                 * Back triangle
                 */
                gl2.glColor3f(1.0f, 0.0f, 0.0f);
                gl2.glVertex3f(0.0f, 1.0f, 0.0f);
                gl2.glColor3f(0.0f, 1.0f, 0.0f);
                gl2.glVertex3f(1.0f, -1.0f, -1.0f);
                gl2.glColor3f(0.0f, 0.0f, 1.0f);
                gl2.glVertex3f(-1.0f, -1.0f, -1.0f);
                /*
                 * Left triangle
                 */
                gl2.glColor3f(1.0f, 0.0f, 0.0f);
                gl2.glVertex3f(0.0f, 1.0f, 0.0f);
                gl2.glColor3f(0.0f, 0.0f, 1.0f);
                gl2.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl2.glColor3f(0.0f, 1.0f, 0.0f);
                gl2.glVertex3f(-1.0f, -1.0f, 1.0f);

                gl2.glEnd();
                gl2.glFlush();

                // Pyramid base
                gl2.glBegin(GL2.GL_QUADS);

                gl2.glVertex3f(-1.0f, -1.0f,1.0f);
                gl2.glVertex3f(-1.0f, -1.0f,-1.0f);
                gl2.glVertex3f(1.0f, -1.0f,-1.0f);
                gl2.glVertex3f(1.0f, -1.0f,1.0f);

                gl2.glEnd();

                gl2.glFlush();

                angle += 0.3f;

            }



            @Override
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
                GL2 gl2 = glAutoDrawable.getGL().getGL2();
                gl2.glViewport(0, 0, i2, i3);
                gl2.glMatrixMode(GL2.GL_PROJECTION);
                gl2.glLoadIdentity();
                GLU glu = GLU.createGLU(gl2);
                glu.gluPerspective(45.0, (double) i2 / i3, 1.0, 100.0);
                gl2.glMatrixMode(GL2.GL_MODELVIEW);
                gl2.glLoadIdentity();
            }
        });
        panel.setSize(400,400);

        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(panel, 400,true );
        animator.start();
    }


}