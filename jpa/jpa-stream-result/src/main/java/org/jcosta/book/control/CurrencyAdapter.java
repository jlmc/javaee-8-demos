package org.jcosta.book.control;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Currency;

public class CurrencyAdapter extends XmlAdapter<String, Currency> {

    //http://www.eclipse.org/eclipselink/documentation/2.6/moxy/advanced_concepts006.htm

    public String marshal(Currency val) throws Exception {
        if (val == null) {
            return null;
        }

        return val.getCurrencyCode();
    }

    public Currency unmarshal(String val) throws Exception {
        if (val == null) {
            return null;
        }

        return Currency.getInstance(val);
    }
}
