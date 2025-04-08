package GatewayProviders;

import GatewayClasses.PaymentGateway;

public interface GatewayGetter {
    public String getGatewayType();
    public PaymentGateway getGateWay();
}
