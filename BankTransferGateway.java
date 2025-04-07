import java.util.Map;
import java.util.HashMap;

public class BankTransferGateway implements PaymentGateway {
    private String endpoint;

    public BankTransferGateway(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> processPayment(double amount, String currency, 
                                             Map<String, String> customerInfo,
                                             Map<String, String> paymentDetails) {
        System.out.println("Processing bank transfer via endpoint: " + endpoint);
        System.out.println("Amount: " + amount + " " + currency);
      
        
        Map<String, String> result = new HashMap<>();
        result.put("status", "success");
        result.put("transaction_id", "BT" + System.currentTimeMillis());
        return result;
    }

    @Override
    public Map<String, String> refundPayment(String transactionId, double amount, String reason) {
        System.out.println("Refunding bank transfer transaction: " + transactionId);
        System.out.println("Amount: " + amount + ", Reason: " + reason);
        
        Map<String, String> result = new HashMap<>();
        result.put("status", "refunded");
        result.put("refund_id", "RF" + System.currentTimeMillis());
        return result;
    }

    @Override
    public Map<String, String> getTransactionStatus(String transactionId) {
        System.out.println("Checking status for bank transfer: " + transactionId);
        
        Map<String, String> status = new HashMap<>();
        status.put("status", "completed");
        status.put("transaction_id", transactionId);
        return status;
    }
}
