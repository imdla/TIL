package org.example;

import org.example.javaIntro.Dog;
import org.example.practice.Bread;
import org.example.practice.Coffee;

import static org.example.practice.Coffee.taste;


public class Main {
    public static void main(String[] args) {
        // Circle
        System.out.println("Circle ------------------------------------");
        Circle c1 = new Circle(3);
        System.out.println(c1.radius);
        System.out.println(c1.calculateArea());

        // Person
        System.out.println("Person ------------------------------------");
        Person person1 = new Person("john", 20);
        System.out.println(person1.name);
        System.out.println(person1.age);

        // Dog
        System.out.println("Dog ------------------------------------");
        Dog dog1 = new Dog("hound", "mimi");
        System.out.println(dog1.name);
        System.out.println(dog1.seatDown());

        // Triangle
        System.out.println("Triangle ------------------------------------");
        Triangle triangle1 = new Triangle(4);
        System.out.println(triangle1.sideLength);
        System.out.println(triangle1.circumference);
        System.out.println(triangle1.calculateArea());

        // Car
        System.out.println("Car ------------------------------------");
        Coffee.Car car1 = new Coffee.Car("Avante");
        System.out.println(car1.modelName);
        System.out.println(car1.accelSpeed());
        System.out.println(car1.accelSpeed());
        System.out.println(car1.accelSpeed());
        System.out.println(car1.breakSpeed());

        car1.showInfo();

        System.out.println(car1.increaseSpeedByAmount(100));

        car1.showInfo();

        // MP3Player
        System.out.println("MP3Player ------------------------------------");
        MP3Player mp3player1 = new MP3Player("iPod");
        mp3player1.turnOn();
        mp3player1.volumeUp();
        mp3player1.volumeUp();
        mp3player1.volumeUp();

        mp3player1.volumeDown();

        mp3player1.showInfo();

        // Calculation
        System.out.println("Calculation ------------------------------------");
        Calculation cal1 = new Calculation(1, 2, "+");
        System.out.println(cal1.Calculate());

        Calculation cal2 = new Calculation(1, 2, "-");
        System.out.println(cal2.Calculate());

        Calculation cal3 = new Calculation(1, 3, "*");
        System.out.println(cal3.Calculate());

        Calculation cal4 = new Calculation(4, 2, "/");
        System.out.println(cal4.Calculate());

        // Caculator
        System.out.println("Caculator ------------------------------------");
        Calculator cal5 = new Calculator();
        System.out.println(cal5.add(5, 4));
        System.out.println(cal5.minus(5, 4));
        System.out.println(cal5.multiply(5, 4));

        System.out.println(Calculator.staticAdd(10, 4));

        // Rectangle
        System.out.println("Rectangle ------------------------------------");
        Rectangle rec1 = new Rectangle(10, 30);
        System.out.println(rec1.angleCount);
        Rectangle.angleCount = 10;
        System.out.println(Rectangle.angleCount);

        // Dog
        System.out.println("Dog ------------------------------------");
        Dog poppy = new Dog("puddle", "poppy");
        Dog poppy2 = new Dog("puddle", "poppy");
        System.out.println(Dog.count);

        // Coffee
        taste();
        // Bread
        Bread.taste();
    }
}