import java.util.Map;

import GatewayClasses.PaymentGateway;

public abstract class Payment {
    protected double amount;
    protected String currency;
    protected long transactionTime;
    protected Map<String, String> customerInfo;
     protected Map<String, String> paymentDetails;
    
    public Payment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        this.amount = amount;
        this.currency = currency;
        this.customerInfo = customerInfo;
        this.paymentDetails = paymentDetails;
        this.transactionTime = System.currentTimeMillis();
    }

    public abstract boolean validatePayment();

    public Map<String, String> processPayment(PaymentGateway gateway) {
        String transactionId = gateway.processPayment(amount, currency, customerInfo, paymentDetails);

        Map<String, String> status = gateway.getTransactionStatus(paymentDetails, transactionId);

        if (status.get("status").equals("success"))
            return status;

        gateway.refundPayment(paymentDetails, transactionId);

        return gateway.getTransactionStatus(paymentDetails, transactionId);
    }

    public double getAmount() {
        return amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public Map<String, String> getCustomerInfo() {
        return customerInfo;
    }
    
    public Map<String, String> getPaymentDetails() {
        return paymentDetails;
    }
}
