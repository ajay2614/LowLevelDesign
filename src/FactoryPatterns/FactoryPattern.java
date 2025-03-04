package FactoryPatterns;

public class FactoryPattern {
    public static void main(String[] args) {
        Car car = CarFactory.getCar("Sedan");
        car.drive(); // Output: Driving a Sedan
    }
}

interface Car {
    void drive();
}

class Sedan implements Car {
    public void drive() { System.out.println("Driving a Sedan"); }
}

class SUV implements Car {
    public void drive() { System.out.println("Driving an SUV"); }
}

class CarFactory {
    static Car getCar(String type) {
        if (type.equalsIgnoreCase("Sedan")) return new Sedan();
        if (type.equalsIgnoreCase("SUV")) return new SUV();
        return null;
    }
}

