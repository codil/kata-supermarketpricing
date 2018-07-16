package kata.supermarketpricing.pricingschemes;

import kata.supermarketpricing.InvoiceItem;
import kata.supermarketpricing.PriceForQuantity;
import kata.supermarketpricing.quantity.Quantity;

/**
 * A mapping of the scheme "get n quantity for x price"
 * Example : "1 battery costs 1.5 euros, 4 batteries costs 4.00 euros only"
 *
 * by Adil on 14/07/2018.
 */
public class GetNQuantityForXPricePricingScheme implements PricingScheme {

    private PriceForQuantity regularPrice;
    private PriceForQuantity specialPrice;

    /**
     * Constructor
     * @param regularPrice The regular price
     * @param specialPrice The special price if you buy n quantity
     */
    public GetNQuantityForXPricePricingScheme(PriceForQuantity regularPrice, PriceForQuantity specialPrice) {
        this.regularPrice = regularPrice;
        this.specialPrice = specialPrice;
    }

    /**
     * Based on the brought quantity create the invoice item lines
     * @param productName The product name. Used when generating and formatting an invoice
     * @param broughtQuantity The brought quantity
     * @return An invoice item with entries
     */
    public InvoiceItem apply(String productName, Quantity broughtQuantity) {

        InvoiceItem invoiceItem = new InvoiceItem(productName);

        int[] div = specialPrice.occurrencesIn(broughtQuantity);
        int specialPackages = div[0];
        int regularPackages = div[1];

        Quantity speciallyPricedQuantity = specialPrice.quantityTimes(specialPackages);
        Quantity regularlyPricedQuantity = broughtQuantity.minus(speciallyPricedQuantity);

        if (specialPackages > 0) {
            invoiceItem.addEntry(specialPrice, speciallyPricedQuantity, specialPackages);
        }

        invoiceItem.addEntry(regularPrice, regularlyPricedQuantity, regularPackages);

        return invoiceItem;
    }

}
