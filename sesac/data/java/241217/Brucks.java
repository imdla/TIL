package org.example.interfaceprac;

public class Brucks extends Vehicle implements GetOnOff, LoadOnOff {
    private int passenger;
    private int bagage;
    private int maxPassenger;
    private int maxBagage;

    public Brucks(String modelName) {
        super(modelName);
        maxFuel = 1000;
        passenger = 0;
        bagage = 0;
        maxPassenger = 20;
        maxBagage = 30;
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

    // 짐 올리기
    @Override
    public void loadOn(int num) {
        boolean flag = true;
        if (!validateValue(num)) {
            flag = false;
            System.out.println("Bagages are under 0 !");
        } else {
            bagage += num;
        }
        if (flag && !validateLoadOnOff(bagage)) {
            bagage = maxBagage;
            System.out.println("Max bagage !");
        }
        System.out.println("Current bagage: " + bagage);
    }

    // 짐 내리기
    @Override
    public void loadOff(int num) {
        boolean flag = true;
        if (!validateValue(num)) {
            flag = false;
            System.out.println("Bagages are under 0 !");
        } else if (num > bagage) {
            flag = false;
            System.out.println("Get off bagages so many !");
        } else {
            bagage -= num;
        }

        if (flag && !validateLoadOnOff(bagage)) {
            bagage = 0;
            System.out.println("Bagages are under 0 !");
        }
        System.out.println("Current bagage: " + bagage);
    }

    // 짐 확인
    @Override
    public boolean validateLoadOnOff(int num) {
        if (num < 0 || num > maxBagage) {
            return false;
        } else {
            return true;
        }
    }

    // 입력값 확인
    @Override
    public boolean validateValue(int num) {
        if (num < 0) {
            return false;
        } else {
            return true;
        }
    }
}
