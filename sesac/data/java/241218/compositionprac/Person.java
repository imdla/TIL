package org.example.interfaceprac.compositionprac;

public class Person {
    protected String name;
    private Pencil pencil;

    public Person(String name, Pencil pencil) {
        this.name = name;
        this.pencil = pencil;
    }

    public void write() {
        pencil.write();
    }

    public void setPencil(Pencil pencil) {
        this.pencil = pencil;
    }
}
