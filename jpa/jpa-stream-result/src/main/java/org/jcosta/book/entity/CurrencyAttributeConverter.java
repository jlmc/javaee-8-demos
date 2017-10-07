package org.jcosta.book.entity;

import javax.persistence.AttributeConverter;
import java.util.Currency;

@javax.persistence.Converter(autoApply = true)
public class CurrencyAttributeConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency attribute) {
        return attribute.getCurrencyCode();
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        return Currency.getInstance(dbData);
    }
}
