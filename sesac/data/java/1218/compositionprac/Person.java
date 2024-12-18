package org.example.interfaceprac.compositionprac;

public class Person {
    private Pencil pencil;

    public Person(Pencil pencil) {
        this.pencil = pencil;
    }

    public void writePen() {
        this.pencil.write();
    }
}
