package org.example.inheritanceprac;

public class Parent {
    public String publicValue = "public Value";
    private String privateValue = "private Value";
    protected String protectedValue = "protected Value";

    public Parent(String publicValue) {
        this.publicValue = publicValue;
    }

    public void publicMethod() {
        System.out.println("public Method");
    }
}
