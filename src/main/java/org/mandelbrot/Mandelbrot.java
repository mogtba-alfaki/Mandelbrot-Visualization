package org.mandelbrot;

import org.mandelbrot.ui.MandelbrotResult;

public class Mandelbrot {
    /*
        the main question of this function is :
        when applying the mandelbrot formula to a value are numbers tend to go towards infinity ?
        or  they stay around and bounce in a narrow spaces on the plane ? based on the answer we can
        determine if the number is in the mandelbrot set or not.
        Z(n) = Z(n)^2 + C
    */
    public MandelbrotResult calculateMandelbrot(ComplexNumber c, int maxIterations)  {
        ComplexNumber z = new ComplexNumber(0, 0);
        for (int i = 0; i < maxIterations; i++) {
            z = z.square().add(c);

            if (z.magnitude() > 2) {
                return new MandelbrotResult(false, i);
            }

            }
        return new MandelbrotResult(true, maxIterations);
    }
}
