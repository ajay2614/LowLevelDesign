package Patterns.MediatorDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Mediator Design Pattern Example
 * The Mediator pattern centralizes communication between objects (colleagues).
 * Flights do not talk to each other directly; they interact only with ATC.
 * ATC coordinates takeoffs and landings, ensuring runways are used safely.
 * Difference with other patterns:
 * - Mediator vs Observer:
 *   Mediator centralizes and controls communication between objects (flights talk via ATC).
 *   Observer is about event subscription/notification, without central control (publisher → subscribers).
 * - Mediator vs Proxy:
 *   Mediator coordinates interaction between multiple objects.
 *   Proxy controls access to a single object (e.g. adds security, caching, logging).
 */

enum FlightStatus {
    IDLE, INAIR, LANDING
}

// ---------------- Mediator Interface ----------------
interface Mediator {
    void addFlight(Flight flight);
    boolean isTakeoffRunwayIdle();
    boolean isLandingRunwayIdle();
    void notifyAndTakeoff(Flight flight);
    void notifyTakeOffCompleted(Flight flight);
    void notifyAndLand(Flight flight);
    void notifyLandingCompleted(Flight flight);
}

// ---------------- Concrete Mediator ----------------
class ATC implements Mediator {
    List<Flight> flightList = new ArrayList<>();
    boolean landingRunwayIdle = true;
    boolean takeoffRunwayIdle = true;

    @Override
    public void addFlight(Flight flight) {
        flightList.add(flight);
    }

    @Override
    public boolean isTakeoffRunwayIdle() {
        return takeoffRunwayIdle;
    }

    @Override
    public boolean isLandingRunwayIdle() {
        return landingRunwayIdle;
    }

    @Override
    public void notifyAndTakeoff(Flight flight) {
        takeoffRunwayIdle = false;
        for (Flight f : flightList) {
            if (f.id != flight.id && f.currentStatus == FlightStatus.IDLE) {
                f.radioMessage("Flight " + flight.id + " is taking off. Hold position.");
            }
        }
    }

    @Override
    public void notifyTakeOffCompleted(Flight flight) {
        System.out.println("✅ Flight " + flight.id + " successfully took off.");
        takeoffRunwayIdle = true;
        for (Flight f : flightList) {
            if (f.currentStatus == FlightStatus.IDLE) {
                f.radioMessage("Takeoff runway is clear again.");
            }
        }
    }

    @Override
    public void notifyAndLand(Flight flight) {
        landingRunwayIdle = false;
        for (Flight f : flightList) {
            if (f.id != flight.id && f.currentStatus == FlightStatus.INAIR) {
                f.radioMessage("Flight " + flight.id + " is landing. Maintain altitude until clear.");
            }
        }
    }

    @Override
    public void notifyLandingCompleted(Flight flight) {
        System.out.println("🛬 Flight " + flight.id + " successfully landed.");
        landingRunwayIdle = true;
        for (Flight f : flightList) {
            if (f.currentStatus == FlightStatus.INAIR) {
                f.radioMessage("Landing runway is clear again.");
            }
        }
    }
}

// ---------------- Colleague Class ----------------
class Flight {
    public Mediator atc;
    FlightStatus currentStatus = FlightStatus.IDLE;
    int id;

    public Flight(int id, Mediator atc) {
        this.atc = atc;
        this.id = id;
        atc.addFlight(this);
    }

    public void takeoff() {
        if (currentStatus != FlightStatus.IDLE) {
            System.out.println("❌ Flight " + id + ": Cannot takeoff, current status = " + currentStatus);
            return;
        }

        if (!atc.isTakeoffRunwayIdle()) {
            System.out.println("❌ Flight " + id + ": Takeoff runway not idle right now.");
            return;
        }

        atc.notifyAndTakeoff(this);
        currentStatus = FlightStatus.INAIR;
        atc.notifyTakeOffCompleted(this);
    }

    public void land() {
        if (currentStatus != FlightStatus.INAIR) {
            System.out.println("❌ Flight " + id + ": Cannot land, current status = " + currentStatus);
            return;
        }

        if (!atc.isLandingRunwayIdle()) {
            System.out.println("❌ Flight " + id + ": Landing runway not idle right now.");
            return;
        }

        atc.notifyAndLand(this);
        currentStatus = FlightStatus.LANDING;
        atc.notifyLandingCompleted(this);
        currentStatus = FlightStatus.IDLE; // after landing, back to idle
    }

    public void radioMessage(String message) {
        System.out.println("📡 Flight " + id + " received: " + message);
    }
}

// ---------------- Demo ----------------
public class MediatorDesignPattern {
    public static void main(String[] args) {
        Mediator atc = new ATC();

        Flight f1 = new Flight(101, atc);
        Flight f2 = new Flight(102, atc);
        Flight f3 = new Flight(103, atc);

        System.out.println("\n--- Simulation Start ---\n");

        f1.takeoff();    // valid
        f2.land();       // invalid (was never in air)
        f1.land();       // valid (f1 was in air)
        f3.takeoff();    // valid
        f3.land();       // valid

        System.out.println("\n--- Simulation End ---");
    }
}
