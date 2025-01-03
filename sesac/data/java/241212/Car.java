package org.example;

public class Car {
    public String modelName;
    public int speed;

    public Car(String modelName) {
        this.modelName = modelName;
        this.speed = 0;
    }

    public int accelSpeed() {
        this.speed += 10;
        return this.speed;
    }

    public int breakSpeed() {
        if (this.speed != 0) {
            this.speed -= 10;
        }
        return this.speed;
    }

    public void showInfo() {
        System.out.println("Model Name: " + this.modelName);
        System.out.println("speed: " + this.speed);
        System.out.printf("model: %s speed: %d \n", modelName, speed);
    }

    // 속도를 입력받는 메서드
    public int increaseSpeedByAmount(int amount) {
        this.speed += amount;
        return this.speed;
    }
}
