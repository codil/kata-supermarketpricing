package kata.supermarketpricing.pricingschemes;

import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.quantity.Quantity;

/**
 * The regular simple pricing scheme where each quantity is priced by quantity.
 *
 * by Adil on 15/07/2018.
 */
public class RegularPricingScheme implements PricingScheme {

    private PriceForQuantity priceForQuantity;

    /**
     * Constructor
     * @param priceForQuantity The pricing model (e.g. 1 battery for 0.5 euro)
     */
    public RegularPricingScheme(PriceForQuantity priceForQuantity) {
        this.priceForQuantity = priceForQuantity;
    }

    /**
     * Based on the brought quantity create the invoice item lines
     * @param productName The product name. Used when generating and formatting an invoice
     * @param broughtQuantity The brought quantity
     * @return An invoice item with one single entry in this particular case
     */
    @Override
    public InvoiceItem apply(String productName, Quantity broughtQuantity) {
        InvoiceItem invoiceItem = new InvoiceItem(productName);
        invoiceItem.addEntry(priceForQuantity, broughtQuantity);
        return invoiceItem;
    }
}
