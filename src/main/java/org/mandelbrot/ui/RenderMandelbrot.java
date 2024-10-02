package org.mandelbrot.ui;
import org.mandelbrot.ComplexNumber;
import org.mandelbrot.Mandelbrot;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderMandelbrot extends JComponent {
    private BufferedImage buffer;
    int WIDTH = 1200;
    int HEIGHT = 1000;

    public final double startXPoint = -2.4;
    public final double startYPoint = 1.6;
    public final double complexPlaneWidth = 3.6;
    public final double complexPlaneHeight = 3;
    public  final double dx = complexPlaneWidth/(WIDTH-1);
    public  final double dy = complexPlaneHeight/(HEIGHT-1);


    public RenderMandelbrot() {
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        render();
        JFrame frame  = new JFrame("Mandelbrot Visualization");
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

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                ComplexNumber point = realToComplex(x, y);
                MandelbrotResult result = new Mandelbrot().calculateMandelbrot(point, maxIterations);

                // set buffer color based on the result
                if (result.isMandelbrot()) {
                    Color black = new Color(255, 255, 255);
                    buffer.setRGB(x, y, black.getRGB());
                } else {
                    Color  backgroundGradiant   =
                            new Color(result.getIterations() * 2, result.getIterations() * 2, result.getIterations() * 2);
                    buffer.setRGB(x, y, backgroundGradiant.getRGB());
                }

            }
        }

    }

    public ComplexNumber realToComplex(int x, int y){

        double real = startXPoint + x*dx;
        double imaginary = startYPoint - y*dy;
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
