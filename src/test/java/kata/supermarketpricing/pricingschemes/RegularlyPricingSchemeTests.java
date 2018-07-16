package kata.supermarketpricing.pricingschemes;

import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static kata.supermarketpricing.Currency.money;
import static kata.supermarketpricing.quantity.Quantities.kilos;
import static kata.supermarketpricing.quantity.Quantities.pieces;

/**
 * by Adil on 15/07/2018.
 */
public class RegularlyPricingSchemeTests {

    private Product productPricedByPiece;
    private Product productPricedByWeight;

    @Before
    public void setup() {
        PricingScheme piecesPricingScheme = new RegularPricingScheme(
                new PriceForQuantity(pieces(1), money(0.20)));

        PricingScheme weightPricingScheme = new RegularPricingScheme(
                new PriceForQuantity(kilos(1), money(2.99)));

        productPricedByPiece = new Product("Bonbon", piecesPricingScheme);
        productPricedByWeight = new Product("Bananes", weightPricingScheme);
    }

    @Test
    public void shouldCalculateInvoiceItemTotalForPieces() {

        Product product = productPricedByPiece;

        InvoiceItem invoiceItem = product.asInvoiceItem(pieces(21));

        Assert.assertNotNull(invoiceItem);
        Assert.assertEquals(1, invoiceItem.size());
        Assert.assertEquals(money(4.20), invoiceItem.total());
    }

    @Test
    public void shouldCalculateInvoiceItemTotalForWeight() {

        Product product = productPricedByWeight;

        InvoiceItem invoiceItem = product.asInvoiceItem(kilos(1.5));

        Assert.assertNotNull(invoiceItem);
        Assert.assertEquals(1, invoiceItem.size());
        Assert.assertEquals(money(4.48), invoiceItem.total());
    }




}
