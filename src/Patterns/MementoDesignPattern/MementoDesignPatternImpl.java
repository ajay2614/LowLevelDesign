package Patterns.MementoDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Originator class: Holds the current state (length, breadth)
 * and can save or restore its state via Mementos.
 */
class MasterDetails {
    private int length = 0;
    private int breadth = 0;
    private MementoDetailsSeries mementoSeries = new MementoDetailsSeries();

    // Update state and save old state as a memento
    public void updateLengthBreadth(int length, int breadth) {
        if (this.length != 0 || this.breadth != 0) { // only save if previous state exists
            MementoDetails memento = new MementoDetails(this.length, this.breadth);
            mementoSeries.addMemento(memento);
        }
        this.length = length;
        this.breadth = breadth;
    }

    // Restore the last saved state
    public void revertLast() {
        MementoDetails memento = mementoSeries.undo();
        if (memento != null) {
            this.length = memento.getLength();
            this.breadth = memento.getBreadth();
        }
    }

    @Override
    public String toString() {
        return "Length: " + length + ", Breadth: " + breadth;
    }
}

/**
 * Memento class: Stores a snapshot of the MasterDetails state.
 */
class MementoDetails {
    private final int length;
    private final int breadth;

    public MementoDetails(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public int getLength() { return length; }
    public int getBreadth() { return breadth; }
}

/**
 * Caretaker class: Maintains the history of mementos.
 */
class MementoDetailsSeries {
    private final List<MementoDetails> mementoList = new ArrayList<>();

    public void addMemento(MementoDetails memento) {
        mementoList.add(memento);
    }

    public MementoDetails undo() {
        if (!mementoList.isEmpty()) {
            return mementoList.remove(mementoList.size() - 1);
        }
        return null;
    }
}

/**
 * Demo class
 */
public class MementoDesignPatternImpl {
    public static void main(String[] args) {
        MasterDetails master = new MasterDetails();

        master.updateLengthBreadth(10, 20);
        System.out.println(master); // Length: 10, Breadth: 20

        master.updateLengthBreadth(30, 40);
        System.out.println(master); // Length: 30, Breadth: 40

        master.revertLast();
        System.out.println(master); // Length: 10, Breadth: 20

        master.revertLast();
        System.out.println(master); // Length: 0, Breadth: 0 (initial state)
    }
}
