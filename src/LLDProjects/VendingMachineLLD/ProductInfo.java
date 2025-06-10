package LLDProjects.VendingMachineLLD;

public enum ProductInfo {
    PEPSI(101, "Pepsi", 50),
    THUMBS_UP(102, "Thumbs Up", 60),
    COCA_COLA(103, "Coca Cole", 55),
    MOUNTAIN_DEW(104, "Mountain Dew", 45),
    SPRITE(105, "Sprite", 40),
    FROOTI(106, "Frooti", 35)
    ;
    Integer productCode;
    String productName;
    Integer amount;

    ProductInfo(Integer productCode, String productName, Integer amount) {
        this.productCode = productCode;
        this.productName = productName;
        this.amount = amount;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public static ProductInfo getProductByProductCode(Integer productCode) {
        for(ProductInfo productInfo : ProductInfo.values()) {
            if (productInfo.getProductCode().equals(productCode))
                return productInfo;
        }
        return null;
    }

    public static void getAllProductInfo() {
        System.out.println("Currently available Products are : ");
        for(ProductInfo productInfo : ProductInfo.values()) {
            System.out.println("Product Code : " + productInfo.getProductCode() + " Product Name : " + productInfo.getProductName() + " Product price : " + productInfo.getAmount());
        }
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
