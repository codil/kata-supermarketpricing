package kata.supermarketpricing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static kata.supermarketpricing.Currency.money;
import static kata.supermarketpricing.quantity.Quantities.kilos;
import static kata.supermarketpricing.quantity.Quantities.pieces;

/**
 * by Adil on 15/07/2018.
 */
public class InvoiceTests {

    @Test
    public void shouldCalculateTotal() {
        InvoiceItem invoiceItem1 = new InvoiceItem("Banana");
        invoiceItem1.addEntry(new PriceForQuantity(kilos(2), money(2.9)), kilos(2));
        invoiceItem1.addEntry(new PriceForQuantity(kilos(1), money(3.2)), kilos(0.5));

        InvoiceItem invoiceItem2 = new InvoiceItem("Candy");
        invoiceItem2.addEntry(new PriceForQuantity(pieces(1), money(0.2)), pieces(5));

        Invoice invoice = new Invoice(LocalDateTime.now(), Arrays.asList(invoiceItem1, invoiceItem2));
        Assert.assertEquals(invoiceItem1.total().plus(invoiceItem2.total()), invoice.total());
    }

}
