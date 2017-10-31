package org.costajlmpp.stock;

import org.costajlmpp.Purchase;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class StockService {

    private BigDecimal defaultValue = BigDecimal.valueOf(0.3D);

    public void reservationMerchandise(@ObservesAsync @Priority(1) Purchase purchase) {
        System.out.println("Stock Service -- Working " + Thread.currentThread().getName() + " - " + LocalDateTime.now());
        purchase.addValue("Stock-Service", purchase.getValue().multiply(defaultValue));
    }

}
