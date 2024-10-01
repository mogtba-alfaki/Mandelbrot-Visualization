package org.mandelbrot.ui;

public class MandelbrotResult {
    private boolean isMandelbrot;
    private int iterations;

    public MandelbrotResult(boolean isMandelbrot, int iterations) {
        this.isMandelbrot = isMandelbrot;
        this.iterations = iterations;
    }

    public boolean isMandelbrot() {
        return isMandelbrot;
    }

    public int getIterations() {
        return iterations;
    }

    public void setMandelbrot(boolean mandelbrot) {
        isMandelbrot = mandelbrot;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
