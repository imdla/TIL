package org.example.interfaceprac.vehicle;

public class Truck extends Vehicle implements BagageGetOnOff {

    @Override
    public void bagageMethod() {
        System.out.println("truck : bagage method");
    }

    @Override
    void fuelMethod() {
        System.out.println("truck : fuel method");
    }

    @Override
    void speedMethod() {
        System.out.println("truck : speed method");
    }
}
