package Patterns.Decorator;

public class Decorator {

    public static void main(String[] args) {

        Coffee simpleCoffee = new SimpleCoffee();

        System.out.println("Name -> " + simpleCoffee.type() + " Cost -> " + simpleCoffee.getCost());

        Coffee sugar = new Sugar(simpleCoffee);

        System.out.println("Name -> " + sugar.type() + " Cost -> " + sugar.getCost());
    }
}

/**
 * What is a Patterns.Decorator in Java?
 * A Patterns.Decorator in Java is a structural design pattern that allows behavior to be added to
 * individual objects dynamically, without modifying the existing class code.
 *
 * It follows the principle of composition over inheritance.
 */

interface Coffee {
    public int getCost();
    public String type();

}

class SimpleCoffee implements Coffee {

    public int getCost() {
        return 10;
    }

    public String type() {
        return "Simple Coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    Coffee coffee;

    CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }


    @Override
    public int getCost() {
        return coffee.getCost();
    }

    @Override
    public String type() {
        return coffee.type();
    }
}
class Sugar extends CoffeeDecorator {

    Sugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getCost() {
        return super.getCost() + 1;
    }
    public String type() {
        return super.type() + " with Sugar";
    }

}