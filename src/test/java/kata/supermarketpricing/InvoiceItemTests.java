package kata.supermarketpricing;

import org.junit.Assert;
import org.junit.Test;

import static kata.supermarketpricing.Currency.money;
import static kata.supermarketpricing.quantity.Quantities.kilos;

/**
 * by Adil on 15/07/2018.
 */
public class InvoiceItemTests {

    @Test
    public void shouldCalculateTotal() {
        InvoiceItem invoiceItem = new InvoiceItem("Banana");
        invoiceItem.addEntry(new PriceForQuantity(kilos(1), money(2.9)), kilos(2));
        Assert.assertEquals(money(2 * 2.9), invoiceItem.total());
    }

}
