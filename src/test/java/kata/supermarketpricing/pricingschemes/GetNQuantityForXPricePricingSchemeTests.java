package kata.supermarketpricing.pricingschemes;

import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.Product;
import org.junit.Assert;
import org.junit.Test;

import static kata.supermarketpricing.Currency.money;
import static kata.supermarketpricing.quantity.Quantities.*;

/**
 * by Adil on 14/07/2018.
 */
public class GetNQuantityForXPricePricingSchemeTests {

    @Test
    public void shouldCalculateInvoiceItemTotalWithEligiblePieces() {
        // base price : 1 for 1$ ; special price : 3 for 2$
        GetNQuantityForXPricePricingScheme pricingScheme =
                new GetNQuantityForXPricePricingScheme(
                        new PriceForQuantity(pieces(1), money(1.00)),
                        new PriceForQuantity(pieces(3), money(2.00)));

        Product product = new Product("Chewing Gum Baaable", pricingScheme);

        InvoiceItem invoiceItem = product.asInvoiceItem(pieces(20));

        Assert.assertTrue(invoiceItem != null);
        Assert.assertEquals(2, invoiceItem.size());
        Assert.assertEquals(money(14.00), invoiceItem.total());

    }

    @Test
    public void shouldCalculateInvoiceItemTotalWithoutEligiblePieces() {
        // base price : 1 for 1$ ; special price : 3 for 2$
        GetNQuantityForXPricePricingScheme pricingScheme =
                new GetNQuantityForXPricePricingScheme(
                        new PriceForQuantity(pieces(1), money(1.00)),
                        new PriceForQuantity(pieces(3), money(2.00)));

        Product product = new Product("Chewing Gum Baaable", pricingScheme);

        InvoiceItem invoiceItem = product.asInvoiceItem(pieces(2));

        Assert.assertTrue(invoiceItem != null);
        Assert.assertEquals(1, invoiceItem.size());
        Assert.assertEquals(money(2.00), invoiceItem.total());

    }


    @Test
    public void shouldCalculateInvoiceItemTotalWithEligibleWeight() {
        // base price : 1 for 1$ ; special price : 3 for 2$
        GetNQuantityForXPricePricingScheme pricingScheme =
                new GetNQuantityForXPricePricingScheme(
                        new PriceForQuantity(kilos(1), money(2.00)),
                        new PriceForQuantity(kilos(3), money(5.00)));

        Product product = new Product("Apples", pricingScheme);

        InvoiceItem invoiceItem = product.asInvoiceItem(kilos(4.5));

        Assert.assertTrue(invoiceItem != null);
        Assert.assertEquals(2, invoiceItem.size());
        Assert.assertEquals(money(8.00), invoiceItem.total());

    }

    @Test
    public void shouldCalculateInvoiceItemTotalWithoutEligibleWeight() {
        // base price : 1 for 1$ ; special price : 3 for 2$
        GetNQuantityForXPricePricingScheme pricingScheme =
                new GetNQuantityForXPricePricingScheme(
                        new PriceForQuantity(kilos(1), money(2.00)),
                        new PriceForQuantity(kilos(3), money(5.00)));

        Product product = new Product("Apples", pricingScheme);

        InvoiceItem invoiceItem = product.asInvoiceItem(kilos(0.5));

        Assert.assertTrue(invoiceItem != null);
        Assert.assertEquals(1, invoiceItem.size());
        Assert.assertEquals(money(1.00), invoiceItem.total());

    }





}
