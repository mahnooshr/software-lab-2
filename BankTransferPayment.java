import java.util.Date;
import java.util.Map;

public class BankTransferPayment extends Payment {

    private Map<String, String> config;

    public BankTransferPayment(double amount, String currency, 
        Map<String, String> customerInfo, Map<String, String> config) {
        super(amount, currency, customerInfo);
        this.config = config;
    }

    @Override
    public boolean validatePayment(Map<String, String> paymentDetails) {
        return paymentDetails.containsKey("account_number");
    }

    @Override
    public Map<String, String> processPayment(Map<String, String> paymentDetails) {
        System.out.println("Connecting to Bank Transfer API at " + config.get("bank_transfer_endpoint"));
        String transactionId = "BT" + new Date().getTime();
        System.out.println("Processing bank transfer payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
