package org.example.interfaceprac;

// Shape를 추상 클래스로 사용할 것
public abstract class Shape {
    public int width;
    public int height;

    public abstract int calcaulateArea();

    public void test() {
        System.out.println("test");
    }
}
