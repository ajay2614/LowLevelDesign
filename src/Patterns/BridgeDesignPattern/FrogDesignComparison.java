package Patterns.BridgeDesignPattern;
// Frog Design Comparison: Legacy vs Abstract Class vs Bridge Pattern
// ------------------------------------------------------------------------
// 1. ❌ Legacy: Everything is hardcoded into a single class
class LegacyFrog {
    void breathe() {
        // ❌ If breathing method changes, this class must change — violates Open/Closed Principle
        System.out.println("Breathing with lungs and skin.");
    }

    void describe() {
        System.out.println("I am a legacy frog.");
    }
}

// ------------------------------------------------------------------------
// 2. ❌ Abstract Class version: Inheritance-based tight coupling

// ❌ Inheritance forces us to create separate subclasses for each variation
abstract class AbstractFrog {
    abstract void breathe();

    void describe() {
        System.out.println("I am a frog using abstract class.");
    }
}

class FrogWithLungs extends AbstractFrog {
    void breathe() {
        System.out.println("Breathing with lungs.");
    }
}

class FrogWithSkin extends AbstractFrog {
    void breathe() {
        System.out.println("Breathing through skin.");
    }
}

class FrogWithLungsAndSkin extends AbstractFrog {
    void breathe() {
        System.out.println("Breathing with lungs and skin.");
    }
    // ❌ Adding "Lungs + Gills" or "Gills + Skin" => new subclasses every time
}

// ------------------------------------------------------------------------
// 3. ✅ Bridge Pattern: Use composition (has-a) instead of inheritance (is-a)
// ✅ Goal: Separate abstraction (Frog/Animal) from implementation (BreathingMechanism)
// ✅ Advantage: Change or extend behavior without modifying existing classes

// Bridge interface
interface BreathingMechanism {
    void breathe();
}

// Implementations of breathing
class Lungs implements BreathingMechanism {
    public void breathe() {
        System.out.println("Breathing with lungs.");
    }
}

class Skin implements BreathingMechanism {
    public void breathe() {
        System.out.println("Breathing through skin.");
    }
}

class LungsAndSkin implements BreathingMechanism {
    public void breathe() {
        System.out.println("Breathing with lungs and skin.");
    }
}

// You can add new mechanisms like Gills, or External Respiration without touching existing code!

// Abstract animal class holding reference to the breathing strategy (composition)
abstract class Animal {
    protected BreathingMechanism breathingMechanism;

    public Animal(BreathingMechanism breathingMechanism) {
        this.breathingMechanism = breathingMechanism;
    }

    void performBreathing() {
        breathingMechanism.breathe(); // Delegates breathing behavior
    }

    abstract void describe();
}

// Refactored Frog using Bridge Pattern, this way frog can have different types of breathing without changing the Frog class itself
class RefactoredFrog extends Animal {
    public RefactoredFrog(BreathingMechanism breathingMechanism) {
        super(breathingMechanism);
    }

    void describe() {
        System.out.println("I am a refactored frog using Bridge Pattern.");
    }
}

public class FrogDesignComparison {
    public static void main(String[] args) {
        System.out.println("----- 1. Legacy Hardcoded Frog -----");
        LegacyFrog legacyFrog = new LegacyFrog();
        legacyFrog.describe();
        legacyFrog.breathe(); // ❌ Hardcoded: Cannot change breathing without modifying class

        System.out.println("\n----- 2. Abstract Class Version -----");
        AbstractFrog frog1 = new FrogWithLungs();
        frog1.describe();
        frog1.breathe(); // ❌ Still tightly coupled to breathing type

        AbstractFrog frog2 = new FrogWithSkin();
        frog2.describe();
        frog2.breathe();

        AbstractFrog frog3 = new FrogWithLungsAndSkin();
        frog3.describe();
        frog3.breathe();
        // ❌ Again, to add new type like "Gills", you must create new subclass
        // ❌ Inheritance explosion if combinations increase

        System.out.println("\n----- 3. Bridge Pattern Version -----");
        Animal bridgeFrog1 = new RefactoredFrog(new Lungs());
        bridgeFrog1.describe();
        bridgeFrog1.performBreathing(); // ✅ You can change breathing behavior independently

        Animal bridgeFrog2 = new RefactoredFrog(new Skin());
        bridgeFrog2.describe();
        bridgeFrog2.performBreathing();

        Animal bridgeFrog3 = new RefactoredFrog(new LungsAndSkin());
        bridgeFrog3.describe();
        bridgeFrog3.performBreathing();
        // ✅ New behavior? Just add new BreathingMechanism implementation!
    }
}