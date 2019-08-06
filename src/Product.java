public class Product {

    private String productName;
    private int boughtAmount;

    public Product(String productName, int boughtAmount){
        this.productName = productName;
        this.boughtAmount = boughtAmount;
    }


    public String getProductName() {
        return productName;
    }

    public int getBoughtAmount() {
        return boughtAmount;
    }

    public void addAmount(final int amount){
        this.boughtAmount += amount;
    }
}
