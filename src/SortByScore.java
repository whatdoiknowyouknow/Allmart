import java.util.Comparator;

public class SortByScore implements Comparator<Customer> {
        // Used for sorting in descending order of
        // customerscore
        public int compare(Customer a, Customer b)
        {
            return b.getCustomerScore() - a.getCustomerScore();
        }
    }



