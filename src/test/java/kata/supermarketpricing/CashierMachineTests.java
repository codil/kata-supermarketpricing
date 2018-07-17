package kata.supermarketpricing;

import kata.supermarketpricing.pricingschemes.BuyXQuantityGetYQuantityFreePricingScheme;
import kata.supermarketpricing.pricingschemes.GetNQuantityForXPricePricingScheme;
import kata.supermarketpricing.pricingschemes.RegularPricingScheme;
import kata.supermarketpricing.quantity.Piece;
import kata.supermarketpricing.quantity.Weight;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static kata.supermarketpricing.Currency.money;
import static kata.supermarketpricing.Currency.zero;
import static kata.supermarketpricing.quantity.Quantities.kilos;
import static kata.supermarketpricing.quantity.Quantities.pieces;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Adil on 12/07/2018.
 */
public class CashierMachineTests {

    private ProductFinder productFinder;

    private String candyBarCode = "1235455";
    private Product candy;
    private String beansBarCode = "9035422";
    private Product beans;
    private String bananaBarCode = "630555";
    private Product banana;
    private String batteryBarCode = "333048";
    private Product battery;
    private String mayonnaiseBarCode = "200034";
    private Product mayonnaise;
    private String yogurtBarCode = "996350";
    private Product yogurt;

    @Before
    public void setup() {
        productFinder = mock(ProductFinder.class);

        candy = new Product("Bonbon Dolce & Banana", new GetNQuantityForXPricePricingScheme(
                new PriceForQuantity(pieces(4), money(4.00)),
                new PriceForQuantity(pieces(6), money(5.00))));

        beans = new Product("Haricots fushia", new GetNQuantityForXPricePricingScheme(
                new PriceForQuantity(kilos(1), money(2.50)),
                new PriceForQuantity(kilos(3), money(6.00))));

        banana = new Product("Bananes de mongolie",
                    new BuyXQuantityGetYQuantityFreePricingScheme(
                            new PriceForQuantity(kilos(1), money(1.90)),
                            kilos(2),
                            kilos(0.5)));

        battery = new Product("Batterie d'iphone à sec", new BuyXQuantityGetYQuantityFreePricingScheme(
                new PriceForQuantity(pieces(1), money(999.99)),
                pieces(100),
                pieces(1)));

        mayonnaise = new Product("Mayonnaise zero calories", new RegularPricingScheme(
                new PriceForQuantity(pieces(1), money(0.2))));

        yogurt = new Product("Yaourt de lionne", new RegularPricingScheme(
                new PriceForQuantity(pieces(1), money(500))));

        when(productFinder.productByBarCode(candyBarCode)).thenReturn(candy);
        when(productFinder.productByBarCode(beansBarCode)).thenReturn(beans);
        when(productFinder.productByBarCode(bananaBarCode)).thenReturn(banana);
        when(productFinder.productByBarCode(batteryBarCode)).thenReturn(battery);
        when(productFinder.productByBarCode(mayonnaiseBarCode)).thenReturn(mayonnaise);
        when(productFinder.productByBarCode(yogurtBarCode)).thenReturn(yogurt);
    }

    @Test
    public void shouldProduceAnInvoiceAfterCheckout() {

        CashierMachine cashierMachine = new CashierMachine(productFinder);

        Piece mayonnaiseQuantity1 = pieces(1);
        Piece mayonnaiseQuantity2 = pieces(1);
        Piece candyQuantity = pieces(2);
        Weight bananaQuantity = kilos(3);
        Weight beansQuantity = kilos(5);
        Piece batteriesQuantity1 = pieces(4);
        Piece batteriesQuantity2 = pieces(2);
        Piece yogurtQuantity = pieces(4);

        Invoice invoice =
                cashierMachine.newCheckout()
                        .next(new ScannedItem(mayonnaiseBarCode, mayonnaiseQuantity1))
                        .next(new ScannedItem(mayonnaiseBarCode, mayonnaiseQuantity2))
                        .next(new ScannedItem(candyBarCode, candyQuantity))
                        .next(new ScannedItem(bananaBarCode, bananaQuantity))
                        .next(new ScannedItem(beansBarCode, beansQuantity))
                        .next(new ScannedItem(batteryBarCode, batteriesQuantity1))
                        .next(new ScannedItem(batteryBarCode, batteriesQuantity2))
                        .next(new ScannedItem(yogurtBarCode, yogurtQuantity))
                        .end();

        Assert.assertNotNull(invoice);
        Assert.assertEquals(
                Stream.of(candy.asInvoiceItem(candyQuantity),
                        beans.asInvoiceItem(beansQuantity),
                        banana.asInvoiceItem(bananaQuantity),
                        battery.asInvoiceItem(batteriesQuantity1),
                        battery.asInvoiceItem(batteriesQuantity2),
                        mayonnaise.asInvoiceItem(mayonnaiseQuantity1),
                        mayonnaise.asInvoiceItem(mayonnaiseQuantity2),
                        yogurt.asInvoiceItem(yogurtQuantity)).map(InvoiceItem::total).reduce(zero(), Money::plus),
                invoice.total()
        );

    }

}
