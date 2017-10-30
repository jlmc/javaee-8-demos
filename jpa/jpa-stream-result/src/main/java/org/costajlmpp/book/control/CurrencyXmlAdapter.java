package org.costajlmpp.book.control;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Currency;

public class CurrencyXmlAdapter extends XmlAdapter<String, Currency> {

    // TODO:: CDI is't not working for JsonbAdapter, so for now i'm initialize this usingthe construtor
    private final CurrencyConverter converter;

    //http://www.eclipse.org/eclipselink/documentation/2.6/moxy/advanced_concepts006.htm

    public CurrencyXmlAdapter() {
        this.converter = CurrencyConverter.instance();
    }

    public String marshal(Currency val) throws Exception {
        return converter.to(val);
    }

    public Currency unmarshal(String val) throws Exception {
        return converter.from(val);
    }
}
