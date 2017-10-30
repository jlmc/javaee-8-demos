package org.jcosta.store;

import org.jcosta.Purchase;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class BookStore {

    @Inject
    Event<Purchase> purchaseEvent;

    public String createPurchase(String productId, int quantity)  {
        Purchase purchase = new Purchase("Book CDI 2.0 - SE ", BigDecimal.TEN);


        CompletionStage<Purchase> purchaseCompletionStage = purchaseEvent.fireAsync(purchase);

        CompletionStage<String> stringCompletionStage = purchaseCompletionStage
                .thenApply(p -> this.printInvoice(p));


        return waitForInvoice(stringCompletionStage);
    }

    private String waitForInvoice(CompletionStage<String> stringCompletionStage) {
        try {
            return stringCompletionStage.toCompletableFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private String printInvoice(Purchase p) {
        //final String s = JsonbBuilder.create().toJson(p);
        return "this is the Invoice Detail " + LocalDateTime.now();
    }


}
