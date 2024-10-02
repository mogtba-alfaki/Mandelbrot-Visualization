package org.mandelbrot.ui;

import org.mandelbrot.ComplexNumber;
import org.mandelbrot.Mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RenderMandelbrot extends JComponent {
    private final BufferedImage buffer;
    int WIDTH = 1200;
    int HEIGHT = 1000;

    public final double startXPoint = -2.4;
    public final double startYPoint = 1.6;
    public final double complexPlaneWidth = 3.6;
    public final double complexPlaneHeight = 3;
    public final double dx = complexPlaneWidth / (WIDTH - 1);
    public final double dy = complexPlaneHeight / (HEIGHT - 1);

    public RenderMandelbrot() {
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        JFrame frame = new JFrame("Mandelbrot Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().add(this);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
        frame.setVisible(true);
    }

    public void render() {
        int maxIterations = 100;
        int numThreads = 100;
        int segmentHeight = HEIGHT / numThreads;

        // the number of thread will be the number of tasks pushed to the executor task list
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            int startY = i * segmentHeight;
            int endY = (i + 1) * segmentHeight;

            tasks.add(() -> {
                for (int x = 0; x < WIDTH; x++) {
                    for (int y = startY; y < endY; y++) {
                        ComplexNumber point = realToComplex(x, y);
                        MandelbrotResult result = new Mandelbrot().calculateMandelbrot(point, maxIterations);
                        synchronized (buffer) {
                            // set buffer color based on the result
                            if (result.isMandelbrot()) {
                                Color black = new Color(255, 255, 255);
                                buffer.setRGB(x, y, black.getRGB());
                            } else {
                                int colorValue = result.getIterations() * 2;
                                Color backgroundGradient = new Color(colorValue, colorValue, colorValue);
                                buffer.setRGB(x, y, backgroundGradient.getRGB());
                            }
                        }
                    }
                }
                 return null;
            });
        }

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public ComplexNumber realToComplex(int x, int y) {
        double real = startXPoint + x * dx;
        double imaginary = startYPoint - y * dy;
        return new ComplexNumber(real, imaginary);
    }

    @Override
    public void addNotify() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
    }

}
