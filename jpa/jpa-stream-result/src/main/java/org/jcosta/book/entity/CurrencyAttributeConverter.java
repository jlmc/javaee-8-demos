package org.jcosta.book.entity;

import org.jcosta.book.control.CurrencyConverter;

import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import java.util.Currency;

@javax.persistence.Converter(autoApply = true)
public class CurrencyAttributeConverter implements AttributeConverter<Currency, String> {

    @Inject
    CurrencyConverter converter;

    @Override
    public String convertToDatabaseColumn(Currency attribute) {
        return converter.to(attribute);
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        return this.converter.from(dbData);
    }
}
