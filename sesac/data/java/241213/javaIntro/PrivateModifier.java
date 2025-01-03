package org.example.javaIntro;

public class PrivateModifier {
    public int publicNum = 1;
    int defaultNum = 100;

    // getter
    public int getPrivateNum() {
        return privateNum;
    }

    // setter
    public void setPrivateNum(int privateNum) {
        this.privateNum = privateNum;
    }

    private int privateNum = 10;

    public void publicHi() {
        System.out.println("hi, public");
    }

    private void privateHi() {
        System.out.println("hi, private");
    }

    public void sayHi() {
        privateHi();
    }
}
