package kata.supermarketpricing;

import kata.supermarketpricing.format.InvoiceItemFormat;
import kata.supermarketpricing.quantity.Quantity;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kata.supermarketpricing.Currency.money;

/**
 * Each invoice item concerns a product and contains entries based on the pricing scheme template
 * Example : for the batteries branded XYZ we have 4 pieces for 2.00 euro and 1 piece for free
 *
 * by Adil on 14/07/2018.
 */
public class InvoiceItem {

    private String product;
    private List<Entry> entries;

    public InvoiceItem(String productName) {
        this.product = productName;
        this.entries = new ArrayList<>();
    }

    public void addEntry(PriceForQuantity unitsPrice, Quantity quantity) {
        entries.add(new Entry(quantity, unitsPrice, 1));
    }

    public void addEntry(PriceForQuantity priceForQuantity, Quantity quantity, int times) {
        entries.add(new Entry(quantity, priceForQuantity, times));
    }

    public int size() {
        return entries.size();
    }

    public Money total() {
        return entries.stream().map(Entry::sum).reduce(money(0.00), Money::plus);
    }

    public <T> List<T> format(InvoiceItemFormat<T> invoiceItemFormat) {
        return entries.stream()
                .map(e -> invoiceItemFormat.apply(product, e.priceForQuantity, e.quantity, e.sum(), e.times))
                .collect(Collectors.toList());
    }

    private static class Entry {
        Quantity quantity;
        PriceForQuantity priceForQuantity;
        int times;

        private Entry(Quantity quantity, PriceForQuantity priceForXUnits, int times) {
            this.quantity = quantity;
            this.priceForQuantity = priceForXUnits;
            this.times = times;
        }

        Money sum() {
            //return priceForQuantity.update(quantity).price();
            return priceForQuantity.priceFor(quantity);
        }
    }
}
