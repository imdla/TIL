package org.example;

public class Circle {
    // 변수
    int radius;

    // 생성자
    Circle(int radius) {
        this.radius = radius;
    }

    // 메서드
    double calculateArea() {
        return 3.14 * this.radius * this.radius;
    }
}
