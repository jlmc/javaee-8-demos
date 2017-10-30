package org.jcosta.book.control;

import javax.inject.Inject;
import javax.json.bind.adapter.JsonbAdapter;
import java.util.Currency;

public class CurrencyJsonbAdapter implements JsonbAdapter<Currency, String> {

    // TODO:: CDI is't not working for JsonbAdapter, so for now i'm initialize this usingthe construtor
    @Inject CurrencyConverter converter;

    public CurrencyJsonbAdapter() {
        //this.converter = CurrencyConverter.instance();
    }


    @Override
    public Currency adaptFromJson(String currencyCode) throws Exception {
        return converter.from(currencyCode);
    }

    @Override
    public String adaptToJson(Currency currency) throws Exception {
        return converter.to(currency);
    }
}
