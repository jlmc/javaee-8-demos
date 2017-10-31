package org.costajlmpp.book.entity;

import org.costajlmpp.book.control.CurrencyConverter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import java.util.Currency;

@javax.persistence.Converter(autoApply = true)
public class CurrencyAttributeConverter implements AttributeConverter<Currency, String> {

    @Inject
    CurrencyConverter converter;

    public CurrencyAttributeConverter() {
        this.converter = CurrencyConverter.instance();
    }

    @PostConstruct
    public void init() {
        System.out.println("CDI is Working for AttributeConverter");
    }

    @Override
    public String convertToDatabaseColumn(Currency attribute) {
        return converter.to(attribute);
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        return this.converter.from(dbData);
    }
}
