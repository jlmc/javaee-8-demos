package org.costajlmpp.book.entity;

import org.costajlmpp.book.control.CurrencyJsonbAdapter;
import org.costajlmpp.book.control.CurrencyXmlAdapter;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
public class Amount implements Serializable {


    /*
     * The precision is the number of digits in the unscaled value.
     * For instance, for the number 123.45, the precision returned is 5.
     *
     * If zero or positive, the scale is the number of digits to the right of the decimal point.
     * If negative, the unscaled value of the number is multiplied by ten to the power of the negation of the scale.
     * For example, a scale of -3 means the unscaled value is multiplied by 1000.
     *
     *  Basically:
     * Precision: Total number of significant digits
     *
     * Scale: Number of digits to the right of the decimal point
     */

    public enum CurrencyCode {
        EUR("EUR"),
        US_DOLLAR("USD");

        private final String code;

        CurrencyCode(String code) {
            this.code = code;
        }

        protected Currency currency() {
            return Currency.getInstance(code);
        }
    }


    public static final Amount ZERO_EUR = Amount.of(BigDecimal.ZERO, CurrencyCode.EUR);
    public static final Amount ZERO_US_DOLLAR = Amount.of(BigDecimal.ZERO, CurrencyCode.US_DOLLAR);

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal value = BigDecimal.ZERO;


    @JsonbTypeAdapter(CurrencyJsonbAdapter.class)
    @XmlJavaTypeAdapter(CurrencyXmlAdapter.class)
    //@XmlTransient
    //@Convert(converter = CurrencyAttributeConverter.class)
    @Column(nullable = false)
    private java.util.Currency currency = Currency.getInstance("EUR");

    protected Amount() {}

    private Amount(BigDecimal value, Currency currency) {

        if (value == null) {
            throw new IllegalArgumentException("the Amount value can't null");
        }

        if (currency == null){
            throw new IllegalArgumentException("the Amount currency can't null");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("the Amount value can't be less than ZERO");
        }

        this.value = value;
        this.currency = currency;
    }

    private static Amount of (BigDecimal value, Currency currency) {
        return new Amount(value, currency);
    }

    public static Amount of (BigDecimal value, CurrencyCode currencyCode) {
        return new Amount(value, currencyCode.currency());
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    protected void setCurrency(Currency currency) {
        this.currency = currency;
    }

    protected void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) &&
                Objects.equals(currency, amount.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return  value + " " + currency;
    }

    public Amount plus(Amount other) {
        if (this.currency != other.currency) {
            throw new IllegalArgumentException("Can not sum amounts of different currencies" );
        }

        BigDecimal add = this.value.add(other.value);
        return Amount.of(this.value.add(other.value), this.currency);
    }
}
