package org.mandelbrot;

public class ComplexNumber {
    private double realNumber;
    private double imaginaryNumber;

    public ComplexNumber(double realNumber, double imaginaryNumber) {
        this.realNumber = realNumber;
        this.imaginaryNumber = imaginaryNumber;
    }


    public ComplexNumber multiply(ComplexNumber complexNumber) {
        /*
        z1 =(a1+ib1)
        z2 = (a2+ib2)
        z1*z2 = (a1a2 - b1b2) + i(a1b2 + a2b1)

        --------------------------
         z1 = a + ib
         z2 = c + id
          z1z2 = (ac – db) + i(ad + bc).
         */
//        double multiplicationResult =
//                (this.realNumber * this.realNumber) - (this.imaginaryNumber * this.imaginaryNumber)
//                +
//                (this.realNumber * this.imaginaryNumber) + (this.imaginaryNumber * this.realNumber);
//        ComplexNumber finalResult =  new ComplexNumber(multiplicationResult, 0);
//
//        System.out.println("result of multiplying " + complexNumber +  " * " + this + " = " +   finalResult);
//        return finalResult;
        double realPart = (this.realNumber * complexNumber.realNumber) - (this.imaginaryNumber * complexNumber.imaginaryNumber);
        double imaginaryPart = (this.realNumber * complexNumber.imaginaryNumber) + (this.imaginaryNumber * complexNumber.realNumber);
        return new ComplexNumber(realPart, imaginaryPart);
    }

    public ComplexNumber add(ComplexNumber complexNumber) {
        double newRealNumber = this.realNumber + complexNumber.realNumber;
        double newImaginaryNumber = this.imaginaryNumber + complexNumber.imaginaryNumber;
        return new ComplexNumber(newRealNumber, newImaginaryNumber);
    }

    public ComplexNumber addReal(double realNumber) {
        return new ComplexNumber(this.realNumber + realNumber, this.imaginaryNumber);
    }

    public ComplexNumber square() {
        return this.multiply(this);
    }

    public double magnitude() {
        return Math.sqrt(this.realNumber * this.realNumber + this.imaginaryNumber * this.imaginaryNumber);
    }

    public ComplexNumber convertRealNumberToComplex(double realNumber) {
        return new ComplexNumber(realNumber, 0);
    }

    public double abs(){
        return Math.hypot(this.realNumber, this.imaginaryNumber);
    }

    @Override
    public String toString() {
        return realNumber + " + " + imaginaryNumber + "i";
    }

}
