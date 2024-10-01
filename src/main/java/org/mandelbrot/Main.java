package org.mandelbrot;


import org.mandelbrot.ui.RenderMandelbrot;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RenderMandelbrot renderMandelbrot = new RenderMandelbrot();
        renderMandelbrot.render();
    }
}

