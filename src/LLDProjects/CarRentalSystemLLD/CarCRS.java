package LLDProjects.CarRentalSystemLLD;

public class CarCRS implements VehicleCRS{
    int carId;
    String carName;
    int pricePerDay;
    boolean available;

    public CarCRS(int carId, String carName, int pricePerDay) {
        this.carId = carId;
        this.carName = carName;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }


    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public int getId() {
        return this.carId;
    }

    @Override
    public boolean status() {
        return this.available;
    }

    @Override
    public void setStatus(boolean val) {
        this.available = val;
    }

    @Override
    public String getName() {
        return this.carName;
    }

    @Override
    public int getVehiclePricePerDay() {
        return this.pricePerDay;
    }
}
