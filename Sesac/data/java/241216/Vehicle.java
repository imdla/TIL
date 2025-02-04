package org.example.inheritanceprac;

public class Vehicle {
    private String model;
    private int speed = 0;
    protected int fuel = 0;
    protected static final int MAX_FUEL = 100;

    public Vehicle(String model) {
        this.model = model;
    }

    public void speedUp() {
        if (fuel > 10) {
            speed += 10;
            fuel -= 10;
            System.out.println("Current speed: " + speed + ", Current fuel: " + fuel);
        } else {
            System.out.println("fuel is so few ! ");
        }
    }

    public void speedDown() {
        speed -= 10;
        System.out.println("Current speed: " + speed);
    }

    // 연료 채우기
    public void addFuel(int fuel) {
        if (validateFuel(fuel)) {
            this.fuel += fuel;
        }

        if (this.fuel > getMaxFuel()) {
            this.fuel = getMaxFuel();
        }

        System.out.println("Current fuel: " + this.fuel);
    }

    // 연료 확인
    public boolean validateFuel(int fuel) {
        if (fuel <= 0) {
            System.out.println("Fuel is under 0 !");
            return false;
        } else {
            return true;
        }
    }

    public int getMaxFuel() {
        return MAX_FUEL;
    }

}
