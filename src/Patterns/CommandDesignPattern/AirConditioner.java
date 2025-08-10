package Patterns.CommandDesignPattern;

//Receiver Class
public class AirConditioner {
    public void turnOnAc() {
        System.out.println("AC is On");
    }
    public void turnOffAc() {
        System.out.println("AC is Off");
    }
    public void turnSummerModeOn() {
        System.out.println("Summer Mode is On");
    }
    public void turnSummerModeOff() {
        System.out.println("Summer Mode is Off");
    }
    public void turnRainModeOn() {
        System.out.println("Rain Mode is On");
    }
    public void turnRainModeOff() {
        System.out.println("Rain Mode is Off");
    }
    public void turnLightOn() {
        System.out.println("Light is On");
    }
    public void turnLightOff() {
        System.out.println("Light is Off");
    }
}
