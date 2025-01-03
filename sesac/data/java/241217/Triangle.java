package org.example.interfaceprac;

public class Triangle extends Shape {
    public int width = 10;
    public int height = 20;

    @Override
    public int calcaulateArea() {
        return width * height/2;
    }
}
