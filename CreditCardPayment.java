import java.util.Map;
import java.util.Date;

public class CreditCardPayment extends Payment {

    private Map<String, String> config;

    public CreditCardPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> config) {
        super(amount, currency, customerInfo);
        this.config = config;
    }

    @Override
    public boolean validatePayment(Map<String, String> paymentDetails) {
       return paymentDetails.containsKey("card_number") && paymentDetails.get("card_number").length() >= 12;
    }

    @Override
    public Map<String, String> processPayment(Map<String, String> paymentDetails) {
        System.out.println("Connecting to Credit Card API at " + this.config.get("credit_card_endpoint"));
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing credit card payment for " + this.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}

