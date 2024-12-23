package org.example.exceptionprac;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        // checked exception
        // 1. try catch
//        try {
//            Files.readString(Paths.get(""));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        // 2. throws IOException와 함께
//        Files.readString(Paths.get(""));

        try {
            func();
        } catch (RuntimeException e) {
            System.out.println("error in func");
        }

    }

    public static void func() {
        System.out.println("func runtime");

        throw new RuntimeException("func error !");
    }

//    public int speedUp() {
        // 연료 소모, 속도 높임
        // 만약 연료가 존재하지 않을 때 처리
        // 1. if문 -> speed 반환
//        if (fuel < 0) {
//            // 문제 상황 알려줘
//            return speed;
//        }
        // 2. if문 -> throw
//        if (fuel < 0) {
//            throw new RuntimeException("No fuel");
//            // speed up을 실행시키는 상위 메서드에서
//            // try-catch를 통해 해결
//        }
//        return speed;
//    }

//    public void 구매() {
//        if (내가 돈이 없어) {
//            System.out.println(너 못사);
//        }
//        if (내가 돈이 없어) {
//            throw new RuntimeException();
//        }
//    }


}
