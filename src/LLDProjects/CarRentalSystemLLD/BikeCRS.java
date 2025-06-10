package LLDProjects.CarRentalSystemLLD;

public class BikeCRS implements VehicleCRS{

    int bikeId;
    String bikeName;
    int pricePerDay;
    boolean available;

    public BikeCRS(int bikeId, String bikeName, int pricePerDay) {
        this.bikeId = bikeId;
        this.bikeName = bikeName;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }


    @Override
    public String getType() {
        return "Bike";
    }

    @Override
    public int getId() {
        return this.bikeId;
    }

    @Override
    public boolean status() {
        return available;
    }

    @Override
    public void setStatus(boolean val) {
        this.available = val;
    }

    @Override
    public String getName() {
        return this.bikeName;
    }

    @Override
    public int getVehiclePricePerDay() {
        return this.pricePerDay;
    }
}
