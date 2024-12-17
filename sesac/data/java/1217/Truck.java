package org.example.interfaceprac;

public class Truck extends Vehicle implements LoadOnOff {
    private int bagage;
    private int maxBagage;

    public Truck(String modelName) {
        super(modelName);
        maxFuel = 250;
        bagage = 0;
        maxBagage = 30;
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
