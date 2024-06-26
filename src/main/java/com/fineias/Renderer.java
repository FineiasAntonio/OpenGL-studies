package com.fineias;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Renderer implements GLEventListener {

    private Set<Polygon> polygons = new HashSet<>();

    private float angle = 0f;

    public void addPolygon(Polygon polygon){
        polygons.add(polygon);
    }

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

        gl2.glRotatef(angle, 0.0f, 1.0f, 0.0f);

        polygons.stream().forEach(polygon -> {
            float[] vertices = polygon.getVertices();
            gl2.glBegin(GL2.GL_TRIANGLES);
            System.out.println(Arrays.toString(vertices));
            gl2.glVertex3f(vertices[0], vertices[1], vertices[2]);
            gl2.glEnd();
        });

        gl2.glFlush();

        angle += 0.2f;

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

}
