package kata.supermarketpricing.format;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * by Adil on 16/07/2018.
 */
public class InvoiceFormats {

    public static InvoiceFormat<String> stringFormat = (InvoiceFormat<String>) (dateTime, invoiceItems, total) ->


            dateTime.format(DateTimeFormatter.ISO_DATE) + "\n" +
                    invoiceItems
                            .stream()
                            .map(e -> e.format((product, priceForQuantity, quantity, sum, packages) ->
                                    product + quantity + "\t" + sum).stream().collect(Collectors.joining("\n")))
                            .collect(Collectors.joining("\n")) +
                    "\ntotal=" + total;

}
