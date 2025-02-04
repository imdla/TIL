package org.example.interfaceprac.vehicle;



public class Bus extends Vehicle implements PassengerGetOnOff {
    public String busValue = "bus";

    @Override
    void fuelMethod() {
        System.out.println("bus : fuel method");
    }

    @Override
    public void boardMethod() {
        System.out.println("bus : fuel method");
    }

    @Override
    void speedMethod() {
        System.out.println("bus : speed method");
    }

}
