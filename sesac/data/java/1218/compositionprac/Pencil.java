package org.example.interfaceprac.compositionprac;

public class Pencil {
    protected String color;

    public Pencil(String color) {
        this.color = color;
    }

    public void write() {
        System.out.println("Write with " + color + " color");
    }
}
