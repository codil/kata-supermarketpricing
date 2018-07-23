package kata.supermarketpricing.pricingschemes;

import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.quantity.Quantity;

import static kata.supermarketpricing.Currency.*;

/**
 * A mapping of the scheme "If you buy x quantity you receive y quantity for free"
 * Example : "if you buy 4 batteries, the fifth is free"
 *
 * by Adil on 15/07/2018.
 */
public class BuyXQuantityGetYQuantityFreePricingScheme implements PricingScheme {

    private PriceForQuantity basePriceForQuantity;
    private Quantity eligibleQuantity;
    private Quantity freeQuantity;

    /**
     * Constructor
     * @param basePriceForQuantity This quantity cost x amount (e.g. 1 battery costs 0.2 euro)
     * @param eligibleQuantity The quantity where begins the offer (e.g. 4 pieces)
     * @param freeQuantity The free quantity you can get if you buy the eligible quantity (e.g. 1 piece)
     */

    public BuyXQuantityGetYQuantityFreePricingScheme
            (PriceForQuantity basePriceForQuantity, Quantity eligibleQuantity, Quantity freeQuantity) {
        this.basePriceForQuantity = basePriceForQuantity;
        this.eligibleQuantity = eligibleQuantity;
        this.freeQuantity = freeQuantity;
    }

    /**
     * Based on the brought quantity create the invoice item lines
     * @param productName The product name. Used when generating and formatting an invoice
     * @param broughtQuantity The brought quantity
     * @return An invoice item with entries
     */

    @Override
    public InvoiceItem apply(String productName, Quantity broughtQuantity) {
        InvoiceItem invoiceItem = new InvoiceItem(productName);

        Quantity packageQuantity = eligibleQuantity.plus(freeQuantity);
        int[] part = broughtQuantity.partitionBy(packageQuantity);
        int intoPackage = part[0];
        int outOfPackage = part[1];

        if (intoPackage == 0) {
            invoiceItem.addEntry(basePriceForQuantity, broughtQuantity);
            return invoiceItem;
        }

        Quantity pricedQuantity = eligibleQuantity.times(intoPackage).plus(basePriceForQuantity.quantityTimes(outOfPackage));
        Quantity freeQuantity = broughtQuantity.minus(pricedQuantity);

        invoiceItem.addEntry(basePriceForQuantity, pricedQuantity, intoPackage);
        invoiceItem.addEntry(basePriceForQuantity.update(zero()), freeQuantity, outOfPackage);

        return invoiceItem;
    }
}
