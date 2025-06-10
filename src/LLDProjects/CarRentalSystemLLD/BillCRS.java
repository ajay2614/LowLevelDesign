package LLDProjects.CarRentalSystemLLD;

public class BillCRS {
    int billId;
    boolean billPaid;
    String billPaidVia;
    int totalPrice;

    BillCRS(int billId, int vehiclePricePerDay, int totalDays) {
        this.billId = billId;
        this.billPaid = false;
        this.totalPrice = vehiclePricePerDay * totalDays;
    }
    public void payBill(String payType) {
        billPaid = true;
        this.billPaidVia = payType;
    }
}
