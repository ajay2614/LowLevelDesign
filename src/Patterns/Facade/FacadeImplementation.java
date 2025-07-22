package Patterns.Facade;
/**
 *
 * 1. The Facade Pattern provides a simplified class to a complex system of classes.
 * It acts like a front desk, handling all the internal complexities so the user doesn't have to interact
 * with the subsystems directly.
 *
 * 2. The facade class holds an instance (or instances) of the actual complex classes and combines or
 * normalizes multiple operations into simpler methods. This allows the user to perform tasks
 * through user-friendly methods that may internally involve 2–3 underlying operations.
 *
 * */
class CarA {
    public void start() {
        System.out.println("Car A is starting.");
    }
    public void stop() {
        System.out.println("Car A is stopping.");
    }
    public void drive() {
        System.out.println("Car A is driving.");
    }
    public void changeGear() {
        System.out.println("Car A is changing gear.");
    }
    public void refuel() {
        System.out.println("Car A is being refueled.");
    }
}

class CarAFacade {
    private CarA carA;

    public CarAFacade() {
        this.carA = new CarA();
    }

    public void startCar() {
        carA.refuel();
        carA.start();
        carA.changeGear();
    }

    public void driveCar() {
        carA.drive();
    }

    public void stopCar() {
        carA.changeGear(); // maybe to neutral or park
        carA.stop();
    }

}
public class FacadeImplementation {
    public static void main(String[] args) {
        CarAFacade carAFacade = new CarAFacade();
        carAFacade.startCar();
        carAFacade.driveCar();
        carAFacade.stopCar();
    }
}
