package kata.supermarketpricing.pricingschemes;

import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.Product;
import kata.supermarketpricing.quantity.Quantity;

/**
 * by Adil on 14/07/2018.
 */
public interface PricingScheme {
    InvoiceItem apply(String productName, Quantity broughtQuantity);
}
