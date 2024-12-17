package org.example.inheritanceprac;

public class Student extends Person {
    int studentNum;

    // 메서드 오버라이딩
    public static void introduce() {
        System.out.println("Hello I'm student");
    }

    public void study() {
        System.out.println("Studying");
    }
}
