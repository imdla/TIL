package org.example.interfaceprac.vehicle;

public abstract class Vehicle {
    protected int maxFuel = 100;

    // 연료 관련
    abstract void fuelMethod();

    // 속도 관련
    void speedMethod() {
        System.out.println("vehicle : speed method");
    }
}
