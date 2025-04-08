package GatewayProviders;

import java.util.HashMap;

import GatewayClasses.PaymentGateway;

public class GatewayProvider {
    public static GatewayProvider Instance = new GatewayProvider();
    private HashMap<String, GatewayGetter> paymentGatewayGetterByType =
        new HashMap<String, GatewayGetter>();

    private GatewayProvider() {
        GatewayGetter[] gatewayGetters = new GatewayGetter[] {
                new PayPalGatewayGetter(),
                new StripeGatewayGetter()
        };

        for (GatewayGetter gatewayGetter : gatewayGetters) {
            paymentGatewayGetterByType.put(gatewayGetter.getGatewayType(), gatewayGetter);
        }
    }

    public PaymentGateway getGateway(String gatewayType) {
        return paymentGatewayGetterByType.get(gatewayType).getGateWay();
    }
}
