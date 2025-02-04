package org.example.inheritanceprac;

public class Bus extends Vehicle {
    private int passenger = 0;
    protected static final int MAX_FUEL = 300;

    public Bus(String model) {
        super(model);
    }

    // 최대 탑승 인원 수 : 20
    // 승객 탑승
    public void boardingPassenger(int passenger) {
        if (validatePassenger(passenger)) {
            this.passenger += passenger;
        }

        if (this.passenger <= 20) {
            System.out.println("Current passenger: " + this.passenger);
        } else {
            System.out.println("We max boarding is 20 !");
            this.passenger = 20;
            System.out.println("Current passenger: " + this.passenger);
        }
    }

    // 승객 하차
    public void quitPassenger(int passenger) {
        boolean flag = true;
        if (!validatePassenger(passenger)) {
            flag = false;
        }

        if (this.passenger < passenger) {
            System.out.println("get off passenger so many !");
            flag = false;
        }

        if (flag){
            this.passenger -= passenger;
            System.out.println("Current passenger: " + this.passenger);
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

    // 고객 수 확인
    public boolean validatePassenger(int passenger) {
        if (passenger <= 0) {
            System.out.println("passenger is under 0 !");
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
