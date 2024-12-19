package org.example.collectionprac.management;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    public Student student;

    List<Student> allStudents = new ArrayList<>();

    public void addStudent(Student student) {
        allStudents.add(student);
    }

    public void searchStudent(Student student) {
        if (allStudents.contains(student)) {
            System.out.println(student.name);
        } else {
            System.out.println("There is no student !");
        }
    }

    public void showInfoStudent(Student student) {
        if (allStudents.contains(student)) {
            System.out.println("Name: " + student.name);
            System.out.println("Age: " + student.age);
            System.out.println("Avg: " + student.calculateGradeAvg());
        } else {
            System.out.println("There is no student !");
        }
    }
}
