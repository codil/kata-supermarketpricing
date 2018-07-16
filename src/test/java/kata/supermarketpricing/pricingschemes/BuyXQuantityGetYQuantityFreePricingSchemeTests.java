package kata.supermarketpricing.pricingschemes;

import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static kata.supermarketpricing.Currency.money;
import static kata.supermarketpricing.quantity.Quantities.kilos;
import static kata.supermarketpricing.quantity.Quantities.pieces;

/**
 * by Adil on 14/07/2018.
 */
public class BuyXQuantityGetYQuantityFreePricingSchemeTests {

    private Product productPricedByPiece;
    private Product productPricedByWeigh;

    @Before
    public void setup() {
        PricingScheme piecesPricingScheme = new BuyXQuantityGetYQuantityFreePricingScheme(
                new PriceForQuantity(pieces(1), money(3.90)),
                pieces(3),
                pieces(1));
        PricingScheme weightPricingScheme = new BuyXQuantityGetYQuantityFreePricingScheme(
                new PriceForQuantity(kilos(1), money(6.90)),
                kilos(2),
                kilos(0.5));
        productPricedByPiece = new Product("Candy", piecesPricingScheme);
        productPricedByWeigh = new Product("Banana", weightPricingScheme);
    }

    @Test
    public void shouldCalculateInvoiceItemTotalWithEligiblePieces() {

        Product product = productPricedByPiece;

        InvoiceItem invoiceItem = product.asInvoiceItem(pieces(21));

        Assert.assertNotNull(invoiceItem);
        Assert.assertEquals(2, invoiceItem.size());
        Assert.assertEquals(money(62.40), invoiceItem.total());
    }

    @Test
    public void shouldCalculateInvoiceItemTotalWithoutEligiblePieces() {

        Product product = productPricedByPiece;

        InvoiceItem invoiceItem = product.asInvoiceItem(pieces(2));

        Assert.assertNotNull(invoiceItem);
        Assert.assertEquals(1, invoiceItem.size());
        Assert.assertEquals(money(7.80), invoiceItem.total());
    }

    @Test
    public void shouldCalculateInvoiceItemTotalWithEligibleWeight() {

        Product product = productPricedByWeigh;

        InvoiceItem invoiceItem = product.asInvoiceItem(kilos(3));

        Assert.assertNotNull(invoiceItem);
        Assert.assertEquals(2, invoiceItem.size());
        Assert.assertEquals(money(17.25), invoiceItem.total());
    }

    @Test
    public void shouldCalculateInvoiceItemTotalWithoutEligibleWeight() {

        Product product = productPricedByWeigh;

        InvoiceItem invoiceItem = product.asInvoiceItem(kilos(2));

        Assert.assertNotNull(invoiceItem);
        Assert.assertEquals(1, invoiceItem.size());
        Assert.assertEquals(money(13.80), invoiceItem.total());
    }

}
