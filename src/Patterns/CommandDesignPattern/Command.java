package Patterns.CommandDesignPattern;

public interface Command {
    void doExecute(AirConditioner ac);
    void undo(AirConditioner ac);
}

// ----- Concrete Commands -----

class ACTurnOnCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnOnAc(); }
    public void undo(AirConditioner ac) { ac.turnOffAc(); }
}

class ACTurnOffCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnOffAc(); }
    public void undo(AirConditioner ac) { ac.turnOnAc(); }
}

class ACLightOnCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnLightOn(); }
    public void undo(AirConditioner ac) { ac.turnLightOff(); }
}

class ACLightOffCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnLightOff(); }
    public void undo(AirConditioner ac) { ac.turnLightOn(); }
}

class ACSummerModeOnCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnSummerModeOn(); }
    public void undo(AirConditioner ac) { ac.turnSummerModeOff(); }
}

class ACSummerModeOffCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnSummerModeOff(); }
    public void undo(AirConditioner ac) { ac.turnSummerModeOn(); }
}

class ACRainModeOnCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnRainModeOn(); }
    public void undo(AirConditioner ac) { ac.turnRainModeOff(); }
}

class ACRainModeOffCommand implements Command {
    public void doExecute(AirConditioner ac) { ac.turnRainModeOff(); }
    public void undo(AirConditioner ac) { ac.turnRainModeOn(); }
}
