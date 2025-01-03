package org.example.interfaceprac.vehicle;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus();

        bus.boardMethod();
        bus.fuelMethod();
        bus.speedMethod();
        System.out.println(bus.busValue);

        Vehicle bus2 = new Bus();
        Bus bus3 = (Bus) bus2;
        System.out.println(bus3.busValue);

        bus2.fuelMethod();
        bus2.speedMethod();
    }
}
