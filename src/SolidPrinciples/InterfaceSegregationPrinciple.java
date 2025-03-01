package SolidPrinciples;


public class InterfaceSegregationPrinciple {
}

/**
 * Definition: A class should not be forced to implement interfaces it doesn’t use.
 * Why? Large, bloated interfaces force unnecessary dependencies on classes.
 */

interface Worker {
    void work();
    void eat();
}
class Robot implements Worker {
    public void work() { /* Works */ }
    public void eat() { throw new UnsupportedOperationException(); } // Robots don't eat!
}

interface Workable {
    void work();
}
interface Eatable {
    void eat();
}
class Robots implements Workable {
    public void work() { /* Works */ }
}

class Humans implements Workable, Eatable {
    public void work() { /* Works */ }
    public void eat() { /* Eats */ }
}
