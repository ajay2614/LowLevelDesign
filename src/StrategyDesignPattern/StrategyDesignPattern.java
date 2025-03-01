package StrategyDesignPattern;

public class StrategyDesignPattern { }

/**
 * The Strategy Design Pattern is a behavioral design pattern that allows a class's
 * behavior to be selected at runtime
 * It defines a family of algorithms, encapsulates each one, and makes them interchangeable.
 *
 * Key Concepts
 * Context – Maintains a reference to a strategy object and delegates the behavior to it.
 * Strategy Interface – Defines a common interface for all supported strategies.
 * Concrete Strategies – Implement different variations of the algorithm.
 *
 *When to Use the Strategy Pattern
 * When you have multiple related algorithms and want to switch them at runtime.
 * When you want to avoid using multiple conditional statements (if-else or switch-case)
 * to choose behavior.
 * When different behaviors should be easily interchangeable without modifying the client code.
 *
 *
 * 1. IS-A Relationship (Inheritance)
 * Represents inheritance between classes.
 * Created using the extends (for classes) or implements (for interfaces) keyword.
 * Example: A Dog IS-A Animal because a dog is a type of animal.
 * class Animal {}  // Parent class
 * class Dog extends Animal {}  // Dog IS-A Animal
 * 2. HAS-A Relationship (Composition/Aggregation)
 * Represents composition (one class contains another as a field).
 * Created by including an instance of another class as a member.
 * Example: A Car HAS-A Engine because a car contains an engine.
 * class Engine {}
 * class Car {
 *     Engine engine;  // Car HAS-A Engine
 * }
 * Key Difference:
 * IS-A → Inheritance (A Dog IS-A Animal).
 * HAS-A → Composition (A Car HAS-A Engine).
 */

interface DriveStrategy {
    public void drive();
}

class SportsDrive implements DriveStrategy {
    public void drive() {
        System.out.println("This is a sports drive");
    }
}
class NormalDrive implements DriveStrategy {
    public void drive() {
        System.out.println("This is a normal drive");
    }
}

class Vehicle {
    DriveStrategy driveStrategy;

    Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }
}

class NormalCar extends Vehicle {

    NormalCar() {
        super(new NormalDrive());
    }
}

class SportsCar extends Vehicle {
    SportsCar() {
        super(new SportsDrive());
    }
}