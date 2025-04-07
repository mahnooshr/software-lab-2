import java.util.Map;


public interface PaymentGateway {

    String processPayment(double amount, String currency, 
                                      Map<String, String> customerInfo,
                                      Map<String, String> paymentDetails);
    
 
    void refundPayment(Map<String, String> paymentDetails, String transactionId);
    
  
    Map<String, String> getTransactionStatus(String transactionId);
}
