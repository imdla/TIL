package org.example.interfaceprac;

public class Bus extends Vehicle implements GetOnOff {
    private int passenger;
    private int maxPassenger;

    public Bus(String modelName) {
        super(modelName);
        maxFuel = 300;
        passenger = 0;
        maxPassenger = 20;
    }

    // 승객 승차
    @Override
    public void getOn(int num) {
        boolean flag = true;
        if (!validateNum(num)) {
            flag = false;
            System.out.println("Passengers are under 0 !");
        } else {
            passenger += num;
        }
        if (flag && !validateGetOnOff(passenger)) {
            passenger = maxPassenger;
            System.out.println("Max passenger !");
        }
        System.out.println("Current passenger: " + passenger);
    }

    // 승객 하차
    @Override
    public void getOff(int num) {
        boolean flag = true;
        if (!validateNum(num)) {
            flag = false;
            System.out.println("Passengers are under 0 !");
        } else if (num > passenger) {
            flag = false;
            System.out.println("Get off passengers so many !");
        } else {
            passenger -= num;
        }

        if (flag && !validateGetOnOff(passenger)) {
            passenger = 0;
            System.out.println("Passengers are under 0 !");
        }
        System.out.println("Current passenger: " + passenger);
    }

    // 승객 확인
    @Override
    public boolean validateGetOnOff(int num) {
        if (num < 0 || num > maxPassenger) {
            return false;
        } else {
            return true;
        }
    }

    // 입력값 확인
    @Override
    public boolean validateNum(int num) {
        if (num < 0) {
            return false;
        } else {
            return true;
        }
    }

}
