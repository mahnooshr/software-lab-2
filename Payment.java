import java.util.Map;

public abstract class Payment {
    protected double amount;
    protected String currency;
    protected long transactionTime;
    protected Map<String, String> customerInfo;
    
    public Payment(double amount, String currency, Map<String, String> customerInfo) {
        this.amount = amount;
        this.currency = currency;
        this.customerInfo = customerInfo;
        this.transactionTime = System.currentTimeMillis();
    }

    public abstract boolean validatePayment(Map<String, String> paymentDetails);

    public abstract Map<String, String> processPayment(Map<String, String> paymentDetails);
}