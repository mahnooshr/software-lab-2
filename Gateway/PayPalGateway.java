import java.util.Date;
import java.util.Map;

public class PayPalGateway implements PaymentGateway{
    
    private Map<String, String> config;

    public PayPalGateway(Map<String, String> config){
        this.config = config;
    }

    public String processPayment(double amount, String currency, 
                                      Map<String, String> customerInfo,
                                      Map<String, String> paymentDetails){

        String paymentEndpoint = paymentDetails.get("payment_endpoint");
        String baseUrl = this.config.get("paypal_process_endpoint");
        System.out.println("Connecting to PayPalGateway at " + baseUrl + paymentEndpoint);

        String paymentType = paymentDetails.get("payment_type");
        String transactionId = paymentType + new Date().getTime();
        
        System.out.println("Processing payment for " + customerInfo.get("name"));
        return transactionId;
    }
    
    public void refundPayment(Map<String, String> paymentDetails, String transactionId){
        String paymentEndpoint = paymentDetails.get("payment_endpoint");
        String baseUrl = this.config.get("paypal_refund_endpoint");
        System.out.println("Refund payment with transaction id" + transactionId + " to PayPalGateway at " + baseUrl + paymentEndpoint);
    }
    
    public Map<String, String> getTransactionStatus(Map<String, String> paymentDetails, String transactionId){
        String paymentEndpoint = paymentDetails.get("payment_endpoint");
        String baseUrl = this.config.get("paypal_get_status_endpoint");
        System.out.println("Get status of payment with transaction id" + transactionId + " to PayPalGateway at " + baseUrl + paymentEndpoint);
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}