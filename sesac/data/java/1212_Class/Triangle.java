package org.example;

public class Triangle {
    public int sideLength;
    public int circumference;

    public Triangle(int sideLength) {
        this.sideLength = sideLength;
        this.circumference = sideLength * 3;
    }

    public double calculateArea() {
        double area = (Math.sqrt(3) / 4) * this.sideLength * this.sideLength;
        area = Math.floor(area*100)/100;
        return area;
    }
}
