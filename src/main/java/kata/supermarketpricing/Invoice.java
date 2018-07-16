package kata.supermarketpricing;

import kata.supermarketpricing.format.InvoiceFormat;
import kata.supermarketpricing.format.InvoiceItemFormat;
import org.joda.money.Money;

import java.time.LocalDateTime;
import java.util.List;

import static kata.supermarketpricing.Currency.*;

/**
 * An invoice contains a list of {@link InvoiceItem} each one holding the sum of its elements
 * by Adil on 14/07/2018.
 */
public class Invoice {

    private LocalDateTime dateTime;
    private List<InvoiceItem> items;

    public Invoice(LocalDateTime dateTime, List<InvoiceItem> items) {
        this.dateTime = dateTime;
        this.items = items;
    }

    /**
     * @return The sum of all {@link InvoiceItem} sum
     */
    public Money total() {
        return items.stream().map(InvoiceItem::total).reduce(zero(), Money::plus);
    }

    /**
     * To format an invoice for console, print, LCD screen, computer screen...
     * @param invoiceFormat The invoice formatter
     * @param <T> The type returned by the formatter, can be a string or a template format
     * @return The formatter return type
     */
    public <T> T format(InvoiceFormat<T> invoiceFormat) {
        return invoiceFormat.apply(dateTime, items, total());
    }

}
