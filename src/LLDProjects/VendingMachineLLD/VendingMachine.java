package LLDProjects.VendingMachineLLD;

public class VendingMachine {
    private VendingMachineStates vendingMachineStates = VendingMachineStates.IDLE;
    private Integer amount = 0;
    private Integer change = 0;
    private ProductInfo productInfo = null;
    private Integer quantity = 0;
    private Integer totalPrice = 0;
    public void addMoney(Integer amount) {
        if(this.vendingMachineStates.equals(VendingMachineStates.IDLE)) {
            this.vendingMachineStates = VendingMachineStates.HAS_MONEY;
            this.amount = amount;

            System.out.println("Successfully Added amount " + amount);
            ProductInfo.getAllProductInfo();
            System.out.println("Please enter a product code to Select the product");

        }
        else if(this.vendingMachineStates.equals(VendingMachineStates.HAS_MONEY)) {
            System.out.println("Vending Machine already has money added, " +
                    "please complete or cancel the current transaction and try again.");
        }
        else if(this.vendingMachineStates.equals(VendingMachineStates.SELECTED_PRODUCT)) {
            System.out.println("Vending Machine already has selected product, " +
                    "please complete or cancel the current transaction and try again.");
        }
    }

    public void selectProduct(Integer productCode, Integer quantity) {
        if(quantity == 0) {
            System.out.println("Incorrect quantity entered");
            return;
        }
        ProductInfo productInfo = ProductInfo.getProductByProductCode(productCode);

        if(productInfo == null) {
            System.out.println("Incorrect Product Code Entered, try again");
            return;
        }

        Integer amountRequired = quantity * productInfo.getAmount();

        if(this.vendingMachineStates.equals(VendingMachineStates.IDLE)) {
            System.out.println("Vending machine in IDLE state, Please add money first.");
            return;
        }
        if(this.vendingMachineStates.equals(VendingMachineStates.SELECTED_PRODUCT)) {
            System.out.println("Vending Machine already has selected product, " +
                    "please complete or cancel the current transaction and try again.");
            return;
        }
        if(this.vendingMachineStates.equals(VendingMachineStates.HAS_MONEY)) {
            if(amountRequired > this.amount) {
                System.out.println("Entered amount is less than the total product amount, " +
                        "please cancel the transaction and enter sufficient amount again " +
                        "or please select some lesser amount item");
            }
            else {
                vendingMachineStates = VendingMachineStates.SELECTED_PRODUCT;
                this.change = this.amount - amountRequired;
                this.productInfo = productInfo;
                this.quantity = quantity;
                this.totalPrice = amountRequired;
                System.out.println("Entered Product : " + productInfo.getProductName() + " quantity : " + quantity +
                        " total Product Amount : " + totalPrice);
                System.out.println("Press next to get the amount or press cancel to cancel transaction");
            }
        }
    }

    public void cancelProduct() {
        if(this.vendingMachineStates.equals(VendingMachineStates.IDLE))
            System.out.println("Vending Machine already in IDLE state");
        else if (this.vendingMachineStates.equals(VendingMachineStates.HAS_MONEY) || this.vendingMachineStates.equals(VendingMachineStates.SELECTED_PRODUCT)) {
            System.out.println("Please collect your amount " + amount);
            vendingMachineStates = VendingMachineStates.IDLE;
            this.amount = 0;
            this.change = 0;
            this.quantity = 0;
            this.productInfo = null;
            this.totalPrice = 0;
        }
    }

    public void deliverProductAndChange() {
        if(productInfo == null || vendingMachineStates.equals(VendingMachineStates.IDLE)
                || this.vendingMachineStates.equals(VendingMachineStates.HAS_MONEY)) {
            System.out.println("Please Select a product first");
            return;
        }

        System.out.println("Please enjoy your " + this.quantity + " quantity of " +  this.productInfo.getProductName());
        System.out.println("Total price : " + this.totalPrice);
        System.out.println("Please collect your change amount : " + change);

        vendingMachineStates = VendingMachineStates.IDLE;
    }

}
