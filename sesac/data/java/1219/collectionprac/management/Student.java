package org.example.collectionprac.management;

import java.util.HashMap;
import java.util.Map;

public class Student {
    protected String name;
    protected int age;
    protected Grade grade;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Map<String, Integer> allGrades = new HashMap<>();

    // 성적 입력
    public void inputGrade(Grade grade) {
        allGrades.put(grade.subject, grade.score);
    }

    // 평균 성적 계산
    public int calculateGradeAvg() {
        int gradeSum = 0;
        for (Integer value : allGrades.values()) {
            gradeSum += value;
        }
        int gradeAvg = gradeSum / allGrades.size();

        return gradeAvg;
    }
}
