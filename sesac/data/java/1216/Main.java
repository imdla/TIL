package org.example.interfaceprac;

public class Main {
    public static void main(String[] args) {
        // Shape
//        Shape rectangle = new Rectangle();
//        Rectangle rectangle2 = new Rectangle();
//        System.out.println(rectangle.calcaulateArea());
//
//        Triangle triangle = new Triangle();
//        System.out.println(triangle.calcaulateArea());

        // Animal
        Dog dog = new Dog("happy", 18, "puddle");
        dog.makeSound();

        Cat cat = new Cat("cat", 3);
        cat.makeSound();

        makeThemSound(dog);
        makeThemSound(cat);

        // Vehicle
        System.out.println("Vehicle ---------------------------");
        Bus bus = new Bus("bus");
        System.out.println(bus.fuel);
        bus.speedUp();
        bus.speedUp();
        bus.speedUp();
        bus.speedDown();

        bus.getOn(10);
        bus.getOff(5);

        Truck truck = new Truck("truck");
        System.out.println(truck.fuel);
        truck.speedUp();
        truck.speedUp();
        truck.speedUp();
        truck.speedDown();

        truck.loadOn(10);
        truck.loadOff(5);
    }

    public static void makeThemSound(Animal animal) {
        System.out.println("cry!");
        animal.makeSound();
    }

//    public static void makeThemSound(Dog dog){
//        System.out.println("cry!");
//        dog.makeSound();
//    }
//    public static void makeThemSound(Cat cat){
//        System.out.println("cry!");
//        cat.makeSound();
//    }
}
