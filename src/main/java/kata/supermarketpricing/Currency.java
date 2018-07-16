package kata.supermarketpricing;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.RoundingMode;

/**
 * by Adil on 15/07/2018.
 */
public class Currency {

    private static CurrencyUnit eur = CurrencyUnit.of("EUR");

    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    public static Money money(double amount) {
        return Money.of(eur, amount);
    }

    public static Money zero() {
        return Money.zero(eur);
    }
}
