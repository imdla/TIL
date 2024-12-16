package org.example.inheritanceprac;

public class Truck extends Vehicle{
    private int bagage = 0;
    protected static final int MAX_FUEL = 250;

    public Truck(String model) {
        super(model);
    }

    // 최대 보관 짐 수 : 30
    // 짐 추가하기
    public void addBagage(int bagage) {
        if (validateBagage(bagage)) {
            this.bagage += bagage;
        }

        if (this.bagage <= 30) {
            System.out.println("Current bagage: " + this.bagage);
        } else {
            System.out.println("We max bagage are 30 !");
            this.bagage = 30;
            System.out.println("Current bagage: " + this.bagage);
        }
    }

    // 짐 빼기
    public void minusBagage(int bagage) {
        boolean flag = true;
        if (!validateBagage(bagage)) {
            flag = false;
        }

        if (this.bagage < bagage) {
            System.out.println("get off bagage so many !");
            flag = false;
        }

        if (flag){
            this.bagage -= bagage;
            System.out.println("Current bagage: " + this.bagage);
        }
    }

    // 연료 채우기
//    @Override
//    public void addFuel(int fuel) {
//        if (validateFuel(fuel)) {
//            this.fuel += fuel;
//        }
//
//        if (this.fuel > MAX_FUEL) {
//            this.fuel = MAX_FUEL;
//        }
//
//        System.out.println("Current fuel: " + this.fuel);
//    }

    // 짐 수 확인
    public boolean validateBagage(int bagage) {
        if (bagage <= 0) {
            System.out.println("bagage is under 0 !");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getMaxFuel() {
        return MAX_FUEL;
    }
}
