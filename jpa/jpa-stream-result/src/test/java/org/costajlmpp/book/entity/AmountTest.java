package org.jcosta.book.entity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class AmountTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test() {
        Currency instance = Currency.getInstance(Locale.FRANCE);

        System.out.println(instance);
        System.out.println(instance.getCurrencyCode());
        System.out.println(instance.getDisplayName());
        System.out.println(instance.getNumericCode());
        System.out.println("----");

        Currency eur = Currency.getInstance("EUR");

        System.out.println(instance.equals(eur));
        System.out.println(eur);
        System.out.println(eur.getCurrencyCode());
        System.out.println(eur.getDisplayName());
        System.out.println(eur.getNumericCode());
    }

    @Test
    public void shouldAddMoneyOfTheSameCurrency() {
        Amount zeroEur = Amount.ZERO_EUR;
        Amount plusResult = zeroEur.plus(Amount.of(BigDecimal.TEN, Amount.CurrencyCode.EUR));

        assertFalse(plusResult == zeroEur);
        assertFalse(zeroEur.equals(plusResult));
        assertEquals(Amount.CurrencyCode.EUR.currency(), plusResult.getCurrency());
        assertEquals(BigDecimal.TEN, plusResult.getValue());
        assertEquals(Amount.of(BigDecimal.TEN, Amount.CurrencyCode.EUR), plusResult);
    }

    @Test
    public void shouldNotAddMoneyOfTheDiferentsCurrency() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(containsString("Can not sum amounts of different currencies"));

        Amount zeroEur = Amount.ZERO_EUR;
        Amount plusResult = zeroEur.plus(Amount.of(BigDecimal.TEN, Amount.CurrencyCode.US_DOLLAR));



    }

}