package org.costajlmpp.delivery;


import org.costajlmpp.Purchase;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class DeliveryService {

    private BigDecimal defaultValue = BigDecimal.valueOf(0.2D);

    public void registeDelivery(@ObservesAsync @Priority(2) Purchase purchase) {
        System.out.println("Delivery Service -- Working " + Thread.currentThread().getName() + " - " + LocalDateTime.now());
        purchase.addValue("Delivery", purchase.getValue().multiply(defaultValue));
    }


}
