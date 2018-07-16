package kata.supermarketpricing;

import kata.supermarketpricing.quantity.Quantity;
import org.joda.money.Money;

import java.math.RoundingMode;

import static kata.supermarketpricing.Currency.*;

/**
 * by Adil on 14/07/2018.
 */
public class PriceForQuantity {
    private Money price;
    private Quantity quantity;

    public PriceForQuantity(Quantity quantity, Money amount) {
        this.price = amount;
        this.quantity = quantity;
    }

    public int[] occurrencesIn(Quantity quantity) {
        return quantity.partitionBy(this.quantity);
    }

    public Money priceFor(Quantity quantity) {
        double ratio = this.quantity.ratio(quantity);
        return price.multipliedBy(ratio, ROUNDING_MODE);
    }

    public PriceForQuantity times(int times) {
        return new PriceForQuantity(quantity.times(times), this.price.multipliedBy(times, ROUNDING_MODE));
    }


    public PriceForQuantity update(Quantity newQuantity) {
        double ratio = quantity.ratio(newQuantity);
        return new PriceForQuantity(newQuantity, price.multipliedBy(ratio, ROUNDING_MODE));
    }

    public PriceForQuantity update(Money newPrice) {
        return new PriceForQuantity(quantity, newPrice);
    }

    public Quantity quantityTimes(int times) {
        return quantity.times(times);
    }
}
