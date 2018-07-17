package kata.supermarketpricing.format;

import kata.supermarketpricing.Invoice;
import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.quantity.Piece;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static kata.supermarketpricing.Currency.money;
import static kata.supermarketpricing.quantity.Quantities.pieces;

/**
 * by Adil on 16/07/2018.
 */
public class StringInvoiceFormatTests {

    @Test
    public void shouldFormatAnInvoice() {

        String product = "Candy";
        Piece quantity = pieces(5);
        InvoiceItem invoiceItem = new InvoiceItem(product);
        invoiceItem.addEntry(new PriceForQuantity(pieces(1), money(0.2)), quantity);

        LocalDateTime now = LocalDateTime.now();
        Invoice invoice = new Invoice(LocalDateTime.now(), Collections.singletonList(invoiceItem));
        String format = invoice.format(InvoiceFormats.stringFormat);
        String[] lines = format.split("\n");
        Assert.assertEquals(lines[0], now.format(DateTimeFormatter.ISO_DATE));
        Assert.assertEquals(lines[1], product +  quantity + "\t" + invoiceItem.total());
        Assert.assertEquals(lines[2], "total=" + invoice.total());


    }



}
