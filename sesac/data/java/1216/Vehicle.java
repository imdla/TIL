package org.example.interfaceprac;

public abstract class Vehicle {
    protected String modelName;
    protected int speed;
    protected int fuel;
    protected static int maxFuel;

    public Vehicle(String modelName) {
        this.modelName = modelName;
        this.speed = 0;
        this.fuel = 100;
        maxFuel = 100;
    }

    // 속도 증가
    public void speedUp() {
        if (validateFuel()) {
            speed += 10;
            fuel -= 10;
        }
        System.out.println("Current speed: " + speed);
        System.out.println("Current fuel: " + fuel);
    }

    // 속도 하락
    public void speedDown() {
        if (validateSpeed()) {
            speed -= 10;
        }
        System.out.println("Current speed: " + speed);
    }

    // 속도 확인
    private boolean validateSpeed() {
        if (speed < 0) {
            return false;
        } else {
            return true;
        }
    }

    // 연료 확인
    private boolean validateFuel() {
        if (fuel < 0) {
            return false;
        } else {
            return true;
        }
    }
}
