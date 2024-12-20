package org.example.collectionprac.management;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    public List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    // 학생 추가
    public List<Student> addStudent(Student student) {
        students.add(student);
        return students;
    }

    // 학생 검색
    public Student findStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return  null;
    }

    // 학생 정보 조회
    public void showStudentsInfo() {
        for (Student student : students) {
            student.showInfo();
            System.out.println();
        }
    }
}
