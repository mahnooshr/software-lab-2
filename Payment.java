import java.util.Map;

public abstract class Payment {
    protected double amount;
    protected String currency;
    protected long transactionTime;
    
    public Payment(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
        this.transactionTime = System.currentTimeMillis();
    }
  public abstract boolean validatePayment(Map<String, String> paymentDetails);
   public abstract Map<String, String> processPayment(Map<String, String> paymentDetails);
}

public class CreditCardPayment extends Payment {

    public CreditCardPayment(double amount, String currency) {
        super(amount, currency);
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
