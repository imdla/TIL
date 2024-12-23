package org.example.exceptionprac;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(0/0);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            System.out.println("error !");
        }

        try {
            // 문제 상황이 발생했다고 가정
            // if (스페셜어택이 불가능하면)
            if (true) {
                throw new RuntimeException("error message");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("runtime error !");
        }
    }
}
