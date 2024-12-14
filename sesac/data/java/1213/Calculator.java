package org.example;

public class Calculator {
    // 인스턴스 메서드
    int add(int num1, int num2) {
        return num1 + num2;
    }

    // 클래스 메서드
    static int staticAdd(int num1, int num2) {
        return num1 + num2;
    }

    int minus(int num1, int num2) {
        return num1 - num2;
    }

    int multiply(int num1, int num2) {
        return num1 * num2;
    }

    int divide(int num1, int num2) {
        return num1 / num2;
    }
}
