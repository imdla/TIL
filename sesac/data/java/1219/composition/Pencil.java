package org.example.interfaceprac.compositionprac;

public class Pencil implements Tool {
    protected String color;

    public Pencil(String color) {
        this.color = color;
    }

    public void write() {
        System.out.println("Write with " + color + " color");
    }

    @Override
    public void use() {
        System.out.println("use : Pencil");
    }
}
