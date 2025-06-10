package LLDProjects.ElevatorLLD;

import java.util.*;

class Elevator {

    int id;
    Direction direction;
    int topFloor;
    int currentFloor;
    int lowestFloor;
    public Elevator(int id, int topFloor) {
        this.id = id;
        currentFloor = 0;
        this.direction = Direction.IDLE;
        this.topFloor = topFloor;
        this.lowestFloor = 0;
    }
}


public class ElevatorController {

    Elevator elevator;
    TreeSet<Integer> callsUp;
    TreeSet<Integer> callsDown;
    TreeSet<Integer> destinationFloors;

    public ElevatorController(int id, int topFloor) {
        this.callsUp = new TreeSet<>();
        this.callsDown = new TreeSet<>(Collections.reverseOrder());
        this.destinationFloors = new TreeSet<>();
        this.elevator = new Elevator(id, topFloor);
    }

    public void requestWaitingHall(Direction requestDirection, int hallFloor) {
        if(elevator.direction.equals(Direction.IDLE)) {
            if(hallFloor > elevator.currentFloor)
                elevator.direction = Direction.UP;
            else if(hallFloor < elevator.currentFloor)
                elevator.direction = Direction.DOWN;
        }
        if(requestDirection.equals(Direction.UP))
            callsUp.add(hallFloor);
        else
            callsDown.add(hallFloor);
    }

    public void requestDestinationFloor(int destinationFloor) {
        if(elevator.direction.equals(Direction.IDLE) && destinationFloor > elevator.currentFloor)
            elevator.direction = Direction.UP;
        else if(elevator.direction.equals(Direction.IDLE) && destinationFloor < elevator.currentFloor)
            elevator.direction = Direction.DOWN;

        destinationFloors.add(destinationFloor);
    }

    public void processCurrentFloor() {

        if(elevator.direction.equals(Direction.UP)) {
            System.out.println("Current Floor -> " + elevator.currentFloor);
            if(!callsUp.isEmpty() && callsUp.contains(elevator.currentFloor)) {
                System.out.println("Passenger entering at current floor -> " + elevator.currentFloor);
                callsUp.remove(elevator.currentFloor);
            }
            if(!destinationFloors.isEmpty() && destinationFloors.contains(elevator.currentFloor)) {
                System.out.println("Passenger exiting at current floor -> " + elevator.currentFloor);
                destinationFloors.remove(elevator.currentFloor);
            }
        }
        else if (elevator.direction.equals(Direction.DOWN)) {
            System.out.println("Current Floor -> " + elevator.currentFloor);
            if(!callsDown.isEmpty() && callsDown.contains(elevator.currentFloor)) {
                System.out.println("Passenger entering lift at current floor -> " + elevator.currentFloor);
                callsDown.remove(elevator.currentFloor);
            }
            else if(!destinationFloors.isEmpty() && destinationFloors.contains(elevator.currentFloor)) {
                System.out.println("Passenger exiting at current floor -> " + elevator.currentFloor);
                destinationFloors.remove(elevator.currentFloor);
            }
        }
    }

    public void checkLimitFloor() {
        if(elevator.currentFloor == elevator.topFloor && elevator.direction.equals(Direction.UP)) {
            System.out.println("Elevator at Top Floor, moving down");
            elevator.direction = Direction.DOWN;
        }
        if(elevator.currentFloor == elevator.lowestFloor && elevator.direction.equals(Direction.DOWN)) {
            System.out.println("Elevator at Lowest Floor, moving up");
            elevator.direction = Direction.UP;
        }
    }
    public void getNextFloor() {
        if(elevator.direction.equals(Direction.UP)) {
            Integer nextUp = getNextUp(elevator.currentFloor);
            if(nextUp != null) {
                elevator.currentFloor = nextUp;
            }
        }
        if(elevator.direction.equals(Direction.DOWN)) {
            Integer nextDown = getNextDown(elevator.currentFloor);
            if(nextDown != null) {
                elevator.currentFloor = nextDown;
            }
        }
    }


    public Integer getNextUp(int currentFloor) {
        Integer up = callsUp.higher(currentFloor);
        Integer car = destinationFloors.higher(currentFloor);
        if(car == null && up == null)
            return null;
        if (up == null) return car;
        if (car == null) return up;
        return Math.min(up, car);
    }
    public Integer getNextDown(int currentFloor) {
        Integer down = callsDown.lower(currentFloor);
        Integer car = destinationFloors.lower(currentFloor);
        if(car == null && down == null)
            return null;
        if (down == null) return car;
        if (car == null) return down;
        return Math.max(down, car);
    }

    public void processElevator() {
        while (!callsUp.isEmpty() || !callsDown.isEmpty() || !destinationFloors.isEmpty()) {
            processCurrentFloor();
            checkLimitFloor();
            getNextFloor();
        }

        elevator.direction = Direction.IDLE;
    }
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(1, 9); // floors: 0 - 9

        System.out.println("\n=== Scenario 1: Call from floor 2 to go UP, then exit at 6 ===");
        controller.requestWaitingHall(Direction.UP, 2);       // person calls from floor 2
        controller.requestDestinationFloor(6);                // person selects floor 6 once inside
        controller.processElevator();

        System.out.println("\n=== Scenario 2: Call from floor 7 to go DOWN, then exit at 3 ===");
        controller.requestWaitingHall(Direction.DOWN, 7);     // person at floor 7 wants to go down
        controller.requestDestinationFloor(3);                // person selects floor 3
        controller.processElevator();

        System.out.println("\n=== Scenario 3: Interleaved Calls - 1 UP, 8 DOWN, 4 UP ===");
        controller.requestWaitingHall(Direction.UP, 1);
        controller.requestWaitingHall(Direction.DOWN, 8);
        controller.requestWaitingHall(Direction.UP, 4);
        controller.requestDestinationFloor(9);                // person from 4 wants to go to 9
        controller.requestDestinationFloor(0);                // someone inside wants to go to 0
        controller.processElevator();

        System.out.println("\n=== Scenario 4: Idle at floor 5, person enters and presses 7 ===");
        controller.elevator.currentFloor = 5;
        controller.elevator.direction = Direction.IDLE;
        controller.requestDestinationFloor(7);                // elevator should start going up
        controller.processElevator();

        System.out.println("\n=== Scenario 5: Requests beyond limits (should be ignored) ===");
        controller.requestWaitingHall(Direction.UP, 10);      // beyond top floor
        controller.requestDestinationFloor(-1);               // below bottom
        controller.processElevator();

        System.out.println("\n=== Scenario 6: Back-to-back UP/DOWN switching ===");
        controller.requestWaitingHall(Direction.UP, 2);
        controller.requestDestinationFloor(7);
        controller.processElevator();
        controller.requestWaitingHall(Direction.DOWN, 9);
        controller.requestDestinationFloor(1);
        controller.processElevator();
    }
}
