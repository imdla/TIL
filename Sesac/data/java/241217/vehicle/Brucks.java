package org.example.interfaceprac.vehicle;

public class Brucks extends Vehicle implements PassengerGetOnOff, BagageGetOnOff {

    @Override
    public void bagageMethod() {
        System.out.println("brucks : bagage method");
    }

    @Override
    public void boardMethod() {
        System.out.println("brucks : board method");
    }

    @Override
    void fuelMethod() {
        System.out.println("brucks : fuel method");
    }
}
