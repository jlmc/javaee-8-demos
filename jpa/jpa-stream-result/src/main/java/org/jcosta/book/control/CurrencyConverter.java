package org.jcosta.book.control;

import java.util.Currency;

public class CurrencyConverter {


    public Currency from (String currencyCode) {
        return Currency.getInstance(currencyCode);
    }

    public String to (Currency currency) {
        return currency.getCurrencyCode();
    }
}
