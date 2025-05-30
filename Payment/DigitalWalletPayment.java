import java.util.Map;

public class DigitalWalletPayment extends Payment {


    public DigitalWalletPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    @Override
    public boolean validatePayment() {
        return this.paymentDetails.containsKey("wallet_id");
    }
}