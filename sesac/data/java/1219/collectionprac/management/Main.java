package org.example.collectionprac.management;

public class Main {
    public static void main(String[] args) {
       Student s1 = new Student("s1", 17);
       s1.addGrade("korean", 10);
       s1.addGrade("cs", 100);
       s1.addGrade("math", 20);

       s1.showInfo();
       System.out.println(s1.averageScore());

        System.out.println();
        Student s2 = new Student("s2", 17);
        s2.addGrade("korean", 50);
        s2.addGrade("cs", 50);
        s2.addGrade("math", 10);

        s2.showInfo();
        System.out.println(s2.averageScore());
    }
}
