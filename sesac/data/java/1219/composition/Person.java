package org.example.interfaceprac.compositionprac;

public class Person {
    protected String name;
    private Pencil pencil;
    public Pencil[] pencils;
    public Tool tool;

    public Person(String name, Tool tool) {
        this.name = name;
        this.tool = tool;
    }

//    public Person(String name, Pencil pencil) {
//        this.name = name;
//        this.pencil = pencil;
//    }

    public Person(String name, Pencil[] pencils) {
        this.name = name;
        this.pencils = pencils;
    }

    public void use() {
        tool.use();
    }

    public void writeMany() {
        for (Pencil p : pencils) {
            p.write();
        }
    }

    public void write() {
        pencil.write();
    }

    public void setPencil(Pencil pencil) {
        this.pencil = pencil;
    }
}
