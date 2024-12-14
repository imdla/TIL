package org.example.modifierprac;

public class Person {
    private String name;
    public int age;
    double height;

    Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    private void run() {
        System.out.println("run");
    }

    public void runShow() {
        run();
    }

    public void walk() {
        System.out.println("walk");
    }

    void study() {
        System.out.println("study");
    }
}
