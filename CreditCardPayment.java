import java.util.Map;
import java.util.Date;

public class CreditCardPayment extends Payment {

    public CreditCardPayment(double amount, String currency, Map<String, String> customerInfo) {
        super(amount, currency, customerInfo);
    }

    @Override
    public boolean validatePayment(Map<String, String> paymentDetails) {
       return paymentDetails.containsKey("card_number") && paymentDetails.get("card_number").length() >= 12;
    }

    @Override
    public Map<String, String> processPayment(Map<String, String> paymentDetails) {
      String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing credit card payment...");
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}

