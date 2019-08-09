public class Customer {

    private String name;
    private int customerScore;

    public Customer(String name){
        this(name, 0);
    }

    public Customer (String name, int customerScore){
        this.name = name;
        this.customerScore = customerScore;
    }

    public void addToCustomerScore(final int amount){
        customerScore += amount;
    }

    public int getCustomerScore() {
        return customerScore;
    }

    public String getName(){
        return name;
    }


}
