import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {

    //these two lines below are only here to be able to generate random receipts
    private static final String[] availableProducts = {"Smartphone X", "Smartphone Y", "cable", "lamp", "glass", "bottle of gin", "speakers", "placemat", "wallet", "vacuum cleaner", "mouse", "java course", "c# course", "book", "booklet"};
    private static final String[] clients = {"Jane Janukova", "Tom", "Geert", "Hans", "Fien", "Sophie", "Ma", "Pa", ""}; // 9

    private final String MANAGERSWIFE = "Jane Janukova";
    private final String SPECIALPRODUCT = "Smartphone X";
    private List<Receipt> receipts;
    private List<Customer> customerScores;


    public Store() {

        receipts = new ArrayList<>();
        customerScores = new ArrayList<>();
    }

    public void printCustomerScores() {
        Collections.sort(customerScores, new SortByScore());
        System.out.println("CUSTOMER OF THE DAY");
        System.out.println("====================");
        String format = "%-20s%s%n";
        System.out.printf(format, "Customer", "Score");
        System.out.println("");
        for (int i = 0; i < customerScores.size(); i++) {
            System.out.printf(format, customerScores.get(i).getName(), customerScores.get(i).getCustomerScore());
        }
    }

    private int computeCustomerScore(final Receipt receipt) {
        int customerScore = 0;
        if (!receipt.getCustomerName().equals("")) {

            // extra points for managers wife
            if (receipt.getCustomerName().equalsIgnoreCase(MANAGERSWIFE)) {
                customerScore += 10;
            }

            // extra points depending on number of bought products
            if (receipt.getTotalAmountOfProducts() > 30) {
                customerScore += 9;
            } else if (receipt.getTotalAmountOfProducts() > 20) {
                customerScore += 7;
            } else if (receipt.getTotalAmountOfProducts() > 10) {
                customerScore += 5;
            }

            // extra points per special product (smartphone X)
            customerScore += receipt.getProductAmount(SPECIALPRODUCT) * 5;
        }
        return customerScore;
    }

    public void closeReceipt(final Receipt receipt) {
        if (computeCustomerScore(receipt) != 0) {
            saveCustomerScore(receipt.getCustomerName(), computeCustomerScore(receipt));
        }
        receipt.print();
        receipts.add(receipt);
    }

    private void saveCustomerScore(final String customerName, final int customerScore) {
        if (findCustomer(customerName) != -1) {
            // customer already in our arraylist of customerscores
            customerScores.get(findCustomer(customerName)).addToCustomerScore(customerScore);
        } else {
            Customer customer = new Customer(customerName, customerScore);
            customerScores.add(customer);
        }
    }

    private int findCustomer(final String customerName) {
        // returns index of customer in arraylist of customerScores
        for (int i = 0; i < customerScores.size(); i++) {
            if (customerScores.get(i).getName().equalsIgnoreCase(customerName)) {
                return i;
            }
        }
        return -1;
    }



    public static Receipt generateRandomReceipt() {

        // create receipt
        int randomIndex = (int) (Math.random() * clients.length);
        String customerName = clients[randomIndex];
        Receipt receipt = new Receipt(customerName);

        // define number of products that will be bought
        int numberOfProducts = (int) (Math.random() * availableProducts.length) + 1;

        // add that number of products (it can be the same product more than once), each with a random amount
        for (int i = 0; i < numberOfProducts; i++) {
            int productAmount = (int) (Math.random() * 5) + 1;

            // picking a random product from the array of availableProducts
            randomIndex = (int) (Math.random() * availableProducts.length);
            String productName = availableProducts[randomIndex];

            receipt.addProduct(productName, productAmount);
        }
        return receipt;
    }

}
