public class Main {

    public static void main(String[] args) {


        Store store = new Store();
        for (int i = 0; i < 10; i++) {
            store.closeReceipt(Store.generateRandomReceipt());
        }
        store.printCustomerScores();

    }




}