import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private List<Product> boughtProducts;
    private String customerName;


    public Receipt(String customerName){
        this.customerName = customerName;
        boughtProducts = new ArrayList<>();
    }

    public void print(){
        System.out.println("Receipt of " + (customerName.equals("") ? "anonymous customer" : customerName));
        System.out.println("===============");
        for (int i = 0; i < boughtProducts.size(); i++){
            System.out.println(boughtProducts.get(i).getBoughtAmount() + " ex. " + boughtProducts.get(i).getProductName());
        }
        System.out.println("_________________");
        System.out.println("Total of " + getTotalAmountOfProducts() + " products bought. \n\n");
    }

    public void addProduct(final String productName, final int amount){

        if (findProduct(productName) == -1 ) {
            // product is not yet on receipt
            Product product = new Product(productName, amount);
            boughtProducts.add(product);
        }
        else {
            // product is already on receipt, so only the bought amount needs to be updated
            boughtProducts.get(findProduct(productName)).addAmount(amount);
        }
    }

    private int findProduct(final String productName){
        // returns index of the product in the arraylist boughtProducts
        for (int i = 0; i < boughtProducts.size(); i++){
            if (boughtProducts.get(i).getProductName().equalsIgnoreCase(productName)){
                return i;
            }
        }
        return -1;
    }

    public int getProductAmount(final String productName){
        if (findProduct(productName) == -1){
            return 0;
        }
        return boughtProducts.get(findProduct(productName)).getBoughtAmount();

    }

    public int getTotalAmountOfProducts(){
        int totalAmount = 0;
        for (int i = 0; i < boughtProducts.size(); i++){
            totalAmount += boughtProducts.get(i).getBoughtAmount();
        }
        return totalAmount;
    }

    public String getCustomerName(){
        return customerName;
    }

}
