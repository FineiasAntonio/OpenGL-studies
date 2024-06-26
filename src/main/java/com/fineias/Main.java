package com.fineias;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class Main {

    private static final String FILE_STRING = "espada.obj";

    private static Renderer renderer = new Renderer();

    public static void main(String[] args) throws IOException {
        loadObjFile();
        System.out.println("Processed object, rendering now");
        render();
    }

    public static void loadObjFile() throws IOException {

        File obj = new File(FILE_STRING);
        Polygon polygon;

        BufferedReader in = new BufferedReader(new FileReader(obj));

        String line;
        while ((line = in.readLine()) != null) {

            if(line.startsWith("v ")){
                String[] splitedLine = line.split(" ");

                float[] vertices = new float[3];
                vertices[0] = Float.parseFloat(splitedLine[1]);
                vertices[1] = Float.parseFloat(splitedLine[2]);
                vertices[2] = Float.parseFloat(splitedLine[3]);

                renderer.addPolygon(new Polygon(vertices));

            }

        }

    }

    public static void render(){
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glCapabilities.setDoubleBuffered(true);
        glCapabilities.setHardwareAccelerated(true);


        GLJPanel panel = new GLJPanel(glCapabilities);
        panel.addGLEventListener(renderer);
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