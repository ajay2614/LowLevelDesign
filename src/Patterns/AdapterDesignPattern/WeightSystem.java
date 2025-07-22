package Patterns.AdapterDesignPattern;


/**
 * The role of adapter is to convert one system to another, do not get confused and use if-else like
 * factory method
 */

// Target interface
interface KgWeightSystem {
    int getWeightInKg();
}

// Adaptee
interface PoundWeightSystem {
    int getWeightInPound();
}

// Adaptee implementation
class PoundWeightSystemImpl implements PoundWeightSystem {
    public int getWeightInPound() {
        return 60;
    }
}

// Adapter class to convert Pound to Kg
class PoundToKgAdapter implements KgWeightSystem {
    private PoundWeightSystem poundWeightSystem;

    public PoundToKgAdapter(PoundWeightSystem poundWeightSystem) {
        this.poundWeightSystem = poundWeightSystem;
    }

    @Override
    public int getWeightInKg() {
        // Convert pounds to kg (approx conversion)
        return (int)(poundWeightSystem.getWeightInPound() * 0.453592);
    }
}

// Client
public class WeightSystem {
    public static void main(String[] args) {
        PoundWeightSystem oldWeight = new PoundWeightSystemImpl();
        KgWeightSystem adapted = new PoundToKgAdapter(oldWeight);

        System.out.println("Weight in KG (adapted from pounds): " + adapted.getWeightInKg());
    }
}
