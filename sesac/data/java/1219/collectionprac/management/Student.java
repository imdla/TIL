package org.example.collectionprac.management;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private int age;
    private Map<String, Integer> grade;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.grade = new HashMap<>();
    }

    // 성적 저장
    public Map<String, Integer> addGrade(String subject, Integer score) {
        grade.put(subject, score);
        return grade;
    }

    // 성적 조회
    public void showInfo() {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("grade = " + grade);
    }

    // 성적 평균 계산
    public double averageScore() {
        Integer sum = 0;
        for (Integer value : grade.values()) {
            sum += value;
        }

        return (double) sum / grade.size();
    }
}
