package org.example.collectionprac.management;

public class Main {
    public static void main(String[] args) {
        Grade english = new Grade("english", 50);
        Grade korean = new Grade("korean", 50);
        Grade math = new Grade("math", 50);
        System.out.println(english.subject);
        System.out.println(english.score);

        Student s1 = new Student("s1", 20);
        s1.inputGrade(english);
        s1.inputGrade(korean);
        s1.inputGrade(math);
        System.out.println(s1.allGrades);

        System.out.println("avg" + s1.calculateGradeAvg());

        ///

        StudentManager m1 = new StudentManager();
        m1.addStudent(s1);
        m1.searchStudent(s1);

    }
}
