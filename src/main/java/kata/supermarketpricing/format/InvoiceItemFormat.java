package kata.supermarketpricing.format;

import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.quantity.Quantity;
import org.joda.money.Money;

/**
 * by Adil on 15/07/2018.
 */
public interface InvoiceItemFormat<T> {
    T apply(String product, PriceForQuantity priceForQuantity, Quantity quantity, Money amount, int packages);
}
