package LLDProjects.CarRentalSystemLLD;

public interface VehicleCRS {
    public String getType();
    public int getId();
    public boolean status();
    public void setStatus(boolean val);
    public String getName();
    public int getVehiclePricePerDay();
}
