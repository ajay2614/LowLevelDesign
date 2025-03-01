package SolidPrinciples;

public class LiskovSubstitutionPrinciple {
}

/**
 * A Base class must be able to substitute the child class at the time of implementation
 * Parent p = new Child1()
 * Parent p = new Child2()
 */

//Bad Approach
class Bird {
    void fly() { /* All birds fly */ }
}
class Penguin extends Bird {
    void fly() { throw new UnsupportedOperationException(); } // Penguins can’t fly!
}

//Good Approach
abstract class Birds { }

class FlyingBird extends Bird {
    void fly() { /* Can fly */ }
}
class Penguins extends Bird {
    void swim() { /* Can swim */ }
}
