package Patterns.CommandDesignPattern;

import java.util.Stack;

public class RemoteControl {

    private AirConditioner airConditioner;
    private Command[] commandSlots;
    private Stack<Command> history = new Stack<>();

    public RemoteControl(AirConditioner ac, int numButtons) {
        this.airConditioner = ac;
        commandSlots = new Command[numButtons];
    }

    // Configure a button
    public void setCommand(int slot, Command command) {
        commandSlots[slot] = command;
    }

    // Press a button
    public void pressButton(int slot) {
        if (commandSlots[slot] != null) {
            commandSlots[slot].doExecute(airConditioner);
            history.push(commandSlots[slot]);
        }
    }

    // Undo last action
    public void undo() {
        if (!history.isEmpty()) {
            history.pop().undo(airConditioner);
        }
    }

    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner();
        RemoteControl remote = new RemoteControl(ac, 3);
        //Developer Level
        remote.setCommand(0, new ACTurnOnCommand());
        remote.setCommand(1, new ACSummerModeOnCommand());
        remote.setCommand(2, new ACLightOnCommand());

        //User Level
        remote.pressButton(0);
        remote.pressButton(1);
        remote.undo();
    }
}
