package kata.supermarketpricing;

import kata.supermarketpricing.pricingschemes.PricingScheme;
import kata.supermarketpricing.quantity.Quantity;

/**
 * Each product has a name and a {@link PricingScheme}
 *
 * by Adil on 14/07/2018.
 */
public class Product {
    private String name;
    private PricingScheme pricingScheme;

    public Product(String name, PricingScheme pricingScheme) {
        this.name = name;
        this.pricingScheme = pricingScheme;
    }

    /**
     * Transforms a product to an invoice item
     * @param broughtQuantity The brought quantity
     * @return The {@link InvoiceItem}
     */
    public InvoiceItem asInvoiceItem(Quantity broughtQuantity) {
        return pricingScheme.apply(this.name, broughtQuantity);
    }
}
