package org.example;

public class Rectangle {
    // 속성(필드)
    public int width;
    public int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // 기능(메서드)
    public int calculateArea() {
        return this.width * this.height;
    }
}
