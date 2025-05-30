import java.util.Map;

public class CreditCardPayment extends Payment {

    public CreditCardPayment(double amount, String currency, Map<String, String> customerInfo,
                         Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    @Override
    public boolean validatePayment() {
       return this.paymentDetails.containsKey("card_number") && paymentDetails.get("card_number").length() >= 12;
    }
}

