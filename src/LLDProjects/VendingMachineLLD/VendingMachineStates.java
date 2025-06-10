package LLDProjects.VendingMachineLLD;

public enum VendingMachineStates {
    IDLE("IDLE"),
    HAS_MONEY("HASMONEY"),
    SELECTED_PRODUCT("SELECTEDPRODUCT");

    String state;

    VendingMachineStates(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
