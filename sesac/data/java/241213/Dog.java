package org.example;

public class Dog {
    static int count;
    public String breed;
    public String name;

    public Dog(String breed, String name) {
        this.breed = breed;
        this.name = name;
        count++;
    }

    public String seatDown() {
        return "seat down";
    }

    public String giveHand() {
        return "give hand";
    }
}
