package org.example.interfaceprac.compositionprac;

public class Car {
    protected Engine engine;
    protected String modelName;
    protected int speed;

    public Car(String modelName, Engine engine) {
        this.modelName = modelName;
        this.engine = engine;
        this.speed = 0;
    }

    public void accel() {
        speed += engine.horsepower * 10;
        System.out.println("engine: " + engine.horsepower + " speed: " + speed);
    }
}
