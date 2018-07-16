package kata.supermarketpricing.format;

import kata.supermarketpricing.InvoiceItem;
import org.joda.money.Money;

import java.time.LocalDateTime;
import java.util.List;

/**
 * by Adil on 14/07/2018.
 */

@FunctionalInterface
public interface InvoiceFormat<T> {
    T apply(LocalDateTime dateTime, List<InvoiceItem> invoiceItems, Money total);
}
