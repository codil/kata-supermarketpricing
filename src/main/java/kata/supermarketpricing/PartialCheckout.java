package kata.supermarketpricing;

import kata.supermarketpricing.quantity.Quantities;
import kata.supermarketpricing.quantity.Quantity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * by Adil on 14/07/2018.
 */
public class PartialCheckout {

    private Set<Entry> products;

    private ProductFinder productFinder;

    PartialCheckout(ProductFinder productFinder) {
        this.productFinder = productFinder;
        products = new HashSet<>();
    }

    private PartialCheckout(ProductFinder productFinder, Set<Entry> products) {
        this.productFinder = productFinder;
        this.products = products;
    }

    PartialCheckout next(ScannedItem scannedItem) {
        Product product = productFinder.productByBarCode(scannedItem.barCode());
        products.add(new Entry(product, scannedItem.quantity()));
        return new PartialCheckout(productFinder, products);
    }

    Invoice end() {
        List<InvoiceItem> invoiceItems = products.stream()
                .collect(groupingBy(Entry::product, mapping(Entry::quantity, toList())))
                .entrySet()
                .stream()
                .map(entry -> entry.getKey().asInvoiceItem(entry.getValue().stream().reduce(Quantity::plus).orElse(Quantities.nothing())))
                .collect(toList());
        return new Invoice(LocalDateTime.now(), invoiceItems);
    }

    private class Entry {
        private Product product;
        private Quantity quantity;

        private Entry(Product product, Quantity quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        Product product() {
            return this.product;
        }

        Quantity quantity() {
            return this.quantity;
        }
    }


}
