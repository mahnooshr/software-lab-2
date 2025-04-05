import java.util.Date;
import java.util.Map;

public class DigitalWalletPayment extends Payment {

    private Map<String, String> config;

    public DigitalWalletPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> config) {
        super(amount, currency, customerInfo);
        this.config = config;
    }

    @Override
    public boolean validatePayment(Map<String, String> paymentDetails) {
        return paymentDetails.containsKey("wallet_id");
    }

    @Override
    public Map<String, String> processPayment(Map<String, String> paymentDetails) {
        System.out.println("Connecting to Digital Wallet API at " + this.config.get("digital_wallet_endpoint"));
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing digital wallet payment for " + this.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}