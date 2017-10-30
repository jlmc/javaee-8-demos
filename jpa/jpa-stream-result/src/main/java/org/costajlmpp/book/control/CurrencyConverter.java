package org.costajlmpp.book.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.Currency;

@Default
@ApplicationScoped
public class CurrencyConverter {


    public Currency from (String currencyCode) {
        return Currency.getInstance(currencyCode);
    }

    public String to (Currency currency) {
        return currency.getCurrencyCode();
    }

    public static CurrencyConverter instance() {
        return new CurrencyConverter();
    }
}
