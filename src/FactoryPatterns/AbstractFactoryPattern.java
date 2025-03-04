package FactoryPatterns;

/**
 * Factory Pattern
 * Creates objects of a single hierarchy
 *
 * Abstract Factory Pattern
 * Creates families of related objects
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        VehicleFactory factory = new LuxuryVehicleFactory();
        Cars cars = factory.createCar();
        Bike bike = factory.createBike();

        cars.drive();  // Output: Driving a Sedan
        bike.ride();  // Output: Riding a Sports Bike
    }
}

interface Cars {
    void drive();
}

class SedanCar implements Cars {
    public void drive() { System.out.println("Driving a Sedan"); }
}

class SUVCar implements Cars {
    public void drive() { System.out.println("Driving an SUV"); }
}

interface Bike {
    void ride();
}

class SportsBike implements Bike {
    public void ride() { System.out.println("Riding a Sports Bike"); }
}

class CruiserBike implements Bike {
    public void ride() { System.out.println("Riding a Cruiser Bike"); }
}

interface VehicleFactory {
    Cars createCar();
    Bike createBike();
}

class LuxuryVehicleFactory implements VehicleFactory {
    public Cars createCar() { return new SedanCar(); }
    public Bike createBike() { return new SportsBike(); }
}

class RegularVehicleFactory implements VehicleFactory {
    public Cars createCar() { return new SUVCar(); }
    public Bike createBike() { return new CruiserBike(); }
}
